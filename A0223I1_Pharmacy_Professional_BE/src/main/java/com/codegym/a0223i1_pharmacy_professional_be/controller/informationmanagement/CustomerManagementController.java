package com.codegym.a0223i1_pharmacy_professional_be.controller.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement.ICustomerService;
import com.codegym.a0223i1_pharmacy_professional_be.validate.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerManagementController {
    //Quản lý khách hàng
    @Autowired
    private ICustomerService iCustomerService;


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
                iCustomerService.createCustomer(customerDTO);
                return new ResponseEntity<>(customerDTO, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping(value = "/getCustomerById/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId){
        Customer customer = iCustomerService.getCustomerByIdd(customerId);
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getCustomersByName/{customerName}")
    public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable String customerName){
        List<Customer> customers = iCustomerService.getCustomersByName(customerName);
        if(customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(customers,HttpStatus.OK);
        }
    }
    @GetMapping(value = "/getCustomersByAge/{age}")
    public ResponseEntity<List<Customer>> getCustomersByAge(@PathVariable Integer age){
        List<Customer> customers = iCustomerService.getCustomersByAge(age);
        if(customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(customers,HttpStatus.OK);
        }
    }
    @GetMapping(value = "/getCustomersByType/{customerType}")
    public ResponseEntity<List<Customer>> getCustomersByType(@PathVariable String customerType){
        List<Customer> customers = iCustomerService.getCustomersByType(customerType);
        if(customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(customers,HttpStatus.OK);
        }
    }


    @PostMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO){
        if(iCustomerService.findCustomerById(customerDTO.getCustomerId()) == null){
            return new ResponseEntity<>("không tìm thấy khách hàng nào",HttpStatus.BAD_REQUEST);
        }
        else {
            Map<String,String> errors = customerValidate.validate(customerDTO);
            if(errors.isEmpty()){
                iCustomerService.updateCustomer(customerDTO);
                return new ResponseEntity<>(customerDTO,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
