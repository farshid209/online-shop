package org.example.framework.controllers.requests;

import jakarta.servlet.ServletRequest;
import org.example.framework.controllers.BaseRequest;
import org.example.framework.controllers.ServiceResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreateRequest<ID, Dto> extends BaseRequest {
    @CrossOrigin
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponse<Dto>> add(@RequestBody Dto dto, ServletRequest request);
}
