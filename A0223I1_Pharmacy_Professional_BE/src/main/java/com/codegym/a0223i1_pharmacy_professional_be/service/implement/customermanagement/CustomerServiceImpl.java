package com.codegym.a0223i1_pharmacy_professional_be.service.implement.customermanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement.ICustomerRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Page<Customer> getAllCustomer(Pageable pageable) {
        return iCustomerRepository.getAllCustomer(pageable);
    }

    @Override
    public List<String> getAllCustomerIncludeDeleted() {
        return iCustomerRepository.getAllCustomerIncludeDeleted();
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

    @Override
    public List<String> getAllPhoneNumber() {
        return iCustomerRepository.getAllPhoneNumber();
    }

    @Override
    public Page<Customer> getAllOrderByCustomerType(Pageable pageable) {
        return iCustomerRepository.getAllOrderByCustomerType(pageable);
    }

    @Override
    public Page<Customer> getAllOrderByCustomerName(Pageable pageable) {
        return iCustomerRepository.getAllOrderByCustomerName(pageable);
    }

    @Override
    public Page<Customer> getAllOrderByCustomerId(Pageable pageable) {
        return iCustomerRepository.getAllOrderByCustomerId(pageable);
    }

    @Override
    public Page<Customer> getAllOrderByCustomerAddress(Pageable pageable) {
        return iCustomerRepository.getAllOrderByCustomerAddress(pageable);
    }

    @Override
    public Page<Customer> getAllOrderByPhoneNumber(Pageable pageable) {
        return iCustomerRepository.getAllOrderByPhoneNumber(pageable);
    }
    @Override
    public void deleteCustomer(String id) {
        iCustomerRepository.deleteCustomer(id);
    }

    @Override
    public Page<IInvoiceDTO> getAllInvoiceCustomer(String id, String startDay, String endDay, String startHour, String endHour,Pageable pageable) {
        return iCustomerRepository.getAllInvoiceCustomer(id,startDay,endDay,startHour,endHour,pageable);
    }
    @Override
    public Page<Customer> getCustomerByAddress(String address, Pageable pageable) {
        return iCustomerRepository.getAllCustomerByAddrerss(address, pageable);
    }

    @Override
    public Page<Customer> getAllCustomerByName(String customerName, Pageable pageable) {
        return iCustomerRepository.getAllCustomerByName(customerName, pageable);
    }

    @Override
    public Page<Customer> getAllCustomerByType(String customerType, Pageable pageable) {
        return iCustomerRepository.getAllCustomerByType(customerType, pageable);
    }
    @Override
    public Page<Customer> getAllCustomerByPhoneNumber(String phoneNumber, Pageable pageable) {
        return iCustomerRepository.getAllCustomerByPhoneNumber(phoneNumber, pageable);
    }
    @Override
    public Page<Customer> getCustomerById(String customerId, Pageable pageable) {
        return iCustomerRepository.getCustomerById(customerId,pageable);
    }

}

