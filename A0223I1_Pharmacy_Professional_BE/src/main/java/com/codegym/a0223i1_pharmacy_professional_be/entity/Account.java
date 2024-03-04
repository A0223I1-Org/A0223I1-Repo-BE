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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name="email", columnDefinition = "nvarchar(150)", unique = true)
    private String email;

    private String password;

    @Column(name="delete_flag")
    private Boolean deleteFlag;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    List<AccountRole> accountRoles;

    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "account")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "account")
    private Employee employee;
}
