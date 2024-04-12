package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.InvoiceDto;
import org.example.entity.Invoice;
import org.example.framework.exception.NotFoundException;
import org.example.framework.mapping.ObjectMapper;
import org.example.framework.services.operations.CreateService;
import org.example.framework.services.operations.UpdateService;
import org.example.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements CreateService<Long, InvoiceDto>, UpdateService<Long, InvoiceDto> {
    private static final Logger LOGGER = LogManager.getLogger(InvoiceService.class);

    private final InvoiceRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public InvoiceService(InvoiceRepository repository,
                          ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public InvoiceDto create(InvoiceDto invoiceDto) {
        Invoice entity = mapper.map(invoiceDto, Invoice.class);
        entity = repository.save(entity);
        return mapper.map(entity, InvoiceDto.class);
    }

    public InvoiceDto read(Long id) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, InvoiceDto.class))
                .orElseThrow(NotFoundException::new);
    }

    public List<InvoiceDto> readAllByUser(Long userId, int page, int size) {
        return repository.findAllByUser_Id(PageRequest.of(page, size), userId).getContent()
                .stream()
                .map(entity -> mapper.map(entity, InvoiceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, InvoiceDto invoiceDto) throws NotFoundException {
        Invoice invoice = repository.findById(id).orElseThrow(NotFoundException::new);

        Optional.ofNullable(invoiceDto.getPaymentDate()).ifPresent(invoice::setPaymentDate);
        Optional.ofNullable(invoiceDto.getStatus()).ifPresent(invoice::setStatus);

        repository.save(invoice);
    }
}
