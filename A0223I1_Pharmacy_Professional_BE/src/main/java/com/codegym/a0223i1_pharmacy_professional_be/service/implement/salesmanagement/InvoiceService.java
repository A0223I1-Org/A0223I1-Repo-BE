package com.codegym.a0223i1_pharmacy_professional_be.service.implement.salesmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.InvoiceListViewDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.*;
import com.codegym.a0223i1_pharmacy_professional_be.repository.salesmanagement.IInvoiceDetailRepository;
import com.codegym.a0223i1_pharmacy_professional_be.repository.salesmanagement.IInvoiceRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.salesmanagement.IInvoiceService;
import jakarta.transaction.Transactional;
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

    @Autowired
    private IInvoiceDetailRepository iInvoiceDetailRepository;
    @Override
    public List<InvoiceListViewDTO> findAllInvoice() {
        return iInvoiceRepository.findAllInvoice();

    }

    @Override
    public Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRangeAndDisplayField(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime, String displayField, Pageable pageable) {
        return iInvoiceRepository.findInvoiceByDateAndTimeRangeAndDisplayField(fromDate, toDate, fromTime, toTime, displayField, pageable);
    }

    @Override
    public Page<InvoiceListViewDTO> findInvoiceByDateAndTimeRange(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime, Pageable pageable) {
        return iInvoiceRepository.findInvoiceByDateAndTimeRange(fromDate, toDate, fromTime, toTime, pageable);
    }

    @Override
    public List<Symptom> findAllSymtom() {
        return iInvoiceRepository.findAllSymtom();
    }

    @Override
    public List<Prescription> findAllPrescription() {
        return iInvoiceRepository.findAllPrescription();
    }

    @Override
    public List<Customer> findAllCustomer() {
        return iInvoiceRepository.findAllCustomer();
    }

    @Override
    public List<Employee> findAllEmployee() {
        return iInvoiceRepository.findAllEmployee();
    }

    @Override
    public List<Medicine> findAllMedicine() {
        return iInvoiceRepository.findAllMedicine();
    }

    @Transactional
    public void saveInvoice(Invoice invoice) {
            iInvoiceRepository.save(invoice);
    }

    @Transactional
    public void saveInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
        iInvoiceDetailRepository.saveAll(invoiceDetails);
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
