package org.example.configurer;

import org.example.dto.CustomerDto;
import org.example.entity.Customer;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class CustomerConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Customer.class, CustomerDto.class)
                .addMappings(m -> {
                    m.map(Customer::getId, CustomerDto::setId);
                    m.map(Customer::getFirstName, CustomerDto::setFirstName);
                    m.map(Customer::getLastName, CustomerDto::setLastName);
                    m.map(Customer::getPhone, CustomerDto::setPhone);
                    m.map(Customer::getAddress, CustomerDto::setAddress);
                    m.map(Customer::getPostalCode, CustomerDto::setPostalCode);
                });

        mapper.typeMap(CustomerDto.class, Customer.class)
                .addMappings(m -> {
                    m.map(CustomerDto::getId, Customer::setId);
                    m.map(CustomerDto::getFirstName, Customer::setFirstName);
                    m.map(CustomerDto::getLastName, Customer::setLastName);
                    m.map(CustomerDto::getPhone, Customer::setPhone);
                    m.map(CustomerDto::getAddress, Customer::setAddress);
                    m.map(CustomerDto::getPostalCode, Customer::setPostalCode);
                });
    }
}
