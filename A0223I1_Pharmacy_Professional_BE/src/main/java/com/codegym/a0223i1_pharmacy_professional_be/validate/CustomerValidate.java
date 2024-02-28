package com.codegym.a0223i1_pharmacy_professional_be.validate;

import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class CustomerValidate {
    @Autowired
    private ICustomerRepository customerRepository;

    private final Pattern PATTERN_NAME = Pattern.compile("^[a-zA-Z\\s]+$");
    private final Pattern PATTERN_ADDRESS = Pattern.compile("^[a-zA-Z0-9\\s.,#-]+$");
    private final Pattern PATTERN_PHONE = Pattern.compile("^[0-9]+$");

    public Map<String,String> validate(CustomerDTO createTeacherDTO) {
        Map<String,String> errors = new HashMap<>();
        if (createTeacherDTO.getCustomerName() == null || createTeacherDTO.getCustomerName().isEmpty()) {
            errors.put("errorNameEmpty","Tên khách hàng không được trống");
        }
        if (createTeacherDTO.getCustomerName().length() > 50) {
            errors.put("errorNameLength","Tên không được quá 50 ký tự");
        }
        if (!PATTERN_NAME.matcher(createTeacherDTO.getCustomerName()).matches()) {
            errors.put("errorNameSpecialCharacter","Tên không được chứa các kí tự đặc biệt");
        }

        int age =createTeacherDTO.getAge();
        if (age < 18) {
            errors.put("errorDateMin","Khách hàng không được nhỏ hơn 18 tuổi");
        }

        if (createTeacherDTO.getPhoneNumber() == null || createTeacherDTO.getPhoneNumber().isEmpty()) {
            errors.put("errorPhoneEmpty","Số điện thoại không được để trống");
        }
        if (createTeacherDTO.getPhoneNumber().length() != 10) {
            errors.put("errorPhoneLength","Số điện thoại phải có 10 ký tự");
        }
        if (!PATTERN_PHONE.matcher(createTeacherDTO.getPhoneNumber()).matches()) {
            errors.put("errorPhoneNumber","Số điện thoại chỉ được chứa số");
        }

        if (createTeacherDTO.getAddress() == null || createTeacherDTO.getAddress().isEmpty()) {
            errors.put("errorEmailEmpty","Địa chỉ không được để trống");
        }
        if (!PATTERN_ADDRESS.matcher(createTeacherDTO.getAddress()).matches()) {
            errors.put("errorEmailFormat","Địa chỉ không hợp lệ");
        }


        List<CustomerDTO> customers = customerRepository.getCustomersByPhone(createTeacherDTO.getPhoneNumber());
        if (!customers.isEmpty()) {
            errors.put("errorPhoneDuplicate","Số điện thoại đã tồn tại");
        }

        return errors;
    }
}
