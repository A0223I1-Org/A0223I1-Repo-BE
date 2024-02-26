package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Employee;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.IEmployeeRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeRepository iEmployeeRepository;
    @Override
    public List<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee.getEmployeeId(),employee.getEmployeeName(),employee.getPhoneNumber(),
                employee.getDateStart(),employee.getAddress(),employee.getNote(),employee.getSalary(),
                employee.getImage(), employee.getAccount().getAccountId());
    }

    @Override
    public void update(Employee employee) {
        iEmployeeRepository.update(employee.getEmployeeId(),employee.getEmployeeName(),employee.getPhoneNumber(),
                employee.getDateStart(),employee.getAddress(),employee.getNote(),employee.getSalary(),
                employee.getImage(), employee.getAccount().getAccountId());
    }

    @Override
    public Employee findById(String employeeId) {
        return iEmployeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public void delete(String employeeId) {
        iEmployeeRepository.deleteById(employeeId);
    }
}
