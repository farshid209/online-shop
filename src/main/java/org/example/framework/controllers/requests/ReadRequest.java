package org.example.framework.controllers.requests;

import jakarta.servlet.ServletRequest;
import org.example.framework.controllers.BaseRequest;
import org.example.framework.controllers.ServiceResponse;
import org.example.framework.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ReadRequest<ID, Dto> extends BaseRequest {
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponse<Dto>> find(@PathVariable ID id, ServletRequest request) throws NotFoundException;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponse<Dto>> findAll(ServletRequest request) throws NotFoundException;
}
