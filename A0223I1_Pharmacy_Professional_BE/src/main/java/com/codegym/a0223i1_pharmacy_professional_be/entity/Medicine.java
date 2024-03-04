package com.codegym.a0223i1_pharmacy_professional_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @Column(name = "medicine_id")
    private String medicineId;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "medicine_group_id", referencedColumnName = "medicine_group_id")
    private MedicineGroup medicineGroup;

    private String medicineName;

    private String material;

    private String activeIngredient;

    private String unit;
    private Integer conversionRate;

    private String conversionUnit;

    private int quantity;

    private Float importPrice;

    private Float wholesalePrice;

    private Float retailPrice;


    private Float supplierDiscount;

    private Float profitMarginWholesale;

    private Float profitMarginRetail;


    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    @JsonBackReference
    private Supplier supplier;

    private String origin;

    private String status;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    @JsonBackReference
    List<MedicineImg> medicineImgs;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    @JsonBackReference
    List<CartDetail> cartDetails;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    @JsonBackReference
    List<InvoiceDetail> invoiceDetails;

}
