package org.example.configurer;

import org.example.dto.ProductCategoryDto;
import org.example.entity.ProductCategory;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class ProductCategoryConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(ProductCategory.class, ProductCategoryDto.class)
                .addMappings(m -> {
                    m.map(ProductCategory::getId, ProductCategoryDto::setId);
                    m.map(ProductCategory::getImage, ProductCategoryDto::setImage);
                    m.map(ProductCategory::getTitle, ProductCategoryDto::setTitle);
                });

        mapper.typeMap(ProductCategoryDto.class, ProductCategory.class)
                .addMappings(m -> {
                    m.map(ProductCategoryDto::getId, ProductCategory::setId);
                    m.map(ProductCategoryDto::getImage, ProductCategory::setImage);
                    m.map(ProductCategoryDto::getTitle, ProductCategory::setTitle);
                });
    }
}
