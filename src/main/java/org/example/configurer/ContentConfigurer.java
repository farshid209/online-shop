package org.example.configurer;

import org.example.dto.ContentDto;
import org.example.entity.Content;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class ContentConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Content.class, ContentDto.class)
                .addMappings(m -> {
                    m.map(Content::getId, ContentDto::setId);
                    m.map(Content::getTitle, ContentDto::setTitle);
                    m.map(Content::getDescription, ContentDto::setDescription);
                });

        mapper.typeMap(ContentDto.class, Content.class)
                .addMappings(m -> {
                    m.map(ContentDto::getId, Content::setId);
                    m.map(ContentDto::getTitle, Content::setTitle);
                    m.map(ContentDto::getDescription, Content::setDescription);
                });
    }
}
