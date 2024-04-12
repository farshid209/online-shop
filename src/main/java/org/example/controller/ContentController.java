package org.example.controller;

import jakarta.servlet.ServletRequest;
import org.example.dto.ContentDto;
import org.example.enumaration.ResponseStatus;
import org.example.framework.controllers.ServiceResponse;
import org.example.framework.controllers.requests.ReadRequest;
import org.example.framework.exception.NotFoundException;
import org.example.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/content")
public class ContentController implements ReadRequest<Long, ContentDto> {
    private final ContentService service;

    @Autowired
    public ContentController(ContentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ServiceResponse<ContentDto>> find(Long id, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), Collections.singletonList(service.read(id)));
    }

    @CrossOrigin
    @GetMapping(value = "/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<ContentDto>> findByTitle(@PathVariable String title, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), Collections.singletonList(service.readByTitle(title)));
    }

    @Override
    public ResponseEntity<ServiceResponse<ContentDto>> findAll(ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), service.readAll());
    }
}
