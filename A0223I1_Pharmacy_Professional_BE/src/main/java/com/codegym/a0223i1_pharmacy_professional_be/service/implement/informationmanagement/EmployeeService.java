package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.EmployeeDto;
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
    public List<EmployeeDto> findAllEmployee1(String fill) {
        List<EmployeeDto> employeeDtoList = iEmployeeRepository.findAllEmployee1("%"+fill+"%");
        return employeeDtoList;
    }

    @Override
    public List<EmployeeDto> findAllEmployee2(String fill) {
        List<EmployeeDto> employeeDtoList = iEmployeeRepository.findAllEmployee2("%"+fill+"%");
        return employeeDtoList;
    }

    @Override
    public List<EmployeeDto> findAllEmployee3(String fill) {
        List<EmployeeDto> employeeDtoList = iEmployeeRepository.findAllEmployee3("%"+fill+"%");
        return employeeDtoList;
    }

    @Override
    public List<EmployeeDto> findAllEmployee4(String fill) {
        List<EmployeeDto> employeeDtoList = iEmployeeRepository.findAllEmployee4("%"+fill+"%");
        return employeeDtoList;
    }

    @Override
    public List<EmployeeDto> findAll() {
        return iEmployeeRepository.findAllEmployee();
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
    public void updateEmail(int account_id, String email) {
        iEmployeeRepository.updateEmail(account_id,email);
    }

    @Override
    public void updateRoleId(int account_id, Long role_id) {
        iEmployeeRepository.updateRoleId(account_id,role_id);
    }


    @Override
    public EmployeeDto findById(String employeeId) {
        return iEmployeeRepository.findByIdEmployee(employeeId).orElse(null);
    }

    @Override
    public void delete(String employeeId) {
        iEmployeeRepository.deleteById(employeeId);

    }

    @Override
    public Employee findByEmployeeId(String employeeId) {
        return iEmployeeRepository.findById(employeeId).orElse(null);
    }
}
