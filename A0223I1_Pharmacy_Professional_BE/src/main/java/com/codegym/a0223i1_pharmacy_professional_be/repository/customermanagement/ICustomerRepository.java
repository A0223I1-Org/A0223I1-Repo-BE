package com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer,String> {

    @Query(value="select customer.phone_number from customer ", nativeQuery = true)
    List<String> getAllPhoneNumber();


    @Query(value = "select customer.customer_id, customer.address,customer.age,customer.note,customer.customer_type,customer.customer_name,customer.phone_number,customer.account_id from customer "
            + "join account on customer.account_id=account.account_id", nativeQuery = true)
    Page<Customer> getAllCustomer(Pageable pageable);

    @Query(value = "select customer.customer_id, customer.address,customer.age,customer.note,customer.customer_type,customer.customer_name,customer.phone_number,customer.account_id from customer "
            + "join account on customer.account_id=account.account_id"
            + " where account.delete_flag = 0"
            + " ORDER BY customer.customer_id", nativeQuery = true)
    Page<Customer> getAllOrderByCustomerId(Pageable pageable);
    @Query(value = "select customer.customer_id, customer.address,customer.age,customer.note,customer.customer_type,customer.customer_name,customer.phone_number,customer.account_id from customer "
            + "join account on customer.account_id=account.account_id"
            + " where account.delete_flag = 0"
            + " ORDER BY customer.customer_name", nativeQuery = true)
    Page<Customer> getAllOrderByCustomerName(Pageable pageable);
    @Query(value = "select customer.customer_id, customer.address,customer.age,customer.note,customer.customer_type,customer.customer_name,customer.phone_number,customer.account_id from customer "
            + "join account on customer.account_id=account.account_id"
            + " where account.delete_flag = 0"
            + " ORDER BY customer.address desc", nativeQuery = true)
    Page<Customer> getAllOrderByCustomerAddress(Pageable pageable);
    @Query(value = "select customer.customer_id, customer.address,customer.age,customer.note,customer.customer_type,customer.customer_name,customer.phone_number,customer.account_id from customer "
            + "join account on customer.account_id=account.account_id"
            + " where account.delete_flag = 0"
            + " ORDER BY customer.phone_number", nativeQuery = true)
    Page<Customer> getAllOrderByPhoneNumber(Pageable pageable);
    @Query(value = "select customer.customer_id, customer.address,customer.age,customer.note,customer.customer_type,customer.customer_name,customer.phone_number,customer.account_id from customer "
            + "join account on customer.account_id=account.account_id"
            + " where account.delete_flag = 0"
            + " ORDER BY customer.customer_type", nativeQuery = true)
    Page<Customer> getAllOrderByCustomerType(Pageable pageable);

    @Query(value="select customer.customer_id ,customer.customer_name  ,customer.age,customer.address,customer.phone_number," +
            "customer.customer_type, customer.note, customer.account_id from customer " +
            "join account on customer.account_id=account.account_id" +
            " where customer.customer_name LIKE CONCAT('%',?1,'%') and account.delete_flag = 0", nativeQuery = true)
    Page<Customer> getAllCustomerByName(String customerName,Pageable pageable);

    @Query(value="select customer.customer_id ,customer.customer_name  ,customer.age,customer.address,customer.phone_number," +
            "customer.customer_type, customer.note, customer.account_id from customer " +
            "join account on customer.account_id=account.account_id" +
            " where customer.address LIKE CONCAT('%',?1,'%') and account.delete_flag = 0", nativeQuery = true)
    Page<Customer> getAllCustomerByAddrerss(String address,Pageable pageable);

    @Query(value="select customer.customer_id ,customer.customer_name ,customer.age,customer.address,customer.phone_number," +
            "customer.customer_type, customer.note, customer.account_id from customer " +
            "join account on customer.account_id=account.account_id" +
            " where customer.phone_number LIKE CONCAT('%',?1,'%') and account.delete_flag = 0", nativeQuery = true)
    Page<Customer> getAllCustomerByPhoneNumber(String phoneNumber,Pageable pageable);

    @Query(value="select customer.customer_id ,customer.customer_name ,customer.age,customer.address,customer.phone_number," +
            "customer.customer_type, customer.note, customer.account_id from customer " +
            "join account on customer.account_id=account.account_id" +
            " where customer.customer_type = ?1 and account.delete_flag = 0", nativeQuery = true)
    Page<Customer> getAllCustomerByType(String customerType,Pageable pageable);

    @Query(value = "select customer.customer_id, customer.account_id, customer.address,customer.age,customer.note,customer.customer_type,customer.phone_number,customer.customer_name from customer "
            + "join `account` on customer.account_id=`account`.account_id"
            + " where customer.customer_id= ?1 and `account`.delete_flag = 0", nativeQuery = true)
    Customer getCustomerById(String customerId);
    @Query(value = "select customer.customer_id, customer.account_id, customer.address,customer.age,customer.note,customer.customer_type,customer.phone_number,customer.customer_name from customer "
            + "join `account` on customer.account_id=`account`.account_id"
            + " where customer.customer_id= ?1 and `account`.delete_flag = 0", nativeQuery = true)
    Page<Customer> getCustomerById(String customerId,Pageable pageable);

    @Query(value = "UPDATE account\n" +
            "SET delete_flag = 1\n" +
            "WHERE account_id IN (\n" +
            "    SELECT account_id\n" +
            "    FROM customer\n" +
            "    WHERE customer_id = ?\n" +
            ")", nativeQuery = true)
    @Modifying
    void deleteCustomer(String id);

    @Query(value =
            "SELECT invoice.invoice_id as invoiceId,DATE(invoice.date_create) as date,\n" +
                    "TIME(invoice.date_create) as time,employee.employee_name as employeeName,invoice.total as total\n" +
                    "FROM invoice\n" +
                    "INNER JOIN customer ON invoice.customer_id = customer.customer_id\n" +
                    "INNER JOIN employee ON invoice.employee_id = employee.employee_id\n" +
                    "INNER JOIN `account` ON customer.account_id = `account`.account_id\n" +
                    "WHERE DATE(invoice.date_create) BETWEEN ?2 AND ?3 AND\n" +
                    "    CAST(TIME(invoice.date_create) AS TIME) BETWEEN ?4 AND ?5 AND\n" +
                    "    customer.customer_id = ?1 AND `account`.delete_flag = 0;", nativeQuery = true)
    Page<IInvoiceDTO> getAllInvoiceCustomer( String id, String startDay, String endDay,String startHour,
                                             String endHour,Pageable pageable);

    @Modifying
    @Query(value = "insert into customer(customer.customer_id,customer.customer_name,customer.age,customer.address,customer.phone_number," +
            "customer.customer_type, customer.note, customer.account_id) value(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
    void createCustomer(String customerId,String customerName, int customerAge, String address, String phone, String customerType, String note, int accountId);

    @Query(value = "select customer.customer_id , customer.customer_name,customer.age,customer.address,customer.phone_number," +
            "customer.customer_type, customer.note, customer.account_id from customer where customer.customer_id = ?1", nativeQuery = true)
    Customer findCustomerById(String customerId);

    @Modifying
    @Transactional
    @Query(value = "update customer set customer.customer_name = ?1, customer.age = ?2," +
            "customer.address = ?3, customer.phone_number = ?4, customer.customer_type = ?5, customer.note = ?6 " +
            "where customer.customer_id = ?7",nativeQuery = true)
    void updateCustomer(String name, int age, String address, String phone, String type, String note, String customerId);

    @Query(value = "select * from customer ", nativeQuery = true)
    List<String> getAllCustomerIncludeDeleted();
}



