package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String addDate;
    private String description;
    private String image;
    private BigDecimal price;
    private String title;
    private Integer visitCount;
    private ProductCategoryDto category;
    private List<ColorDto> colors;
    private List<SizeDto> sizes;
}
