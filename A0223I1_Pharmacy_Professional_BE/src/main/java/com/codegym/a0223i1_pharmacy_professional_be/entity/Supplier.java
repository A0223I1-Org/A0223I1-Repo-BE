package com.codegym.a0223i1_pharmacy_professional_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "supplier_id")
    private String supplierId;

    @Column(columnDefinition = "TEXT")
    private String supplierName;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String email;

    @Column(columnDefinition = "TEXT")
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String note;

    private int toPayDebt;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    Set<WarehouseIn> warehouseIns;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    Set<WarehouseOut> warehouseOuts;
}
