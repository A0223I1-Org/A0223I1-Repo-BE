package com.codegym.a0223i1_pharmacy_professional_be.entity;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Data
@Entity
@Table(name = "user")
public class Account implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String password;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
=======
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
>>>>>>> cadb75df27bcffe44a1ed6b5fe475ac70486b02a
}
