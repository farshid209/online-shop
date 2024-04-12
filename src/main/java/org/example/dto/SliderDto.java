package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SliderDto {
    private Long id;
    private String title;
    private String subTitle;
    private String image;
    private String link;
}
