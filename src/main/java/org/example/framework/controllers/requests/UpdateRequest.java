package org.example.framework.controllers.requests;

import jakarta.servlet.ServletRequest;
import org.example.framework.controllers.BaseRequest;
import org.example.framework.controllers.ServiceResponse;
import org.example.framework.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UpdateRequest<ID, Dto> extends BaseRequest {
    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponse<Dto>> update(@PathVariable ID id, @RequestBody Dto dto, ServletRequest request) throws NotFoundException;
}
