package com.codegym.a0223i1_pharmacy_professional_be.controller.salesmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.InvoiceListViewDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.salesmanagement.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@RequestMapping("api/v1/retailSaleManagement")
@CrossOrigin("*")
public class RetailSalesManagementController {
    // Quản lý bán hàng - BÁN LẺ
    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping("/displayInvoice")
    public ResponseEntity<?> findAllInvoice(@PageableDefault(page = 0, size = 10, sort = "invoice_id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<InvoiceListViewDTO> result = invoiceService.findAllInvoice(pageable);
        if (result != null && !result.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", result.getContent());
            response.put("message", "Tìm danh sách hóa đơn thành công!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("data", Collections.emptyList());
            response.put("message", "Danh sách hóa đơn trống!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping("/filterInvoice")
    public ResponseEntity<?> findInvoiceByDateAndTimeRange(
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
            @RequestParam("fromTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime fromTime,
            @RequestParam("toTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime toTime,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "displayField", required = false) String displayField,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        try {
            Page<InvoiceListViewDTO> result;
            if (displayField != null && sortField != null) {
                result = invoiceService.findInvoiceByDateAndTimeRangeAndDisplayField(fromDate, toDate, fromTime, toTime, displayField, pageable);
                // Sắp xếp dữ liệu theo trường sortField
                result = sortResult(result, sortField, pageable);
            } else if (displayField != null) {
                result = invoiceService.findInvoiceByDateAndTimeRangeAndDisplayField(fromDate, toDate, fromTime, toTime, displayField, pageable);
            } else if (sortField != null) {
                result = invoiceService.findInvoiceByDateAndTimeRange(fromDate, toDate, fromTime, toTime, pageable);
                result = sortResult(result, sortField, pageable);
            } else {
                result = invoiceService.findInvoiceByDateAndTimeRange(fromDate, toDate, fromTime, toTime, pageable);
            }

            if (result != null && !result.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("data", result.getContent());
                response.put("message", "Tìm danh sách thành công!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("data", Collections.emptyList());
                response.put("message", "Danh sách trống!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            // Xử lý khi các tham số sai định dạng
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Các tham số không đúng định dạng.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private Page<InvoiceListViewDTO> sortResult(Page<InvoiceListViewDTO> result, String sortField, Pageable pageable) {
        // Sắp xếp dữ liệu theo trường sortField
        Sort sort = Sort.by(sortField);
        if (pageable.getSort().isSorted()) {
            sort = sort.and(pageable.getSort());
        }
        return new PageImpl<>(result.getContent(), pageable, result.getTotalElements());
    }


    @GetMapping("/displaySymtom")
    public ResponseEntity<?> findAllSymtom(@PageableDefault(page = 0, size = 10, sort = "symptomId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Symptom> result = invoiceService.findAllSymtom(pageable);
        if (result != null && !result.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", result.getContent());
            response.put("message", "Tìm danh sách triệu chứng thành công!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("data", Collections.emptyList());
            response.put("message", "Danh sách triệu chứng trống!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping("/displayPrescription")
    public ResponseEntity<?> findAllPrescription(@PageableDefault(page = 0, size = 10, sort = "prescriptionId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Prescription> result = invoiceService.findAllPrescription(pageable);
        if (result != null && !result.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", result.getContent());
            response.put("message", "Tìm danh sách toa thuốc thành công!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("data", Collections.emptyList());
            response.put("message", "Danh sách toa thốc trống!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
