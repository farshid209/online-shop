package org.example.configurer;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class UserConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(User.class, UserDto.class)
                .addMappings(m -> {
                    m.map(User::getId, UserDto::setId);
                    m.map(User::getUsername, UserDto::setUsername);
                    m.map(User::getCustomer, UserDto::setCustomer);
                    m.skip(UserDto::setPassword);
                });

        mapper.typeMap(UserDto.class, User.class)
                .addMappings(m -> {
                    m.map(UserDto::getId, User::setId);
                    m.map(UserDto::getUsername, User::setUsername);
                    m.map(UserDto::getPassword, User::setPassword);
                    m.map(UserDto::getCustomer, User::setCustomer);
                });
    }
}
