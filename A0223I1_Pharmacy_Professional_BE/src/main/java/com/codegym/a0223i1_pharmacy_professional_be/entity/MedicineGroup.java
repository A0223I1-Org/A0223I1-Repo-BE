package com.codegym.a0223i1_pharmacy_professional_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicine_group")
public class MedicineGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_group_id")
    private int medicineGroupId;

    private String medicineGroupName;

    @OneToMany(mappedBy = "medicineGroup", cascade = CascadeType.ALL)
    Set<Medicine> medicines;
}