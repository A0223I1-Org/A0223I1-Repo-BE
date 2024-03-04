package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.AccountRole;

import java.util.List;

public interface IAccountRoleService {
    List<AccountRole> findAll();
    void save(AccountRole accountRole);
    AccountRole findById(Long accountRoleId);
    void delete(Long accountRoleId);
}
