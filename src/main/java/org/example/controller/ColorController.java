package org.example.controller;

import jakarta.servlet.ServletRequest;
import org.example.dto.ColorDto;
import org.example.enumaration.ResponseStatus;
import org.example.framework.controllers.ServiceResponse;
import org.example.framework.controllers.requests.ReadRequest;
import org.example.framework.exception.NotFoundException;
import org.example.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/color")
public class ColorController implements ReadRequest<Long, ColorDto> {
    private final ColorService service;

    @Autowired
    public ColorController(ColorService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ServiceResponse<ColorDto>> find(Long id, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), Collections.singletonList(service.read(id)));
    }

    @Override
    public ResponseEntity<ServiceResponse<ColorDto>> findAll(ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), service.readAll());
    }
}
