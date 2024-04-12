package org.example.configurer;

import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.modelmapper.ModelMapper;

@MapperConfiguration
public class ProductConfigurer implements MapperConfigurer {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Product.class, ProductDto.class)
                .addMappings(m -> {
                    m.map(Product::getId, ProductDto::setId);
                    m.map(Product::getAddDate, ProductDto::setAddDate);
                    m.map(Product::getDescription, ProductDto::setDescription);
                    m.map(Product::getImage, ProductDto::setImage);
                    m.map(Product::getPrice, ProductDto::setPrice);
                    m.map(Product::getTitle, ProductDto::setTitle);
                    m.map(Product::getVisitCount, ProductDto::setVisitCount);
                    m.map(Product::getCategory, ProductDto::setCategory);
                    m.map(Product::getColors, ProductDto::setColors);
                    m.map(Product::getSizes, ProductDto::setSizes);
                });

        mapper.typeMap(ProductDto.class, Product.class)
                .addMappings(m -> {
                    m.map(ProductDto::getId, Product::setId);
                    m.map(ProductDto::getAddDate, Product::setAddDate);
                    m.map(ProductDto::getDescription, Product::setDescription);
                    m.map(ProductDto::getImage, Product::setImage);
                    m.map(ProductDto::getPrice, Product::setPrice);
                    m.map(ProductDto::getTitle, Product::setTitle);
                    m.map(ProductDto::getVisitCount, Product::setVisitCount);
                    m.map(ProductDto::getCategory, Product::setCategory);
                    m.map(ProductDto::getColors, Product::setColors);
                    m.map(ProductDto::getSizes, Product::setSizes);
                });
    }
}
