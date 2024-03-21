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

    public Map<String,String> validate(CustomerDTO createUpdateTeacherDTO) {
        Map<String,String> errors = new HashMap<>();

        return errors;
    }
}
