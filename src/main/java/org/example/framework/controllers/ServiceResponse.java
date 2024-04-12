package org.example.framework.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<T> implements Serializable {
    private List<T> data;

    @JsonInclude(Include.NON_NULL)
    private String message;

    @JsonInclude(Include.NON_NULL)
    private String status;

    @JsonInclude(Include.NON_NULL)
    private Long totalCount;
}
