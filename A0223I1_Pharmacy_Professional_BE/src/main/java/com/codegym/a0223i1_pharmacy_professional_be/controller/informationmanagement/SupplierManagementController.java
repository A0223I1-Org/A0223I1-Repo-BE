package com.codegym.a0223i1_pharmacy_professional_be.controller.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.SupplierDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.supplier.ISupplierService;
import com.codegym.a0223i1_pharmacy_professional_be.validate.SupplierValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/suppliers")
public class SupplierManagementController {
    //Quản lý nhà cung cấp
    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private SupplierValidate supplierValidate;

    @GetMapping
    public ResponseEntity<Page<Supplier>> getAllSuppliers(@RequestParam(required = false) String orderBy,
                                                          @RequestParam(required = false) String searchType,
                                                          @RequestParam(required = false) String searchValue,
                                                          Pageable pageable) {
        Page<Supplier> suppliers;

        // Xác định phương thức tìm kiếm mặc định
        if (orderBy != null && !orderBy.isEmpty()) {
            switch (orderBy.toLowerCase()) {
                case "supplierName":
                    suppliers = supplierService.findAllOrderBySupplierName(pageable);
                    break;
                case "address":
                    suppliers = supplierService.findAllOrderByAddress(pageable);
                    break;
                case "phoneNumber":
                    suppliers = supplierService.findAllOrderByPhoneNumber(pageable);
                    break;
                default:
                    suppliers = supplierService.findAllOrderBySupplierId(pageable);
                    break;
            }
        } else {
            suppliers = supplierService.findAllOrderBySupplierId(pageable);
        }

        // Nếu có loại tìm kiếm và giá trị tìm kiếm, áp dụng tìm kiếm
        if (searchType != null && searchValue != null && !searchType.isEmpty() && !searchValue.isEmpty()) {
            switch (searchType.toLowerCase()) {
                case "supplierId":
                    suppliers = supplierService.findAllBySupplierId(searchValue, pageable);
                    break;
                case "supplierName":
                    suppliers = supplierService.findAllBySupplierName(searchValue, pageable);
                    break;
                case "address":
                    suppliers = supplierService.findAllByAddress(searchValue, pageable);
                    break;
                case "phoneNumber":
                    suppliers = supplierService.findAllByPhoneNumber(searchValue, pageable);
                    break;
                default:
                    suppliers = supplierService.findAllOrderBySupplierId(pageable);
                    break;
            }
        }

        // Nếu không có kết quả, trả về trang rỗng
        if (suppliers.isEmpty()) {
            suppliers = Page.empty();
        }

        return ResponseEntity.ok().body(suppliers);
    }


    @PostMapping("/addSupplier")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDTO supplierDTO) {
        if(supplierDTO == null){
            return new ResponseEntity<SupplierDTO>(HttpStatus.BAD_REQUEST);
        }
        else {
            Map<String,String> errors = supplierValidate.validate(supplierDTO);
            if(errors.isEmpty()){
                supplierService.addNewSupplier(supplierDTO);
                return new ResponseEntity<>(supplierDTO, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping( "/getSupplierById/{id}")
    public ResponseEntity<Supplier> findSupplierById(@PathVariable String id){
        Supplier supplier = supplierService.findSupplierById(id);
        if(supplier == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(supplier,HttpStatus.OK);
        }
    }

    @PostMapping("/updateSupplier/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable("id") String id, @RequestBody SupplierDTO supplierDTO){
        if(supplierService.findSupplierById(id) == null){
            return new ResponseEntity<>("không tìm thấy nhà cung cấp nào",HttpStatus.BAD_REQUEST);
        }
        else {
            Map<String,String> errors = supplierValidate.validate(supplierDTO);
            if(errors.isEmpty()){
                supplierService.editSupplier(supplierDTO, id);
                return new ResponseEntity<>(supplierDTO,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("id") String id) {
        Supplier supplier = supplierService.findSupplierById(id);
        if(supplier == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplierService.deleteById(id);
        return new ResponseEntity<>(supplier,HttpStatus.NO_CONTENT);

    }
}
