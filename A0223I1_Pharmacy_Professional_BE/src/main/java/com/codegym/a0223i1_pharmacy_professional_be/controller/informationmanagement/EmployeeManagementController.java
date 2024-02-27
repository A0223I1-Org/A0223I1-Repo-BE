package com.codegym.a0223i1_pharmacy_professional_be.controller.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.EmployeeDto;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import com.codegym.a0223i1_pharmacy_professional_be.entity.AccountRole;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Employee;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Role;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.IRoleService;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employee")
public class EmployeeManagementController {
    @Autowired
    IEmployeeService iEmployeeService;

    @Autowired
    IRoleService iRoleService;

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> findAll(){
        List<Employee> employeeList = iEmployeeService.findAll();
        if(employeeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody EmployeeDto employeeDto){
        if(employeeDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Account account = new Account();
        account.setEmail(employeeDto.getEmail());
        account.setPassword(employeeDto.getPassword());
        account.setDeleteFlag(false);

        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setDateStart(employeeDto.getDateStart());
        employee.setAddress(employeeDto.getAddress());
        employee.setNote(employeeDto.getNote());
        employee.setSalary(employeeDto.getSalary());
        employee.setImage(employeeDto.getImage());
        employee.setAccount(account);

        iEmployeeService.save(employee);

        Role role = iRoleService.findById(employeeDto.getRole_id());

        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(account);
        accountRole.setRole(role);

        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
  
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> detail(@PathVariable String employeeId){
        Employee employee = iEmployeeService.findById(employeeId);
        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
  
    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> update(@PathVariable String employeeId, @RequestBody EmployeeDto employeeDto){
        Employee employee = iEmployeeService.findById(employeeId);
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setDateStart(employeeDto.getDateStart());
        employee.setAddress(employeeDto.getAddress());
        employee.setNote(employeeDto.getNote());
        employee.setSalary(employeeDto.getSalary());
        employee.setImage(employeeDto.getImage());
        iEmployeeService.update(employee);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
  
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Employee> delete(@PathVariable String employeeId){
        Employee employee = iEmployeeService.findById(employeeId);
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iEmployeeService.delete(employeeId);
        return new ResponseEntity<>(employee,HttpStatus.NO_CONTENT);
    }
}
