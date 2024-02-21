package com.codegym.a0223i1_pharmacy_professional_be.entity;
<<<<<<< HEAD

public enum Role {
    URER,
    ADMIN
=======
import jakarta.persistence.*;
import lombok.*;
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
    Set<AccountRole> accountRoles;

>>>>>>> cadb75df27bcffe44a1ed6b5fe475ac70486b02a
}
