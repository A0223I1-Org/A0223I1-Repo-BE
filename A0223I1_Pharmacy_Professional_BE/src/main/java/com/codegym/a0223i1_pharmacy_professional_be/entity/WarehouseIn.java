package com.codegym.a0223i1_pharmacy_professional_be.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse_in")
public class WarehouseIn {
    @Id
    @Column(name = "warehouse_in_id")
    private String warehouseInId;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    private Timestamp createDate;

    private String paymentVoucher;

    private String status;

    private Float pharmacyPay;

    private Float totalAmount;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "warehouseIn", cascade = CascadeType.ALL)
    List<WarehouseInDetail> warehouseInDetails;
}
