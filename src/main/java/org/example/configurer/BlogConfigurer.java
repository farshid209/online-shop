package org.example.configurer;

import org.example.dto.BlogDto;
import org.example.entity.Blog;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class BlogConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Blog.class, BlogDto.class)
                .addMappings(m -> {
                    m.map(Blog::getId, BlogDto::setId);
                    m.map(Blog::getAddDate, BlogDto::setAddDate);
                    m.map(Blog::getDescription, BlogDto::setDescription);
                    m.map(Blog::getImage, BlogDto::setImage);
                    m.map(Blog::getSubTitle, BlogDto::setSubTitle);
                    m.map(Blog::getTitle, BlogDto::setTitle);
                    m.map(Blog::getVisitCount, BlogDto::setVisitCount);
                });

        mapper.typeMap(BlogDto.class, Blog.class)
                .addMappings(m -> {
                    m.map(BlogDto::getId, Blog::setId);
                    m.map(BlogDto::getAddDate, Blog::setAddDate);
                    m.map(BlogDto::getDescription, Blog::setDescription);
                    m.map(BlogDto::getImage, Blog::setImage);
                    m.map(BlogDto::getSubTitle, Blog::setSubTitle);
                    m.map(BlogDto::getTitle, Blog::setTitle);
                    m.map(BlogDto::getVisitCount, Blog::setVisitCount);
                });
    }
}
