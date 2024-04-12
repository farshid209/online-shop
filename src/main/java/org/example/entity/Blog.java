package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "add_date")
    private String addDate;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "title")
    private String title;

    @Column(name = "visit_count")
    private Integer visitCount;
}
