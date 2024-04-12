package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentViewModelDto {
    private Long id;
    private List<InvoiceItemDto> items;
    private UserViewModelDto user;
}
