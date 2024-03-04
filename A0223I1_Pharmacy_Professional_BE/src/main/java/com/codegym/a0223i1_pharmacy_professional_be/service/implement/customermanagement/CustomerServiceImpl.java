package com.codegym.a0223i1_pharmacy_professional_be.service.implement.customermanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement.ICustomerRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;


    @Override
    public Page<Customer> getAllCustomer(String find, Pageable pageable) {
        return iCustomerRepository.getAllCustomer(pageable);
    }

    @Override

    public Page<Customer> getAllCustomerByName(String customerName, Pageable pageable) {
        return iCustomerRepository.getAllCustomerByName(customerName,pageable);
    }

    @Override
    public Page<Customer> getAllCustomerByAge(Integer customerAge, Pageable pageable) {
        return iCustomerRepository.getAllCustomerByAge(customerAge,pageable);
    }
    @Override
    public Page<Customer> getAllCustomerByType(String customerType, Pageable pageable) {
        return iCustomerRepository.getAllCustomerByType(customerType,pageable);
    }

    @Override
    public void deleteCustomer(String id) {
        iCustomerRepository.deleteCustomer(id);
    }

    @Override
    public List<IInvoiceDTO> getAllInvoiceCustomer(String id) {
        return iCustomerRepository.getAllInvoiceCustomer(id);
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        iCustomerRepository.createCustomer(customerDTO.getCustomerId(), customerDTO.getCustomerName(), customerDTO.getAge(),
                customerDTO.getAddress(), customerDTO.getPhoneNumber(),
                customerDTO.getCustomerType(), customerDTO.getNote(), customerDTO.getAccountId());
    }


    @Override
    public Customer getCustomerById(String customerId) {
        return iCustomerRepository.getCustomerById(customerId);
    }

    @Override
    public Customer findCustomerById(String customerId) {
        return iCustomerRepository.findCustomerById(customerId);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        iCustomerRepository.updateCustomer(customerDTO.getCustomerName(), customerDTO.getAge(),
                customerDTO.getAddress(), customerDTO.getPhoneNumber(),
                customerDTO.getCustomerType(), customerDTO.getNote(), customerDTO.getCustomerId());
    }
}

