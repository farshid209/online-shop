package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.ProductCategoryDto;
import org.example.framework.exception.NotFoundException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.operations.ReadService;
import org.example.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService implements ReadService<Long, ProductCategoryDto> {
    private static final Logger LOGGER = LogManager.getLogger(ProductCategoryService.class);

    private final ProductCategoryRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository repository,
                                  ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductCategoryDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, ProductCategoryDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<ProductCategoryDto> readAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, ProductCategoryDto.class))
                .collect(Collectors.toList());
    }
}
