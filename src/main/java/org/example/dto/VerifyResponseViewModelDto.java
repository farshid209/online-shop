package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyResponseViewModelDto {
    private Integer code;
    private Long invoiceNumber;
    private String referenceId;
    private String status;
}
