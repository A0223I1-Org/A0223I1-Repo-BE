package com.codegym.a0223i1_pharmacy_professional_be.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @Column(name = "invoice_id")
    private String invoiceId;

    private Integer status;
    private String note;

    @Column(name = "invoice_type")
    private String invoiceType;

    @Column(name = "date_create",columnDefinition = "DATETIME")
    private Timestamp dateCreate;

    private Float total;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    List<InvoiceDetail> invoiceDetails;
}
