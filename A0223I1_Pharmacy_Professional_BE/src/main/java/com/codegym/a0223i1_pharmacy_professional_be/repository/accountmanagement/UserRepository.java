package com.codegym.a0223i1_pharmacy_professional_be.repository.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Role;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
    Account findByRole(Role role);
}
