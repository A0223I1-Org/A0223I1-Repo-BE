package com.codegym.a0223i1_pharmacy_professional_be.service.implement.supplier;

import com.codegym.a0223i1_pharmacy_professional_be.dto.SupplierDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import com.codegym.a0223i1_pharmacy_professional_be.repository.supplier.ISupplierRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.supplier.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    ISupplierRepository supplierRepository;


    @Override
    public Page<Supplier> findAllOrderBySupplierId(Pageable pageable) {
        return supplierRepository.findAllOrderBySupplierId(pageable);
    }

    @Override
    public Page<Supplier> findAllOrderBySupplierName(Pageable pageable) {
        return supplierRepository.findAllOrderBySupplierName(pageable);
    }

    @Override
    public Page<Supplier> findAllOrderByAddress(Pageable pageable) {
        return supplierRepository.findAllOrderByAddress(pageable);
    }

    @Override
    public Page<Supplier> findAllOrderByPhoneNumber(Pageable pageable) {
        return supplierRepository.findAllOrderByPhoneNumber(pageable);
    }

    @Override
    public Page<Supplier> findAllBySupplierId(String id, Pageable pageable) {
        return supplierRepository.findAllBySupplierId(id, pageable);
    }

    @Override
    public Page<Supplier> findAllBySupplierName(String supplierName, Pageable pageable) {
        return supplierRepository.findAllBySupplierName(supplierName, pageable);
    }

    @Override
    public Page<Supplier> findAllByAddress(String address, Pageable pageable) {
        return supplierRepository.findAllByAddress(address, pageable);
    }

    @Override
    public Page<Supplier> findAllByPhoneNumber(String phoneNumber, Pageable pageable) {
        return supplierRepository.findAllByPhoneNumber(phoneNumber, pageable);
    }

    @Override
    public Supplier findSupplierById(String id) {
        return supplierRepository.findSupplierById(id);
    }

    @Override
    public void deleteById(String id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public void addNewSupplier(SupplierDTO supplierDTO) {
        supplierRepository.addNewSupplier(supplierDTO.getSupplierId(), supplierDTO.getSupplierName(), supplierDTO.getAddress(), supplierDTO.getEmail(), supplierDTO.getPhoneNumber(), supplierDTO.getNote(), 0,0);
    }

    @Override
    public void editSupplier(SupplierDTO supplierDTO, String oldId) {
        Supplier existingSupplier = supplierRepository.findSupplierById(oldId);
        if (existingSupplier != null) {
            supplierRepository.editSupplier(supplierDTO.getSupplierId(), supplierDTO.getSupplierName(), supplierDTO.getAddress(), supplierDTO.getEmail(), supplierDTO.getPhoneNumber(), supplierDTO.getNote(), Boolean.compare(supplierDTO.getDeleteFlag(), false), supplierDTO.getToPayDebt(), oldId);
        }
    }
}
