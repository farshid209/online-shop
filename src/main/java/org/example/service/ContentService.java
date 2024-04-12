package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.ContentDto;
import org.example.framework.exception.NotFoundException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.operations.ReadService;
import org.example.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService implements ReadService<Long, ContentDto> {
    private static final Logger LOGGER = LogManager.getLogger(ContentService.class);

    private final ContentRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public ContentService(ContentRepository repository,
                          ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ContentDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, ContentDto.class))
                .orElseThrow(NotFoundException::new);
    }

    public ContentDto readByTitle(String title) throws NotFoundException {
        return repository.findByTitle(title)
                .map(entity -> mapper.map(entity, ContentDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<ContentDto> readAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, ContentDto.class))
                .collect(Collectors.toList());
    }
}
