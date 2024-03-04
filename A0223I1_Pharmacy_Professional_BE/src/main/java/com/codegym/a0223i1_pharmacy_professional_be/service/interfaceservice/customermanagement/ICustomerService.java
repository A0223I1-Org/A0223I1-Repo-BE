package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICustomerService {
    Page<Customer> getAllCustomer(String find, Pageable pageable);
    Page<Customer> getAllCustomerByName(String name, Pageable pageable);
    Page<Customer> getAllCustomerByType(String customerType, Pageable pageable);
    Page<Customer> getAllCustomerByAge(Integer age, Pageable pageable);
    void deleteCustomer(String id);

    List<IInvoiceDTO> getAllInvoiceCustomer(String id);

    void createCustomer(CustomerDTO customerDTO);

    Customer getCustomerById(String customerId);

    Customer findCustomerById(String customerId);

    void updateCustomer(CustomerDTO customerDTO);

}


