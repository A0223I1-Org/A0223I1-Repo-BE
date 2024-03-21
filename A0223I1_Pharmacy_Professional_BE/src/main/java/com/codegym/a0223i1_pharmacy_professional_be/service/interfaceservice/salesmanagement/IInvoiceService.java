package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.salesmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.InvoiceListViewDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IInvoiceService {
    List<InvoiceListViewDTO> findAllInvoice();

    Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRangeAndDisplayField(LocalDate fromDate,  LocalDate toDate,  LocalTime fromTime, LocalTime toTime, String displayField, Pageable pageable);
    Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRange(LocalDate fromDate,  LocalDate toDate,  LocalTime fromTime, LocalTime toTime, Pageable pageable);
    List<Symptom> findAllSymtom();
    List<Prescription> findAllPrescription();
    List<Customer> findAllCustomer();
    List<Employee> findAllEmployee();
    List<Medicine> findAllMedicine();

    void saveInvoice(Invoice invoice);
    void saveInvoiceDetails(List<InvoiceDetail> invoiceDetails);
    String generateNextInvoiceId();
}
