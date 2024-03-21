package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICustomerService {
    Page<Customer> getAllCustomer(Pageable pageable);
    List<String> getAllCustomerIncludeDeleted();

    void deleteCustomer(String id);

    Page<IInvoiceDTO> getAllInvoiceCustomer(String id, String startDay, String endDay, String startHour, String endHour,Pageable pageable);

    void createCustomer(CustomerDTO customerDTO);

    Customer getCustomerById(String customerId);
    Page<Customer> getCustomerById(String customerId, Pageable pageable);

    Customer findCustomerById(String customerId);

    void updateCustomer(CustomerDTO customerDTO);

    List<String> getAllPhoneNumber();
    Page<Customer> getCustomerByAddress(String address, Pageable pageable);
    Page<Customer> getAllOrderByCustomerType(Pageable pageable);

    Page<Customer> getAllOrderByCustomerName(Pageable pageable);

    Page<Customer> getAllOrderByCustomerId(Pageable pageable);

    Page<Customer> getAllOrderByCustomerAddress(Pageable pageable);

    Page<Customer> getAllOrderByPhoneNumber(Pageable pageable);
    Page<Customer> getAllCustomerByName(String name, Pageable pageable);

    Page<Customer> getAllCustomerByType(String customerType, Pageable pageable);

    Page<Customer> getAllCustomerByPhoneNumber(String phoneNumber, Pageable pageable);
}


