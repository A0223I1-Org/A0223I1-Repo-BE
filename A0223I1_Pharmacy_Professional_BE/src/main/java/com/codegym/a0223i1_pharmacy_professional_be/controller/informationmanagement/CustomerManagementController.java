package com.codegym.a0223i1_pharmacy_professional_be.controller.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.AccountRoleDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.IAccountService;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.customermanagement.ICustomerService;
import com.codegym.a0223i1_pharmacy_professional_be.validate.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/api/customer")
public class CustomerManagementController {
    //Quản lý khách hàng
    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private CustomerValidate customerValidate;

    @GetMapping("/list")
    public ResponseEntity<?> getAllCustomer(@RequestParam(defaultValue = "") String find,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "3") Integer size) {
        Page<Customer> customers = iCustomerService.getAllCustomer(find, PageRequest.of(page, size));
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/list/searchByName")
    public ResponseEntity<?> getAllCustomerByName(@RequestParam(value = "customerName", defaultValue = "") String customerName,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Customer> customers = iCustomerService.getAllCustomerByName(customerName, PageRequest.of(page, 4));
        System.out.println(customers);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/list/searchByAge")
    public ResponseEntity<?> getAllCustomerByAge(@RequestParam(value = "age", defaultValue = "18") Integer age,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Customer> customers = iCustomerService.getAllCustomerByAge(age, PageRequest.of(page, 4));
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/list/searchByType")
    public ResponseEntity<?> getAllCustomerByType(@RequestParam(value = "customerType", defaultValue = "") String customerType,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Customer> customers = iCustomerService.getAllCustomerByType(customerType, PageRequest.of(page, 4));
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        iCustomerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getCustomerById/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable String id) {
        Customer customer = iCustomerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    @GetMapping(value = "/getAllInvoiceCustomer")
    public ResponseEntity<?> getAllInvoiceCustomer(@RequestParam(defaultValue = "") String id) {
        List<IInvoiceDTO> invoice = iCustomerService.getAllInvoiceCustomer(id);
        if (invoice == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return new ResponseEntity<CustomerDTO>(HttpStatus.BAD_REQUEST);
        } else {
            Map<String, String> errors = customerValidate.validate(customerDTO);
            if (errors.isEmpty()) {
                Account account = new Account();
                account.setEmail(customerDTO.getCustomerName());
                account.setPassword("123");
                account.setDeleteFlag(false);
                account = iAccountService.registerAccount(account);
                customerDTO.setAccountId(account.getAccountId());

                AccountRoleDTO accountRoleDTO = new AccountRoleDTO();
                accountRoleDTO.setAccountId(account.getAccountId());
                accountRoleDTO.setRoleId(3);
                iCustomerService.createCustomer(customerDTO);
                return new ResponseEntity<>(customerDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }


    @PostMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        if (iCustomerService.findCustomerById(customerDTO.getCustomerId()) == null) {
            return new ResponseEntity<>("không tìm thấy khách hàng nào", HttpStatus.BAD_REQUEST);
        } else {
            Map<String, String> errors = customerValidate.validate(customerDTO);
            if (errors.isEmpty()) {
                iCustomerService.updateCustomer(customerDTO);
                return new ResponseEntity<>(customerDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
