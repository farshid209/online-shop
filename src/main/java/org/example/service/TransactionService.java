package org.example.service;

import org.example.dto.PaymentViewModelDto;
import org.example.dto.VerifyResponseViewModelDto;
import org.example.framework.services.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService implements BaseService {

    public String pay(PaymentViewModelDto dto) {
        return null;
    }

    public VerifyResponseViewModelDto verify(BigDecimal amount, String orderId, String transId) {
        return null;
    }
}
