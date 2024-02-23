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
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    List<AccountRole> accountRoles;

}
