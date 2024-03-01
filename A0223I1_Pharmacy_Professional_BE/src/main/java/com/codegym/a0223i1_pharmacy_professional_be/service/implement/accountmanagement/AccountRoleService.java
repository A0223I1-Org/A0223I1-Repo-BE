package com.codegym.a0223i1_pharmacy_professional_be.service.implement.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.AccountRole;
import com.codegym.a0223i1_pharmacy_professional_be.repository.accountmanagement.IAccountRoleRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.IAccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountRoleService implements IAccountRoleService {
    @Autowired
    IAccountRoleRepository iAccountRoleRepository;
    @Override
    public List<AccountRole> findAll() {
        return iAccountRoleRepository.findAll();
    }

    @Override
    public void save(AccountRole accountRole) {
        iAccountRoleRepository.save(accountRole);
    }

    @Override
    public AccountRole findById(Long accountRoleId) {
        return iAccountRoleRepository.findById(accountRoleId).orElse(null);
    }

    @Override
    public void delete(Long accountRoleId) {
        iAccountRoleRepository.deleteById(accountRoleId);
    }
}
