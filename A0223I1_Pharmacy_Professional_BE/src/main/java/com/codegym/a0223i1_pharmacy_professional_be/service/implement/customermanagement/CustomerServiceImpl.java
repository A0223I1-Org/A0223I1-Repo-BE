package com.codegym.a0223i1_pharmacy_professional_be.service.implement.customermanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Invoice;
import com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement.ICustomerRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;

   @Autowired
    private ICustomerRepository customerRepository;
  
    @Override
    public Customer getCustomerById(String id) {
        return iCustomerRepository.getCustomerById(id);
    }

    @Override
    public Page<Customer> getAllCustomer( String find, Pageable pageable) {
        return iCustomerRepository.getAllCustomer( pageable);
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

