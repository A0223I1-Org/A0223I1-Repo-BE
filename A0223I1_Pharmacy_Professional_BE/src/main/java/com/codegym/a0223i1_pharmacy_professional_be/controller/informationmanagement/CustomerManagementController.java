package com.codegym.a0223i1_pharmacy_professional_be.controller.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement.ICustomerService;
import com.codegym.a0223i1_pharmacy_professional_be.validate.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerManagementController {
    //Quản lý khách hàng
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private CustomerValidate customerValidate;

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO){
        if(customerDTO == null){
            return new ResponseEntity<CustomerDTO>(HttpStatus.BAD_REQUEST);
        }
        else {
            Map<String,String> errors = customerValidate.validate(customerDTO);
            if(errors.isEmpty()){
                customerService.createCustomer(customerDTO);
                return new ResponseEntity<>(customerDTO, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping(value = "/getCustomerById/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable String customerId){
        Customer customer = customerService.getCustomerById(customerId);
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO){
        if(customerService.findCustomerById(customerDTO.getCustomerId()) == null){
            return new ResponseEntity<>("không tìm thấy khách hàng nào",HttpStatus.BAD_REQUEST);
        }
        else {
            Map<String,String> errors = customerValidate.validate(customerDTO);
            if(errors.isEmpty()){
                customerService.updateCustomer(customerDTO);
                return new ResponseEntity<>(customerDTO,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
