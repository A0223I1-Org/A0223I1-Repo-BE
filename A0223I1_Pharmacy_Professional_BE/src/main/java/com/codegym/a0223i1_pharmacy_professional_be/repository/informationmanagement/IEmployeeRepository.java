package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;


import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee,String> {
    @Query(value = "select employee_id, employee_name, phone_number, date_start, address, note, salary, image, account_id " +
            "from employee", nativeQuery = true)
    List<Employee> findAll();

    @Query(value = "insert into employee (employee_id, employee_name, phone_number, date_start, address, note, salary, image, account_id) " +
            "values (:employeeId, :employeeName, :phoneNumber, :dateStart, :address, :note, :salary, :image, :account_id)", nativeQuery = true)
    void save(@Param("employeeId") String employeeId,
              @Param("employeeName") String employeeName,
              @Param("phoneNumber") String phoneNumber,
              @Param("dateStart") Date dateStart,
              @Param("address") String address,
              @Param("note") String note,
              @Param("salary") int salary,
              @Param("image") String image,
              @Param("account_id")Integer account_id);

    @Query(value = "update employee set employee_name = :employeeName, phone_number = :phoneNumber, " +
            "date_start = :dateStart, address = :address, note = :note, salary = :salary, image = :image ,account_id = :account_id " +
            "where employee_id = :employeeId", nativeQuery = true)
    void update(@Param("employeeId") String employeeId,
                @Param("employeeName") String employeeName,
                @Param("phoneNumber") String phoneNumber,
                @Param("dateStart") Date dateStart,
                @Param("address") String address,
                @Param("note") String note,
                @Param("salary") int salary,
                @Param("image") String image,
                @Param("account_id") Integer account_id);

    @Query(value = "select employee_id, employee_name, phone_number, date_start, address, note, salary, image " +
            "from employee where employee_id like :employeeId", nativeQuery = true)
    Optional<Employee> findById(@Param("employeeId") String employeeId);
  
    @Query(value = "delete from employee where employee_id like :employeeId", nativeQuery = true)
    void deleteById(@Param("employeeId") String employeeId);
}
