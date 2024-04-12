package org.example.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "payment_view_model")
public class PaymentViewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    private List<InvoiceItem> items;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_view_model_id", nullable = false)
    private UserViewModel user;

}
