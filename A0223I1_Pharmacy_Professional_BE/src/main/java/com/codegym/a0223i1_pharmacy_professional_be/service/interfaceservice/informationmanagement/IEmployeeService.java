package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    void save(Employee employee);
    void update(Employee employee);
    Employee findById(String employeeId);
    void delete(String employeeId);
}
