package org.example.configurer;

import org.example.dto.InvoiceDto;
import org.example.entity.Invoice;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class InvoiceConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Invoice.class, InvoiceDto.class)
                .addMappings(m -> {
                    m.map(Invoice::getId, InvoiceDto::setId);
                    m.map(Invoice::getAddDate, InvoiceDto::setAddDate);
                    m.map(Invoice::getPaymentDate, InvoiceDto::setPaymentDate);
                    m.map(Invoice::getStatus, InvoiceDto::setStatus);
                    m.map(Invoice::getUser, InvoiceDto::setUser);
                    m.map(Invoice::getItems, InvoiceDto::setItems);
                });

        mapper.typeMap(InvoiceDto.class, Invoice.class)
                .addMappings(m -> {
                    m.map(InvoiceDto::getId, Invoice::setId);
                    m.map(InvoiceDto::getAddDate, Invoice::setAddDate);
                    m.map(InvoiceDto::getPaymentDate, Invoice::setPaymentDate);
                    m.map(InvoiceDto::getStatus, Invoice::setStatus);
                    m.map(InvoiceDto::getUser, Invoice::setUser);
                    m.map(InvoiceDto::getItems, Invoice::setItems);
                });
    }
}
