package com.codegym.a0223i1_pharmacy_professional_be.service.implement.customermanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement.ICustomerRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        customerRepository.createCustomer(customerDTO.getCustomerId(),customerDTO.getCustomerName(),customerDTO.getAge(),
                                        customerDTO.getAddress(),customerDTO.getPhoneNumber(),
                                        customerDTO.getCustomerType(),customerDTO.getNote(),customerDTO.getAccountId());
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    @Override
    public Customer findCustomerById(String customerId) {
        return customerRepository.findCustomerById(customerId);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepository.updateCustomer(customerDTO.getCustomerName(),customerDTO.getAge(),
                customerDTO.getAddress(),customerDTO.getPhoneNumber(),
                customerDTO.getCustomerType(),customerDTO.getNote(),customerDTO.getAccountId(), customerDTO.getCustomerId());
    }
}
