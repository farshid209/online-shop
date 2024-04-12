package org.example.controller;

import jakarta.servlet.ServletRequest;
import org.example.dto.ProductDto;
import org.example.enumaration.ResponseStatus;
import org.example.framework.controllers.BaseRequest;
import org.example.framework.controllers.ServiceResponse;
import org.example.framework.exception.NotFoundException;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/product")
public class ProductController implements BaseRequest {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<ProductDto>> find(@PathVariable Long id, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), Collections.singletonList(service.read(id)));
    }

    @CrossOrigin
    @GetMapping(params = {"pageIndex", "pageSize"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<ProductDto>> findAll(int pageIndex, int pageSize, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), service.readAll(pageIndex, pageSize));
    }

    @CrossOrigin
    @GetMapping(value = "/cat/{categoryId}", params = {"pageIndex", "pageSize"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<ProductDto>> findAllByCategory(@PathVariable Long categoryId, int pageIndex, int pageSize, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), service.readAllByCategory(categoryId, pageIndex, pageSize));
    }

    @CrossOrigin
    @GetMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<ProductDto>> findAllNew(ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), service.readAllNew());
    }

    @CrossOrigin
    @GetMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<ProductDto>> findAllPopular(ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), service.readAllPopular());
    }
}
