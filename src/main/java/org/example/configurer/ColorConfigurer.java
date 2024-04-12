package org.example.configurer;

import org.example.dto.ColorDto;
import org.example.entity.Color;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class ColorConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Color.class, ColorDto.class)
                .addMappings(m -> {
                    m.map(Color::getId, ColorDto::setId);
                    m.map(Color::getTitle, ColorDto::setTitle);
                    m.map(Color::getHexValue, ColorDto::setHexValue);
                });

        mapper.typeMap(ColorDto.class, Color.class)
                .addMappings(m -> {
                    m.map(ColorDto::getId, Color::setId);
                    m.map(ColorDto::getTitle, Color::setTitle);
                    m.map(ColorDto::getHexValue, Color::setHexValue);
                });
    }
}
