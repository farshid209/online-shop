package org.example.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.CustomerDto;
import org.example.dto.UserDto;
import org.example.dto.UserViewModelDto;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.enumaration.ResponseStatus;
import org.example.framework.exception.NotFoundException;
import org.example.framework.exception.RequestRestrictedException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.operations.ReadService;
import org.example.repository.CustomerRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements ReadService<Long, UserDto> {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private final UserRepository repository;
    private final CustomerRepository customerRepository;
    private final ObjectMapper mapper;

    @Autowired
    public UserService(UserRepository repository,
                       CustomerRepository customerRepository, ObjectMapper mapper) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, UserDto.class))
                .orElseThrow(NotFoundException::new);
    }

    public UserDto readByCustomer(Long customerId) throws NotFoundException {
        return repository.findByCustomer_Id(customerId)
                .map(entity -> mapper.map(entity, UserDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<UserDto> readAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto create(UserViewModelDto dto) {
        CustomerDto customerDto = CustomerDto.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .postalCode(dto.getPostalCode())
                .build();

        customerDto.setId(customerRepository.save(mapper.map(customerDto, Customer.class)).getId());

        UserDto userDto = UserDto.builder()
                .customer(customerDto)
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();

        User entity = repository.save(mapper.map(userDto, User.class));
        return mapper.map(entity, UserDto.class);
    }

    @Transactional
    public UserDto update(UserViewModelDto dto) throws NotFoundException {
        User user = repository.findByCustomer_Id(dto.getCustomerId()).orElseThrow(NotFoundException::new);
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(NotFoundException::new);

        Optional.ofNullable(dto.getFirstName()).ifPresent(customer::setFirstName);
        Optional.ofNullable(dto.getLastName()).ifPresent(customer::setLastName);
        Optional.ofNullable(dto.getPhone()).ifPresent(customer::setPhone);
        Optional.ofNullable(dto.getAddress()).ifPresent(customer::setAddress);
        Optional.ofNullable(dto.getPostalCode()).ifPresent(customer::setPostalCode);

        customerRepository.save(customer);
        user.setCustomer(customer);
        return mapper.map(user, UserDto.class);
    }

    @Transactional
    public UserDto changePassword(UserViewModelDto dto) throws NotFoundException {
        User user = repository.findByCustomer_Id(dto.getCustomerId()).orElseThrow(NotFoundException::new);

        if (dto.getOldPassword() == null || dto.getPassword() == null || dto.getRepeatPassword() == null) {
            throw new RequestRestrictedException("Passwords can't be null!", ResponseStatus.FAILURE.getDescription());
        }

        if (!dto.getOldPassword().equals(user.getPassword())) {
            throw new RequestRestrictedException("User Password doesn't match with old password!", ResponseStatus.FAILURE.getDescription());
        }

        if (!dto.getPassword().equals(dto.getRepeatPassword())) {
            throw new RequestRestrictedException("Password doesn't match with repeated password!", ResponseStatus.FAILURE.getDescription());
        }

        if (user.getUsername() == null) {
            user.setUsername(dto.getUsername());
        }

        user.setPassword(dto.getPassword());
        return mapper.map(repository.save(user), UserDto.class);
    }
}
