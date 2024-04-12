package org.example.configurer;

import org.example.dto.SizeDto;
import org.example.entity.Size;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class SizeConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Size.class, SizeDto.class)
                .addMappings(m -> {
                    m.map(Size::getId, SizeDto::setId);
                    m.map(Size::getTitle, SizeDto::setTitle);
                });

        mapper.typeMap(SizeDto.class, Size.class)
                .addMappings(m -> {
                    m.map(SizeDto::getId, Size::setId);
                    m.map(SizeDto::getTitle, Size::setTitle);
                });
    }
}
