package com.codegym.a0223i1_pharmacy_professional_be.validate;

import com.codegym.a0223i1_pharmacy_professional_be.dto.SupplierDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import com.codegym.a0223i1_pharmacy_professional_be.repository.supplier.ISupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class SupplierValidate {
    @Autowired
    private ISupplierRepository supplierRepository;

    private final Pattern PATTERN_ID = Pattern.compile("^[a-zA-Z0-9\\s]+$");
    private final Pattern PATTERN_NAME = Pattern.compile("^[a-zA-Z0-9\\s]+$");
    private final Pattern PATTERN_ADDRESS = Pattern.compile("^[a-zA-Z\\s]+$");
    private final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    private final Pattern PATTERN_PHONE = Pattern.compile("^[0-9]+$");

    public boolean isSupplierIdUnique(String supplierId) {
        Supplier existingSupplier = supplierRepository.findSupplierById(supplierId);
        return existingSupplier == null;
    }

    public Map<String,String> validate(SupplierDTO supplierDTO) {
        Map<String,String> errors = new HashMap<>();

        // Kiểm tra sự tồn tại và độ dài của SupplierId
        if (supplierDTO.getSupplierId() == null || supplierDTO.getSupplierId().isEmpty()) {
            errors.put("errorIdEmpty","Mã nhà cung cấp không được trống!");
        } else if (supplierDTO.getSupplierId().length() > 50) {
            errors.put("errorIdLength","Mã nhà cung cấp không được quá 50 ký tự");
        } else if (!PATTERN_ID.matcher(supplierDTO.getSupplierId()).matches()) {
            errors.put("errorIdSpecialCharacter","Mã nhà cung cấp không được chứa các kí tự đặc biệt");
        } else if (!isSupplierIdUnique(supplierDTO.getSupplierId())) {
            errors.put("errorIdNotUnique","Mã nhà cung cấp đã tồn tại");
        }

        if (supplierDTO.getSupplierName() == null || supplierDTO.getSupplierName().isEmpty()) {
            errors.put("errorNameEmpty","Tên nhà cung cấp không được trống!");
        }
        if (supplierDTO.getSupplierName().length() > 50) {
            errors.put("errorNameLength","Tên nhà cung cấp không được quá 50 ký tự");
        }
        if (!PATTERN_NAME.matcher(supplierDTO.getSupplierName()).matches()) {
            errors.put("errorNameSpecialCharacter","Tên nhà cung cấp không được chứa các kí tự đặc biệt");
        }


        if (supplierDTO.getPhoneNumber().length() > 11) {
            errors.put("errorPhoneLength","Số điện thoại không được quá 11 ký tự");
        }
        if (!PATTERN_PHONE.matcher(supplierDTO.getPhoneNumber()).matches()) {
            errors.put("errorPhoneNumber","Số điện thoại chỉ được chứa số");
        }


        if (supplierDTO.getAddress().length() > 50) {
            errors.put("errorAddressLength","Địa chỉ không được quá 50 ký tự");
        }
        if (!PATTERN_ADDRESS.matcher(supplierDTO.getAddress()).matches()) {
            errors.put("errorAddressFormat","Địa chỉ không hợp lệ");
        }


        if (supplierDTO.getEmail().length() > 50) {
            errors.put("errorEmailLength","Email không được quá 50 ký tự");
        }
        if (!PATTERN_EMAIL.matcher(supplierDTO.getEmail()).matches()) {
            errors.put("errorEmailFormat","Email không hợp lệ");
        }

        if (supplierDTO.getNote().length() > 25) {
            errors.put("errorNoteLength","Ghi chú không được quá 25 ký tự");
        }
        return errors;
    }
}
