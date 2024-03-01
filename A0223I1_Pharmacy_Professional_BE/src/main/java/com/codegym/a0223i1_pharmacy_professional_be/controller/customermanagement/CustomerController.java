package com.codegym.a0223i1_pharmacy_professional_be.controller.customermanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CustomerController {


    @Autowired
    private ICustomerService iCustomerService;


    @GetMapping("/list")
    public ResponseEntity<?> getAllCustomer(@RequestParam(defaultValue = "") String find,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page){
        Page<Customer> customers = iCustomerService.getAllCustomer(find, PageRequest.of(page,4));
        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id){
        iCustomerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getCustomerById/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable String id){
        Customer customer = iCustomerService.getCustomerById(id);
        if (customer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }


    @GetMapping(value = "/getAllInvoiceCustomer")
    public ResponseEntity<?> getAllInvoiceCustomer(@RequestParam(defaultValue = "") String id){
        List<IInvoiceDTO> invoice = iCustomerService.getAllInvoiceCustomer(id);
        if (invoice == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(invoice,HttpStatus.OK);
    }
}


