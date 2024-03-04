package com.codegym.a0223i1_pharmacy_professional_be.repository.salesmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.InvoiceListViewDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Invoice;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.*;


public interface IInvoiceRepository extends JpaRepository<Invoice, String> {
    @Query(value = "SELECT i.invoice_id, i.customer_id, i.employee_id, DATE(i.date_create) AS create_date, i.status, i.total, i.note, c.customer_name, e.employee_name, i.invoice_type " +
            "FROM invoice i " +
            "JOIN customer c ON i.customer_id = c.customer_id " +
            "JOIN employee e ON i.employee_id = e.employee_id", nativeQuery = true)
    Page<InvoiceListViewDTO> findAllInvoice(Pageable pageable);

    @Query(value = "SELECT i.invoice_id, i.customer_id, i.employee_id, DATE(i.date_create) AS create_date, TIME(i.date_create) AS create_time, i.status, i.total, i.note, c.customer_name, e.employee_name, i.invoice_type " +
            "FROM invoice i " +
            "JOIN customer c ON i.customer_id = c.customer_id " +
            "JOIN employee e ON i.employee_id = e.employee_id " +
            "WHERE DATE(i.date_create) BETWEEN :fromDate AND :toDate " +
            "AND TIME(i.date_create) BETWEEN :fromTime AND :toTime " +
            "AND i.invoice_type = :displayField", nativeQuery = true)
    Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRangeAndDisplayField(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, @Param("fromTime") LocalTime fromTime, @Param("toTime") LocalTime toTime, @Param("displayField") String displayField,Pageable pageable);
    @Query(value = "SELECT i.invoice_id, i.customer_id, i.employee_id, DATE(i.date_create) AS create_date, TIME(i.date_create) AS create_time, i.status, i.total, i.note, c.customer_name, e.employee_name, i.invoice_type " +
            "FROM invoice i " +
            "JOIN customer c ON i.customer_id = c.customer_id " +
            "JOIN employee e ON i.employee_id = e.employee_id " +
            "WHERE DATE(i.date_create) BETWEEN :fromDate AND :toDate " +
            "AND TIME(i.date_create) BETWEEN :fromTime AND :toTime", nativeQuery = true)
    Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRange(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, @Param("fromTime") LocalTime fromTime, @Param("toTime") LocalTime toTime, Pageable pageable);

    @Query("SELECT s FROM Symptom s")
    Page<Symptom> findAllSymtom(Pageable pageable);

    @Query("SELECT p FROM Prescription p")
    Page<Prescription> findAllPrescription(Pageable pageable);
}

