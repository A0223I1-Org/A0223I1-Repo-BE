package com.codegym.a0223i1_pharmacy_professional_be.validate;

import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomerUpdateValidate {
    @Autowired
    private ICustomerRepository customerRepository;

    private final Pattern PATTERN_NAME = Pattern.compile("^[a-zA-Z\\s]+$");
    private final Pattern PATTERN_ADDRESS = Pattern.compile("^[a-zA-Z0-9\\s.,#-]+$");
    private final Pattern PATTERN_PHONE = Pattern.compile("^[0-9]+$");

    public Map<String,String> validate(CustomerDTO updateCustomerDTO) {
        Map<String,String> errors = new HashMap<>();
        if (updateCustomerDTO.getCustomerName() == null || updateCustomerDTO.getCustomerName().isEmpty()) {
            errors.put("errorNameEmpty","Tên khách hàng không được trống");
        }
        if (updateCustomerDTO.getCustomerName().length() > 50) {
            errors.put("errorNameLength","Tên không được quá 50 ký tự");
        }
        if (!PATTERN_NAME.matcher(updateCustomerDTO.getCustomerName()).matches()) {
            errors.put("errorNameSpecialCharacter","Tên không được chứa các kí tự đặc biệt");
        }

        int age =updateCustomerDTO.getAge();
        if (age < 18) {
            errors.put("errorDateMin","Khách hàng không được nhỏ hơn 18 tuổi");
        }

        if (updateCustomerDTO.getPhoneNumber() == null || updateCustomerDTO.getPhoneNumber().isEmpty()) {
            errors.put("errorPhoneEmpty","Số điện thoại không được để trống");
        }
        if (updateCustomerDTO.getPhoneNumber().length() != 10) {
            errors.put("errorPhoneLength","Số điện thoại phải có 10 ký tự");
        }
        if (!PATTERN_PHONE.matcher(updateCustomerDTO.getPhoneNumber()).matches()) {
            errors.put("errorPhoneNumber","Số điện thoại chỉ được chứa số");
        }

        if (updateCustomerDTO.getAddress() == null || updateCustomerDTO.getAddress().isEmpty()) {
            errors.put("errorEmailEmpty","Địa chỉ không được để trống");
        }
        if (!PATTERN_ADDRESS.matcher(updateCustomerDTO.getAddress()).matches()) {
            errors.put("errorEmailFormat","Địa chỉ không hợp lệ");
        }


        Customer customer= customerRepository.getCustomerByIdd(updateCustomerDTO.getCustomerId());
        List<CustomerDTO> customers = customerRepository.getCustomersByPhoneUpdate(customer.getPhoneNumber(),updateCustomerDTO.getPhoneNumber());
        if (!customers.isEmpty()) {
            errors.put("errorPhoneDuplicate","Số điện thoại đã tồn tại");
        }

        return errors;
    }
}
