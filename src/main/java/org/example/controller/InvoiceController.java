package org.example.controller;

import jakarta.servlet.ServletRequest;
import org.example.dto.InvoiceDto;
import org.example.enumaration.ResponseStatus;
import org.example.framework.controllers.ServiceResponse;
import org.example.framework.controllers.requests.CreateRequest;
import org.example.framework.exception.NotFoundException;
import org.example.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController implements CreateRequest<Long, InvoiceDto> {
    private final InvoiceService service;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ServiceResponse<InvoiceDto>> add(InvoiceDto dto, ServletRequest request) {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), Collections.singletonList(service.create(dto)));
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<InvoiceDto>> find(@PathVariable Long id, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), Collections.singletonList(service.read(id)));
    }

    @CrossOrigin
    @GetMapping(value = "/user/{userId}", params = {"pageIndex", "pageSize"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<InvoiceDto>> findAllByUser(@PathVariable Long userId, int pageIndex, int pageSize, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), service.readAllByUser(userId, pageIndex, pageSize));
    }
}
