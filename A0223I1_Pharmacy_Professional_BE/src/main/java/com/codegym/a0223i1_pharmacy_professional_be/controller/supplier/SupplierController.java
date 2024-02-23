package com.codegym.a0223i1_pharmacy_professional_be.controller.supplier;


import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.supplier.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/allOrderBySupplierId")
    public ResponseEntity<Page<Supplier>> findAllOrderBySupplierId(Pageable pageable) {
        Page<Supplier> suppliers = supplierService.findAllOrderBySupplierId(pageable);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/allOrderBySupplierName")
    public ResponseEntity<Page<Supplier>> findAllOrderBySupplierName(Pageable pageable) {
        Page<Supplier> suppliers = supplierService.findAllOrderBySupplierName(pageable);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/allOrderByAddress")
    public ResponseEntity<Page<Supplier>> findAllOrderByAddress(Pageable pageable) {
        Page<Supplier> suppliers = supplierService.findAllOrderByAddress(pageable);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/allOrderByPhoneNumber")
    public ResponseEntity<Page<Supplier>> findAllOrderByPhoneNumber(Pageable pageable) {
        Page<Supplier> suppliers = supplierService.findAllOrderByPhoneNumber(pageable);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findSupplierById(@PathVariable String id) {
        Supplier supplier = supplierService.findSupplierById(id);
        if (supplier != null) {
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable String id) {
        supplierService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewSupplier(@RequestBody Supplier supplier) {
        supplierService.addNewSupplier(supplier.getSupplierId(), supplier.getSupplierName(), supplier.getAddress(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getNote());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editSupplier(@PathVariable String id, @RequestBody Supplier supplier) {
        supplierService.editSupplier(supplier.getSupplierId(), supplier.getSupplierName(), supplier.getAddress(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getNote(), id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
