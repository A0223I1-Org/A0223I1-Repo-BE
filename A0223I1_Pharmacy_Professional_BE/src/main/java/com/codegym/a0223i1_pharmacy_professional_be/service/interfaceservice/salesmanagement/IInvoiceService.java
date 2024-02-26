package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.salesmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.InvoiceListViewDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IInvoiceService {
    Page<InvoiceListViewDTO> findAllInvoice(Pageable pageable);

    Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRangeAndDisplayField(LocalDate fromDate,  LocalDate toDate,  LocalTime fromTime, LocalTime toTime, String displayField,Pageable pageable);
    Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRange(LocalDate fromDate,  LocalDate toDate,  LocalTime fromTime, LocalTime toTime, Pageable pageable);
    Page<Symptom> findAllSymtom(Pageable pageable);
    Page<Prescription> findAllPrescription(Pageable pageable);


}
