package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enumaration.InvoiceStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Long id;
    private String addDate;
    private String paymentDate;
    private InvoiceStatus status;
    private UserDto user;
    private List<InvoiceItemDto> items;
}
