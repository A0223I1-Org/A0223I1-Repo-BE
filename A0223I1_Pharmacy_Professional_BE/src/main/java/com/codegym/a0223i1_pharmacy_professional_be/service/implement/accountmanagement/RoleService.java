package com.codegym.a0223i1_pharmacy_professional_be.service.implement.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Role;
import com.codegym.a0223i1_pharmacy_professional_be.repository.accountmanagement.IRoleRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepository iRoleRepository;
    @Override
    public Role findById(Long roleId) {
        return iRoleRepository.findById(roleId).orElse(null);
    }
}
