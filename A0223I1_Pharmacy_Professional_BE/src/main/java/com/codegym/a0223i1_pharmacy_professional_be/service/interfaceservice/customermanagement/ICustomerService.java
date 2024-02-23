package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;

public interface ICustomerService {
    void createCustomer(CustomerDTO customerDTO);
    Customer getCustomerById(String customerId);
    Customer findCustomerById(String customerId);
    void updateCustomer(CustomerDTO customerDTO);
}
