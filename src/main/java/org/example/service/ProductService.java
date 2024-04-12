package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.ProductDto;
import org.example.framework.exception.NotFoundException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.BaseService;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements BaseService {
    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    private final ProductRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public ProductService(ProductRepository repository,
                          ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, ProductDto.class))
                .orElseThrow(NotFoundException::new);
    }

    public List<ProductDto> readAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent()
                .stream()
                .map(entity -> mapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductDto> readAllByCategory(Long categoryId, int page, int size) {
        return repository.findAllByCategory_Id(PageRequest.of(page, size), categoryId).getContent()
                .stream()
                .map(entity -> mapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductDto> readAllNew() {
        return repository.findAllNew().stream()
                .map(entity -> mapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductDto> readAllPopular() {
        return repository.findAllPopular().stream()
                .map(entity -> mapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
    }
}
