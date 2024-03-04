package com.codegym.a0223i1_pharmacy_professional_be.repository.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
}
