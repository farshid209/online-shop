package org.example.framework.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter {
    private int page;
    private int size;
    private String[] sort;
    private Map<String, Object> filters;

    @JsonIgnore
    public Sort getJpaSort() {
        if (sort == null || sort.length == 0) {
            return Sort.by(Sort.Direction.DESC, "id");
        }

        // handle multiple sort option
        List<Sort.Order> orders = new ArrayList<>();
        for (String sortOrder : sort) {
            String[] orderPart = sortOrder.split(":");
            if (orderPart.length == 2) {
                orders.add(new Sort.Order(getSortDirection(orderPart[1]), orderPart[0]));
            } else {
                orders.add(new Sort.Order(Sort.Direction.ASC, orderPart[0]));
            }
        }
        return Sort.by(orders);
    }

    @JsonIgnore
    private Sort.Direction getSortDirection(String s) {
        return s.contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
