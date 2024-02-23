package com.codegym.a0223i1_pharmacy_professional_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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

    private String supplierName;

    private String address;

    private String email;

    private String phoneNumber;

    private int toPayDebt;

    private boolean deleteFlag;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    List<WarehouseIn> warehouseIns;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    List<WarehouseOut> warehouseOuts;
}
