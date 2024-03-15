package com.codegym.a0223i1_pharmacy_professional_be.controller.salesmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.InvoiceDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.InvoiceListViewDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.MedicinListF;
import com.codegym.a0223i1_pharmacy_professional_be.entity.*;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.MedicineRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.customermanagement.CustomerServiceImpl;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement.EmployeeService;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement.MedicineInformationServiceImpl;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.salesmanagement.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("api/v1/retailSaleManagement")
@CrossOrigin("*")
public class RetailSalesManagementController {
    // Quản lý bán hàng - BÁN LẺ
    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MedicineInformationServiceImpl medicineInformationService;

    @Autowired
    private MedicineRepository medicineRepository;
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

    @GetMapping("/displayCustomer")
    public ResponseEntity<?> findAllCustomer(@PageableDefault(page = 0, size = 10, sort = "customerId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Customer> result = invoiceService.findAllCustomer(pageable);
        if (result != null && !result.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", result.getContent());
            response.put("message", "Tìm danh sách khách hàng thành công!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("data", Collections.emptyList());
            response.put("message", "Danh sách khách hàng trống!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/displayEmployee")
    public ResponseEntity<?> findAllEmployee(@PageableDefault(page = 0, size = 10, sort = "employeeId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Employee> result = invoiceService.findAllEmployee(pageable);
        if (result != null && !result.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", result.getContent());
            response.put("message", "Tìm danh sách nhân viên thành công!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("data", Collections.emptyList());
            response.put("message", "Danh sách nhân viên trống!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/displayMedicine")
    public ResponseEntity<?> findAllMedicine(@PageableDefault(page = 0, size = 10, sort = "medicineId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Medicine> result = invoiceService.findAllMedicine(pageable);
        if (result != null && !result.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", result.getContent());
            response.put("message", "Tìm danh sách thuốc thành công!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("data", Collections.emptyList());
            response.put("message", "Danh sách thuốc trống!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        try {
            String dateCreateFFromReact = invoiceDTO.getDateCreateF();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dateCreateFFromReact, formatter);
            LocalDateTime localDateTime = localDate.atStartOfDay();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);

            Employee employee = new Employee();
              employee = employeeService.findByEmployeeId(invoiceDTO.getEmployeeIdF());

            Customer customer = new Customer();
             customer = customerService.findCustomerById(invoiceDTO.getCustomerIdF());
            Invoice invoice = new Invoice();
            invoice.setInvoiceId(invoiceService.generateNextInvoiceId());
            invoice.setDateCreate(timestamp);
            invoice.setNote(invoiceDTO.getNoteF());
            invoice.setCustomer(customer);
            invoice.setEmployee(employee);
            invoice.setInvoiceType("Bán lẻ");
            invoice.setStatus(2);
            invoice.setTotal(invoiceDTO.getTotalF());
            invoiceService.saveInvoice(invoice);

            // Xử lý chi tiết hóa đơn
            for (MedicinListF medicineDTO : invoiceDTO.getMedicineListF()) {
                Medicine medicine = medicineInformationService.findMedicineById(medicineDTO.getMedicineIdF());
                if (medicine != null) {
                    // Tạo và cấu hình chi tiết hóa đơn
                    InvoiceDetail invoiceDetail = new InvoiceDetail();

                    invoiceDetail.setMedicine(medicine);
                    invoiceDetail.setInvoice(invoice);
                    invoiceDetail.setUnit(medicineDTO.getUnitF());
                    invoiceDetail.setQuantity(medicineDTO.getQuantityF());
                    invoiceDetail.setPrice(medicineDTO.getRetailPriceF());


                    // Lưu thông tin chi tiết hóa đơn vào hóa đơn
                    invoice.addInvoiceDetail(invoiceDetail);

                    // Cập nhật số lượng của thuốc
                    int currentQuantity = medicine.getQuantity();
                    int purchasedQuantity = medicineDTO.getQuantityF();
                    medicine.setQuantity(currentQuantity - purchasedQuantity);
                    // Lưu thay đổi vào database
                    medicineRepository.save(medicine);

                } else {
                    // Không tìm thấy thông tin thuốc - không thực hiện thay đổi nào
                }
            }

            // Lưu InvoiceDetail vào database
            invoiceService.saveInvoiceDetails(invoice.getInvoiceDetails());

            return ResponseEntity.ok("Invoice created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating invoice");
        }
    }

}
