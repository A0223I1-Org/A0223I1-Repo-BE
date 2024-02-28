package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.supplier;

import com.codegym.a0223i1_pharmacy_professional_be.dto.SupplierDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISupplierService {
    Page<Supplier> findAllOrderBySupplierId(Pageable pageable);

    Page<Supplier> findAllOrderBySupplierName(Pageable pageable);

    Page<Supplier> findAllOrderByAddress(Pageable pageable);

    Page<Supplier> findAllOrderByPhoneNumber(Pageable pageable);

    Page<Supplier> findAllBySupplierId(String id, Pageable pageable);

    Page<Supplier> findAllBySupplierName(String supplierName, Pageable pageable);

    Page<Supplier> findAllByAddress(String address, Pageable pageable);

    Page<Supplier> findAllByPhoneNumber(String phoneNumber, Pageable pageable);

    Supplier findSupplierById(String id);

    void deleteById(String id);

    void addNewSupplier(SupplierDTO supplierDTO);

    void editSupplier(SupplierDTO supplierDTO, String oldId);


}
