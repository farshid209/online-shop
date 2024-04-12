package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.*;
import org.example.enumaration.InvoiceStatus;
import org.example.enumaration.ResponseStatus;
import org.example.framework.exception.RequestRestrictedException;
import org.example.framework.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService implements BaseService {
    private static final Logger LOGGER = LogManager.getLogger(TransactionService.class);

    private final InvoiceService invoiceService;
    private final InvoiceItemService invoiceItemService;
    private final UserService userService;

    @Autowired
    public TransactionService(InvoiceService invoiceService,
                              InvoiceItemService invoiceItemService,
                              UserService userService) {
        this.invoiceService = invoiceService;
        this.invoiceItemService = invoiceItemService;
        this.userService = userService;
    }

    public String pay(PaymentViewModelDto dto) {
        UserDto userDto = userService.readByCustomer(dto.getUser().getCustomerId());

        InvoiceDto invoiceDto = InvoiceDto.builder()
                .addDate(LocalDate.now().toString())
                .user(userDto)
                .status(InvoiceStatus.NOT_PAYED)
                .build();

        invoiceDto.setId(invoiceService.create(invoiceDto).getId());
        dto.getItems().stream().peek(it -> it.setInvoice(invoiceDto)).forEach(invoiceItemService::create);

        BigDecimal amount = calculateInvoiceAmount(dto.getItems());

        //TODO: implement external payment gateway
        return "Link of payment gateway";
    }

    public VerifyResponseViewModelDto verify(BigDecimal amount, String orderId, String transId) {
        InvoiceDto invoiceDto = invoiceService.read(Long.valueOf(orderId));

        BigDecimal invoiceAmount = calculateInvoiceAmount(invoiceDto.getItems());
        if (amount.compareTo(invoiceAmount) != 0) {
            throw new RequestRestrictedException("Transaction can't be verified due to amount mismatch!", ResponseStatus.FAILURE.getDescription());
        }

        invoiceDto.setPaymentDate(LocalDate.now().toString());
        invoiceDto.setStatus(InvoiceStatus.PAYED);
        invoiceService.update(invoiceDto.getId(), invoiceDto);

        return VerifyResponseViewModelDto.builder()
                .code(InvoiceStatus.PAYED.getType())
                .invoiceNumber(invoiceDto.getId())
                .referenceId(transId)
                .status(InvoiceStatus.PAYED.getDescription())
                .build();
    }

    private BigDecimal calculateInvoiceAmount(List<InvoiceItemDto> items) {
        return items.stream()
                .map(it -> it.getUnitPrice().multiply(BigDecimal.valueOf(it.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
