package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.EmployeeDto;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeDto> findAllEmployee1(String fill);
    List<EmployeeDto> findAllEmployee2(String fill);
    List<EmployeeDto> findAllEmployee3(String fill);
    List<EmployeeDto> findAllEmployee4(String fill);
    List<EmployeeDto> findAll();
    void save(Employee employee);
    void update(Employee employee);
    void updateEmail(int account_id,String email);
    void updateRoleId(int account_id,Long role_id);
    EmployeeDto findById(String employeeId);
    void delete(String employeeId);

    Employee findByEmployeeId(String employeeId);
}
