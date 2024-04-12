package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.enumaration.InvoiceStatus;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "add_date")
    private String addDate;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private InvoiceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> items;
}
