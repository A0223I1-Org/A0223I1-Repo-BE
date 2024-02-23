package com.codegym.a0223i1_pharmacy_professional_be.service.implement.customermanagement;

//import com.example.dto.ICustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Invoice;
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


}
