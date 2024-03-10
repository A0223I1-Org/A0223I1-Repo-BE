package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;

import java.util.List;

public interface IAccountService {
    Account registerAccount(Account account);
    List<Account> findAll();
    void save(Account account);
    Account findById(Integer accountId);
    void delete(Integer accountId);
}
