package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "add_date")
    private String addDate;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "title")
    private String title;

    @Column(name = "visit_count")
    private Integer visitCount;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Color> colors;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Size> sizes;
}
