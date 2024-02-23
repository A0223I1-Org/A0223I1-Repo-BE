package com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.IInvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Customer;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Invoice;
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
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select customer.customer_id, customer.address,customer.age,customer.note,customer.customer_type,customer.customer_name,customer.phone_number,customer.account_id from customer "
            + "join account on customer.account_id=account.account_id"
            + " where account.delete_flag = 0", nativeQuery = true)
    Page<Customer> getAllCustomer(Pageable pageable);

    @Query(value = "select customer.customer_id, customer.account_id, customer.address,customer.age,customer.note,customer.customer_type,customer.phone_number,customer.customer_name from customer "
            + "join `account` on customer.account_id=`account`.account_id"
            + " where customer.customer_id= ?1 and `account`.delete_flag = 0", nativeQuery = true)
    Customer getCustomerById(String id);

    @Query(value = "UPDATE account\n" +
            "SET delete_flag = 1\n" +
            "WHERE account_id IN (\n" +
            "    SELECT account_id\n" +
            "    FROM customer\n" +
            "    WHERE customer_id = ?\n" +
            ")", nativeQuery = true)
    @Modifying
    void deleteCustomer(String id);

    @Query(value = "SELECT \n" +
            "    invoice.invoice_id as invoiceId ,\n" +
            "    DATE(invoice.date_create) as date ,\n" +
            "    TIME(invoice.date_create) as time ,\n" +
            "    customer.customer_name as customerName ,\n" +
            "    invoice.total as total \n" +
            "FROM invoice\n" +
            "INNER JOIN customer ON invoice.customer_id = customer.customer_id\n" +
            "INNER JOIN `account` ON customer.account_id = `account`.account_id\n" +
            "WHERE customer.customer_id= ?1 and `account`.delete_flag = 0\n", nativeQuery = true)
    List<IInvoiceDTO> getAllInvoiceCustomer(String id);

}