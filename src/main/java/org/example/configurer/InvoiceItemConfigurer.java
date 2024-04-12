package org.example.configurer;

import org.example.dto.InvoiceItemDto;
import org.example.entity.InvoiceItem;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class InvoiceItemConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(InvoiceItem.class, InvoiceItemDto.class)
                .addMappings(m -> {
                    m.map(InvoiceItem::getId, InvoiceItemDto::setId);
                    m.map(InvoiceItem::getQuantity, InvoiceItemDto::setQuantity);
                    m.map(InvoiceItem::getUnitPrice, InvoiceItemDto::setUnitPrice);
                    m.map(InvoiceItem::getProduct, InvoiceItemDto::setProduct);
                    m.map(InvoiceItem::getInvoice, InvoiceItemDto::setInvoice);
                });

        mapper.typeMap(InvoiceItemDto.class, InvoiceItem.class)
                .addMappings(m -> {
                    m.map(InvoiceItemDto::getId, InvoiceItem::setId);
                    m.map(InvoiceItemDto::getQuantity, InvoiceItem::setQuantity);
                    m.map(InvoiceItemDto::getUnitPrice, InvoiceItem::setUnitPrice);
                    m.map(InvoiceItemDto::getProduct, InvoiceItem::setProduct);
                    m.map(InvoiceItemDto::getInvoice, InvoiceItem::setInvoice);
                });
    }
}
