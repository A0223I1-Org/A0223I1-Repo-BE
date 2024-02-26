package com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer,String> {
    @Modifying
    @Query(value = "insert into customer(customer.customer_id,customer.customer_name,customer.age,customer.address,customer.phone_number," +
            "customer.customer_type, customer.note, customer.account_id) value(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
    void createCustomer(String customerId,String customerName, int customerAge, String address, String phone, String customerType, String note, int accountId);

    @Modifying
    @Query(value = "select customer.customer_id as customerId, customer.customer_name,customer.age,customer.address,customer.phone_number," +
       "customer.customer_type, customer.note, customer.account_id from customer where customer.customer_id = ?1", nativeQuery = true)
    Customer getCustomerById(String customerId);
//    @Modifying
//    @Query(value="select customer.customer_id ,customer.customer_name as customerName ,customer.age,customer.address,customer.phone_number," +
//            "customer.customer_type, customer.note, customer.account_id from customer where customer.customer_name = ?1", nativeQuery = true)
//    Customer getCustomerByName(String customerName);
//    @Modifying
//    @Query(value="select customer.customer_id ,customer.customer_name as customerName ,customer.age,customer.address,customer.phone_number," +
//            "customer.customer_type, customer.note, customer.account_id from customer where customer.customer_name = ?1", nativeQuery = true)
//    Customer getCustomerByName(String customerName);

    @Query(value = "select customer.customer_id as customerId, customer.customer_name,customer.age,customer.address,customer.phone_number," +
            "customer.customer_type, customer.note, customer.account_id from customer where customer.customer_id = ?1", nativeQuery = true)
    Customer findCustomerById(String customerId);

    @Modifying
    @Transactional
    @Query(value = "update customer set customer.customer_name = ?1, customer.age = ?2," +
            "customer.address = ?3, customer.phone_number = ?4, customer.customer_type = ?5, customer.note = ?6, customer.account_id = ?7 " +
            "where customer.customer_id = ?8",nativeQuery = true)
    void updateCustomer(String name, int age, String address, String phone, String type, String note, int accountId,String customerId);
}
