package org.example.controller;

import jakarta.servlet.ServletRequest;
import org.example.dto.PaymentViewModelDto;
import org.example.dto.VerifyResponseViewModelDto;
import org.example.enumaration.ResponseStatus;
import org.example.framework.controllers.BaseRequest;
import org.example.framework.controllers.ServiceResponse;
import org.example.framework.exception.NotFoundException;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;

@RestController
@RequestMapping("/api/trx")
public class TransactionController implements BaseRequest {
    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/goToPayment")
    public ResponseEntity<ServiceResponse<String>> pay(@RequestBody PaymentViewModelDto dto) {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), Collections.singletonList(service.pay(dto)));
    }

    @CrossOrigin
    @GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<VerifyResponseViewModelDto>> verify(@RequestParam BigDecimal amount, @RequestParam("order_id") String orderId, @RequestParam("trans_id") String transId, ServletRequest request) throws NotFoundException {
        return toResponseEntity(ResponseStatus.ACCEPTED.getDescription(), Collections.singletonList(service.verify(amount, orderId, transId)));
    }
}
