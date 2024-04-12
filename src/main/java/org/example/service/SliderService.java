package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.SliderDto;
import org.example.framework.exception.NotFoundException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.operations.ReadService;
import org.example.repository.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SliderService implements ReadService<Long, SliderDto> {
    private static final Logger LOGGER = LogManager.getLogger(SliderService.class);

    private final SliderRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public SliderService(SliderRepository repository,
                         ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SliderDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, SliderDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<SliderDto> readAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, SliderDto.class))
                .collect(Collectors.toList());
    }
}
