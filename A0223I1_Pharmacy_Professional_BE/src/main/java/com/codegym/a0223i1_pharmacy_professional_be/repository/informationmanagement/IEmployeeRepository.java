package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.EmployeeDto;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public interface IEmployeeRepository extends JpaRepository<Employee,String> {
    @Query(value = "select employee_id, employee_name, phone_number, date_start, address, note, salary, image, e.account_id , a.email, a.password , al.role_id \n" +
            "from employee e " +
            "join account a " +
            "on e.account_id = a.account_id " +
            "join account_role al " +
            "on al.account_id = a.account_id " +
            "where a.delete_flag = 0 and employee_id like :fill " +
            "order by employee_id asc", nativeQuery = true)
    List<EmployeeDto> findAllEmployee1(@Param("fill") String fill);
    @Query(value = "select employee_id, employee_name, phone_number, date_start, address, note, salary, image, e.account_id , a.email, a.password , al.role_id \n" +
            "from employee e " +
            "join account a " +
            "on e.account_id = a.account_id " +
            "join account_role al " +
            "on al.account_id = a.account_id " +
            "where a.delete_flag = 0 and employee_id like :fill " +
            "order by employee_name asc", nativeQuery = true)
    List<EmployeeDto> findAllEmployee2(@Param("fill") String fill);
    @Query(value = "select employee_id, employee_name, phone_number, date_start, address, note, salary, image, e.account_id , a.email, a.password , al.role_id \n" +
            "from employee e " +
            "join account a " +
            "on e.account_id = a.account_id " +
            "join account_role al " +
            "on al.account_id = a.account_id " +
            "where a.delete_flag = 0 and employee_name like :fill " +
            "order by employee_id asc", nativeQuery = true)
    List<EmployeeDto> findAllEmployee3(@Param("fill") String fill);
    @Query(value = "select employee_id, employee_name, phone_number, date_start, address, note, salary, image, e.account_id , a.email, a.password , al.role_id \n" +
            "from employee e " +
            "join account a " +
            "on e.account_id = a.account_id " +
            "join account_role al " +
            "on al.account_id = a.account_id " +
            "where a.delete_flag = 0 and employee_name like :fill " +
            "order by employee_name asc", nativeQuery = true)
    List<EmployeeDto> findAllEmployee4(@Param("fill") String fill);
    @Query(value = "select employee_id, employee_name, phone_number, date_start, address, note, salary, image, e.account_id , a.email, a.password , al.role_id " +
            "            from employee e " +
            "            join account a " +
            "            on e.account_id = a.account_id " +
            "            join account_role al " +
            "            on al.account_id = a.account_id " +
            "            where a.delete_flag = 0", nativeQuery = true)
    List<EmployeeDto> findAllEmployee();
    @Modifying
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
    @Modifying
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
    @Modifying
    @Query(value = "update account " +
            "set email = :email " +
            "where account_id = :account_id",nativeQuery = true)
    void updateEmail(@Param("account_id")int account_id,
                     @Param("email") String email);
    @Modifying
    @Query(value = "update account_role " +
            "set role_id = :role_id " +
            "where account_id = :account_id" ,nativeQuery = true)
    void updateRoleId(@Param("account_id")int account_id,
                      @Param("role_id")Long role_id);
    @Query(value = "select employee_id, employee_name, phone_number, date_start, address, note, salary, image, e.account_id, a.email, a.password, al.role_id " +
            "            from employee e " +
            "            join account a " +
            "            on e.account_id = a.account_id " +
            "            join account_role al " +
            "            on al.account_id = a.account_id " +
            "            where employee_id like :employeeId", nativeQuery = true)
    Optional<EmployeeDto> findByIdEmployee(@Param("employeeId") String employeeId);
    @Modifying
    @Query(value = "update account " +
            "set delete_flag = 1 " +
            "where account_id in ( " +
            "    select account_id " +
            "    from employee\n" +
            "    where employee_id like :employeeId" +
            ")", nativeQuery = true)
    void deleteById(@Param("employeeId") String employeeId);
}
