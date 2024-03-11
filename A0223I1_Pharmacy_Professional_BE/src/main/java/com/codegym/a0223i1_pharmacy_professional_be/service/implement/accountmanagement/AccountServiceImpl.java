package com.codegym.a0223i1_pharmacy_professional_be.service.implement.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import com.codegym.a0223i1_pharmacy_professional_be.repository.accountmanagement.IAccountRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountRepository iAccountRepository;
    @Override
    public List<Account> findAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public void save(Account account) {
        iAccountRepository.save(account);
    }

    @Override
    public Account registerAccount(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public Account findById(Integer accountId) {
        return iAccountRepository.findById(accountId).orElse(null);
    }

    @Override
    public void delete(Integer accountId) {
        iAccountRepository.deleteById(accountId);
    }
}
