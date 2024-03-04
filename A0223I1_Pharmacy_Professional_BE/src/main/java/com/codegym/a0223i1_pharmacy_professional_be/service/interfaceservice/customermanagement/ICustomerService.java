package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Invoice;
import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICustomerService {
    Customer getCustomerById(String id);
    Page<Customer> getAllCustomer(String find, Pageable pageable);
    void deleteCustomer(String id);

    List<IInvoiceDTO> getAllInvoiceCustomer(String id);

    void createCustomer(CustomerDTO customerDTO);
  
   // Customer getCustomerById(String customerId);
  
    Customer findCustomerById(String customerId);
  
    void updateCustomer(CustomerDTO customerDTO);
  
}


