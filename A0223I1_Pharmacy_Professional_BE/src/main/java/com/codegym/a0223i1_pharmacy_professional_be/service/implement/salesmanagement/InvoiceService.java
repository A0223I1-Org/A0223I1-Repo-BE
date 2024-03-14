package com.codegym.a0223i1_pharmacy_professional_be.service.implement.salesmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.InvoiceListViewDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.*;
import com.codegym.a0223i1_pharmacy_professional_be.repository.salesmanagement.IInvoiceRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.salesmanagement.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceRepository iInvoiceRepository;

    @Override
    public Page<InvoiceListViewDTO> findAllInvoice(Pageable pageable) {
        return iInvoiceRepository.findAllInvoice(pageable);

    }

    @Override
    public Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRangeAndDisplayField(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime, String displayField,Pageable pageable) {
        return iInvoiceRepository.findInvoiceByDateAndTimeRangeAndDisplayField(fromDate, toDate, fromTime, toTime, displayField, pageable);
    }

    @Override
    public Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRange(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime, Pageable pageable) {
        return iInvoiceRepository.findInvoiceByDateAndTimeRange(fromDate, toDate, fromTime, toTime, pageable);
    }

    @Override
    public Page<Symptom> findAllSymtom(Pageable pageable) {
        return iInvoiceRepository.findAllSymtom(pageable);
    }

    @Override
    public Page<Prescription> findAllPrescription(Pageable pageable) {
        return iInvoiceRepository.findAllPrescription(pageable);
    }

    @Override
    public Page<Customer> findAllCustomer(Pageable pageable) {
        return iInvoiceRepository.findAllCustomer(pageable);
    }

    @Override
    public Page<Employee> findAllEmployee(Pageable pageable) {
        return iInvoiceRepository.findAllEmployee(pageable);
    }

    @Override
    public Page<Medicine> findAllMedicine(Pageable pageable) {
        return iInvoiceRepository.findAllMedicine(pageable);
    }

    @Override
    public String generateNextInvoiceId() {
        List<Invoice> invoices = iInvoiceRepository.findAll();
        int maxCode = 0;
        for (Invoice i : invoices) {
            if (i.getInvoiceId() != null) {
                String code = i.getInvoiceId().substring(2); // Remove the "TT" prefix
                try {
                    int codeValue = Integer.parseInt(code);
                    if (codeValue > maxCode) {
                        maxCode = codeValue;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        String nextCode = String.format("TT%04d", maxCode + 1);
        return nextCode;
    }
}
