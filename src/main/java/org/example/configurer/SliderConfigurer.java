package org.example.configurer;

import org.example.dto.SliderDto;
import org.example.entity.Slider;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class SliderConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Slider.class, SliderDto.class)
                .addMappings(m -> {
                    m.map(Slider::getId, SliderDto::setId);
                    m.map(Slider::getTitle, SliderDto::setTitle);
                    m.map(Slider::getSubTitle, SliderDto::setSubTitle);
                    m.map(Slider::getImage, SliderDto::setImage);
                    m.map(Slider::getLink, SliderDto::setLink);
                });

        mapper.typeMap(SliderDto.class, Slider.class)
                .addMappings(m -> {
                    m.map(SliderDto::getId, Slider::setId);
                    m.map(SliderDto::getTitle, Slider::setTitle);
                    m.map(SliderDto::getSubTitle, Slider::setSubTitle);
                    m.map(SliderDto::getImage, Slider::setImage);
                    m.map(SliderDto::getLink, Slider::setLink);
                });
    }
}
