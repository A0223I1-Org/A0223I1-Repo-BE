package com.codegym.a0223i1_pharmacy_professional_be.repository.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
    @Query(value = "insert into account(delete_flag,email,password) values (?1,?2,?3)",nativeQuery = true)
    Account save(Boolean deleteFlag,String email, String password);
}
