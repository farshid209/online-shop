package org.example.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.InvoiceItemDto;
import org.example.entity.InvoiceItem;
import org.example.framework.exception.NotFoundException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.operations.CreateService;
import org.example.framework.services.operations.ReadService;
import org.example.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceItemService implements ReadService<Long, InvoiceItemDto>, CreateService<Long, InvoiceItemDto> {
    private static final Logger LOGGER = LogManager.getLogger(InvoiceItemService.class);

    private final InvoiceItemRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public InvoiceItemService(InvoiceItemRepository repository,
                              ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public InvoiceItemDto create(InvoiceItemDto invoiceItemDto) {
        InvoiceItem entity = mapper.map(invoiceItemDto, InvoiceItem.class);
        entity = repository.save(entity);
        return mapper.map(entity, InvoiceItemDto.class);
    }

    @Override
    public InvoiceItemDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, InvoiceItemDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<InvoiceItemDto> readAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, InvoiceItemDto.class))
                .collect(Collectors.toList());
    }
}
