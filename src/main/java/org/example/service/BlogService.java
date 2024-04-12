package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.BlogDto;
import org.example.framework.controllers.SearchFilter;
import org.example.framework.exception.NotFoundException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.operations.ReadService;
import org.example.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService implements ReadService<Long, BlogDto> {
    private static final Logger LOGGER = LogManager.getLogger(BlogService.class);

    private final BlogRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public BlogService(BlogRepository repository,
                       ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public BlogDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, BlogDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<BlogDto> readAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, BlogDto.class))
                .collect(Collectors.toList());
    }
}
