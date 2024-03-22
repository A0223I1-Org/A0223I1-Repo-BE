package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.SupplierDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISupplierService {
    Page<Supplier> findAllBySupplierId(String id, String supplierName, String address, String phoneNumber, Pageable pageable);

    Page<Supplier> findAllBySupplierName(String id, String supplierName, String address, String phoneNumber, Pageable pageable);

    Page<Supplier> findAllByAddress(String id, String supplierName, String address, String phoneNumber, Pageable pageable);

    Page<Supplier> findAllByPhoneNumber(String id, String supplierName, String address, String phoneNumber, Pageable pageable);

    Supplier findSupplierById(String id);

    Page<Supplier> findAllSuppliers(String supplierId, String supplierName, String address, String phoneNumber, String orderBy, Pageable pageable);

    void deleteById(String id);

    void addNewSupplier(SupplierDTO supplierDTO);

    void editSupplier(SupplierDTO supplierDTO, String oldId);


}