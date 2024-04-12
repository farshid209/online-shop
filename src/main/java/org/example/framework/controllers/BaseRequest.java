package org.example.framework.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseRequest {
    default <T> ResponseEntity<ServiceResponse<T>> toResponseEntity(String status, List<T> result) {
        return ResponseEntity.ok().body(
                ServiceResponse.<T>builder()
                        .status(status)
                        .totalCount((long) result.size())
                        .data(result).build());
    }
}