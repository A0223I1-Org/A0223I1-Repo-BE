package com.codegym.a0223i1_pharmacy_professional_be.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id")
    private String employeeId;

    private String employeeName;

    private String phoneNumber;

    private Date dateStart;

    private String address;

    private String note;

    private int salary;

    @JsonBackReference(value = "account")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @OneToMany(mappedBy = "employee")
    Set<Invoice> invoices;

    @OneToMany(mappedBy = "employee")
    Set<WarehouseIn> warehouseIns;

    @OneToMany(mappedBy = "employee")
    Set<WarehouseOut> warehouseOuts;
}
