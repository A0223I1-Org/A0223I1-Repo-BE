package com.codegym.a0223i1_pharmacy_professional_be.controller.report;

import com.codegym.a0223i1_pharmacy_professional_be.dto.*;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.report.IReportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ReportController {

    @Autowired
    IReportService iReportService;

    @RequestMapping(value = "/revenue-profit", method = RequestMethod.GET)
    public ResponseEntity<?> getRevenueAndProfit(@RequestParam String chartType
            , @RequestParam String startDate, @RequestParam String endDate) {
//        if (rechartType.equalsIgnoreCase("year")) {
//        }else{
//        }
        List<IRevenueProfitDTO> iRevenueProfitDTOS = iReportService.getRevenueAndProfit(chartType   ,startDate,endDate);
        return new ResponseEntity<>(iRevenueProfitDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/generate-report", method = RequestMethod.GET)
    public ResponseEntity<?> generateReport(@RequestParam String reportType,
                                            @RequestParam String startDate,
                                            @RequestParam String endDate,
                                            @RequestParam String startTime,
                                            @RequestParam String endTime) throws IOException {
        switch (reportType) {
            case "drug-enter":
                return getAllDrugEnterReport();
            case "medicines-expiring-soon":
                return getAllMedicinesExpiringSoon();
            case "top-selling-medicine":
                return findTopSellingMedicines(startDate, endDate, startTime, endTime);
            case "sales-diary":
                return salesDiary(startDate, endDate, startTime, endTime);
            case "debt":
                return getDebtSuppliers();
            case "revenue":
                return revenue(startDate, endDate, startTime, endTime);
            case "profit":
                return profit(startDate, endDate, startTime, endTime);
            default:
                return new ResponseEntity<>("Loại báo cáo không hợp lệ", HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> profit
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<IRevenueDTO> profit = iReportService.profit(startDate, endDate, startTime, endTime);
        if (profit.isEmpty()) {
            return new ResponseEntity<>("Cửa hàng chưa bán được trong khoảng thời gian trên", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Thời gian", "Lợi nhuận"};
        writeWorkbook(profit, columnNames, "Lợi nhuận", "D:\\loinhuan.xlsx");
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }
    private ResponseEntity<?> revenue
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<IRevenueDTO> revenue = iReportService.revenue(startDate, endDate, startTime, endTime);
        if (revenue.isEmpty()) {
            return new ResponseEntity<>("Cửa hàng chưa bán được trong khoảng thời gian trên", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Thời gian", "Doanh thu"};
        writeWorkbook(revenue, columnNames, "Doanh thu", "D:\\doanhthu.xlsx");
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }

    private ResponseEntity<?> getDebtSuppliers() throws IOException {
        List<ISupplierDTO> suppliers = iReportService.getDebtSuppliers();
        if (suppliers.isEmpty()) {
            return new ResponseEntity<>("Không có nhà cung cấp nào có công nợ", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Email", "Số điện thoại", "Công nợ"};
        writeWorkbook(suppliers, columnNames, "Nhật ký bán hàng", "D:\\congno.xlsx");
        return new ResponseEntity<>("Công nợ của các nhà cung cấp đã được tạo", HttpStatus.OK);
    }

    private ResponseEntity<?> salesDiary(String startDate, String endDate, String startTime, String endTime)
            throws IOException {
        List<ISalesDiaryDTO> iSalesDiaryDTOS = iReportService.salesDiary(startDate, endDate, startTime, endTime);
        if (iSalesDiaryDTOS.isEmpty()) {
            return new ResponseEntity<>("Không có thuốc cần nhập thêm", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Tên nhân viên", "Ngày tạo", "Mã hóa đơn", "Tổng tiền"};
        writeWorkbook(iSalesDiaryDTOS, columnNames, "Nhật ký bán hàng", "D:\\nhatkybanhang.xlsx");
        return new ResponseEntity<>("Báo cáo nhật ký bán hàng đã được tạo", HttpStatus.OK);
    }

    private ResponseEntity<?> getAllDrugEnterReport() throws IOException {
        List<Medicine> medicines = iReportService.getListDrugEnter();
        if (medicines.isEmpty()) {
            return new ResponseEntity<>("Không có thuốc cần nhập thêm", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Số lượng"};
        writeWorkbook(medicines, columnNames, "Thuốc cần nhập thêm", "D:\\thuoccannhapthem.xlsx");
        return new ResponseEntity<>("Báo cáo thuốc cần nhập thêm đã được tạo", HttpStatus.OK);
    }

    private ResponseEntity<?> getAllMedicinesExpiringSoon() throws IOException {
        List<IExpiredMedicineDTO> expiredMedicines = iReportService.findMedicinesExpiringSoon();
        if (expiredMedicines.isEmpty()) {
            return new ResponseEntity<>("Không có thuốc sắp hết hạn", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Hạn sử dụng"};
        writeWorkbook(expiredMedicines, columnNames, "Thuốc sắp hết hạn", "D:\\thuocsaphethan.xlsx");
        return new ResponseEntity<>(expiredMedicines, HttpStatus.OK);
    }

    private ResponseEntity<?> findTopSellingMedicines
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<ITopSellingMedicineDTO> iTopSellingMedicineDTOS = iReportService.findTopSellingMedicines
                (startDate, endDate, startTime, endTime);
        if (iTopSellingMedicineDTOS.isEmpty()) {
            return new ResponseEntity<>("Cửa hàng hiện chưa bán loại thuốc nào", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Số lượng bán ra"};
        writeWorkbook(iTopSellingMedicineDTOS, columnNames, "Thuốc bán chạy", "D:\\thuocbanchay.xlsx");
        return new ResponseEntity<>("Báo cáo thuốc bán chạy đã được tạo", HttpStatus.OK);
    }

    private void writeWorkbook(List<?> data, String[] columnNames, String sheetName, String fileName)
            throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnNames.length; i++) {
            headerRow.createCell(i).setCellValue(columnNames[i]);
        }

        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            if (data.get(i) instanceof Medicine) {
                Medicine medicine = (Medicine) data.get(i);
                row.createCell(0).setCellValue(medicine.getMedicineId());
                row.createCell(1).setCellValue(medicine.getMedicineName());
                row.createCell(2).setCellValue(medicine.getQuantity());
            } else if (data.get(i) instanceof IExpiredMedicineDTO) {
                IExpiredMedicineDTO medicine = (IExpiredMedicineDTO) data.get(i);
                row.createCell(0).setCellValue(medicine.getMedicineId());
                row.createCell(1).setCellValue(medicine.getMedicineName());
                row.createCell(2).setCellValue(medicine.getExpiredDate());
            } else if (data.get(i) instanceof ITopSellingMedicineDTO) {
                ITopSellingMedicineDTO medicine = (ITopSellingMedicineDTO) data.get(i);
                row.createCell(0).setCellValue(medicine.getMedicineId());
                row.createCell(1).setCellValue(medicine.getMedicineName());
                row.createCell(2).setCellValue(medicine.getTotalQuantity());
            } else if (data.get(i) instanceof IRevenueDTO) {
                IRevenueDTO revenueDTO = (IRevenueDTO) data.get(i);
                row.createCell(0).setCellValue(revenueDTO.getDate());
                row.createCell(1).setCellValue(revenueDTO.getRevenue());
            } else if (data.get(i) instanceof ISalesDiaryDTO) {
                ISalesDiaryDTO salesDiaryDTO = (ISalesDiaryDTO) data.get(i);
                row.createCell(0).setCellValue(salesDiaryDTO.getEmployeeName());
                row.createCell(1).setCellValue(salesDiaryDTO.getDateCreate());
                row.createCell(2).setCellValue(salesDiaryDTO.getInvoiceId());
                row.createCell(3).setCellValue(salesDiaryDTO.getTotal());
            } else if (data.get(i) instanceof ISupplierDTO) {
                ISupplierDTO supplier = (ISupplierDTO) data.get(i);
                row.createCell(0).setCellValue(supplier.getSupplierId());
                row.createCell(1).setCellValue(supplier.getSupplierName());
                row.createCell(2).setCellValue(supplier.getAddress());
                row.createCell(3).setCellValue(supplier.getEmail());
                row.createCell(4).setCellValue(supplier.getPhoneNumber());
                row.createCell(5).setCellValue(supplier.getToPayDebt());
            }
        }
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            workbook.write(fos);
        } finally {
            workbook.close();
        }
    }
}

