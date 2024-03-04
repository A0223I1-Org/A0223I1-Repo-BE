package com.codegym.a0223i1_pharmacy_professional_be.entity;


import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse_out")
public class WarehouseOut {
    @Id
    @Column(name = "warehouse_out_id")
    private String warehouseOutId;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    private Timestamp createDate;

    private String paymentVoucher;

    private String exportType;

    private String reason;

    private Float totalAmount;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "warehouseOut")
    List<WarehouseOutDetail> warehouseOutDetails;
}