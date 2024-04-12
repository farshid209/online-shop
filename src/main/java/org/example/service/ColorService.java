package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.ColorDto;
import org.example.framework.exception.NotFoundException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.operations.ReadService;
import org.example.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorService implements ReadService<Long, ColorDto> {
    private static final Logger LOGGER = LogManager.getLogger(ColorService.class);

    private final ColorRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public ColorService(ColorRepository repository,
                        ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ColorDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, ColorDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<ColorDto> readAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, ColorDto.class))
                .collect(Collectors.toList());
    }
}
