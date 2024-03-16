package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.SupplierDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.ISupplierRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    ISupplierRepository supplierRepository;


    @Override
    public Page<Supplier> findAllBySupplierId(String id, String supplierName, String address, String phoneNumber, Pageable pageable) {
        return supplierRepository.findAllBySupplierId(id, supplierName, address, phoneNumber, pageable);
    }

    @Override
    public Page<Supplier> findAllBySupplierName(String id, String supplierName, String address, String phoneNumber, Pageable pageable) {
        return supplierRepository.findAllBySupplierName(id, supplierName, address, phoneNumber, pageable);
    }

    @Override
    public Page<Supplier> findAllByAddress(String id, String supplierName, String address, String phoneNumber, Pageable pageable) {
        return supplierRepository.findAllByAddress(id, supplierName, address, phoneNumber, pageable);
    }

    @Override
    public Page<Supplier> findAllByPhoneNumber(String id, String supplierName, String address, String phoneNumber, Pageable pageable) {
        return supplierRepository.findAllByPhoneNumber(id, supplierName, address, phoneNumber, pageable);
    }

    @Override
    public Supplier findSupplierById(String id) {
        return supplierRepository.findSupplierById(id);
    }

    @Override
    public Page<Supplier> findAllSuppliers(String supplierId, String supplierName, String address, String phoneNumber, String orderBy, Pageable pageable) {
        return supplierRepository.findAllWithSortingAndFiltering(supplierId, supplierName, address, phoneNumber, orderBy, pageable);
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
            supplierRepository.editSupplier(supplierDTO.getSupplierId(), supplierDTO.getSupplierName(), supplierDTO.getAddress(), supplierDTO.getEmail(), supplierDTO.getPhoneNumber(), supplierDTO.getNote(), 0, supplierDTO.getToPayDebt(), oldId);
        }
    }
}