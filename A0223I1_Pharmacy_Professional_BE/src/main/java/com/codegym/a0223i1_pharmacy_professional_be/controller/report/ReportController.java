package com.codegym.a0223i1_pharmacy_professional_be.controller.report;

import com.codegym.a0223i1_pharmacy_professional_be.dto.*;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.report.IReportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ReportController {

    @Autowired
    IReportService iReportService;

    @RequestMapping(value = "/revenue-profit", method = RequestMethod.GET)
    public ResponseEntity<?> getRevenueAndProfit
            (@RequestParam String chartType, @RequestParam String startDate, @RequestParam String endDate) {
        if (chartType.equalsIgnoreCase("year")) {
            List<IRevenueProfitDTO> iRevenueProfitDTOS = iReportService.getRevenueAndProfitByYear(startDate, endDate);
            return new ResponseEntity<>(iRevenueProfitDTOS, HttpStatus.OK);
        } else {
            List<IRevenueProfitDTO> iRevenueProfitDTOS = iReportService.getRevenueAndProfit(startDate, endDate);
            return new ResponseEntity<>(iRevenueProfitDTOS, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/generate-report", method = RequestMethod.GET)
    public ResponseEntity<?> generateReport
            (@RequestParam String reportType, @RequestParam String startDate, @RequestParam String endDate,
             @RequestParam String startTime, @RequestParam String endTime) throws IOException {
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
                return getDebtSuppliers(startDate, endDate, startTime, endTime);
            case "revenue":
                return revenue(startDate, endDate, startTime, endTime);
            case "profit":
                return profit(startDate, endDate, startTime, endTime);
            default:
                return new ResponseEntity<>("Loại báo cáo không hợp lệ", HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> getAllDrugEnterReport() throws IOException {
        List<Medicine> medicines = iReportService.getListDrugEnter();
        if (medicines.isEmpty()) {
            return new ResponseEntity<>("Hiện không có thuốc nào cần nhập thêm", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Số lượng còn lại"};
        String filePath = "D:\\thuoccannhapthem.xlsx";
        writeWorkbook(medicines, columnNames, "Thuốc cần nhập thêm", filePath);
        return new ResponseEntity<>("Báo cáo thuốc cần nhập thêm đã được xuất thành công . File lưu tại: "+filePath, HttpStatus.OK);
    }

    private ResponseEntity<?> getAllMedicinesExpiringSoon() throws IOException {
        List<IExpiredMedicineDTO> expiredMedicines = iReportService.findMedicinesExpiringSoon();
        if (expiredMedicines.isEmpty()) {
            return new ResponseEntity<>("Hiện không có thuốc nào sắp hết hạn", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Hạn sử dụng"};
        String filePath = "D:\\thuocsaphethan.xlsx";
        writeWorkbook(expiredMedicines, columnNames, "Thuốc sắp hết hạn", filePath);
        return new ResponseEntity<>("Báo cáo thuốc sắp hết hạn đã được xuất thành công. File lưu tại:"+filePath, HttpStatus.OK);
    }

    private ResponseEntity<?> findTopSellingMedicines
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<ITopSellingMedicineDTO> iTopSellingMedicineDTOS = iReportService.findTopSellingMedicines
                (startDate, endDate, startTime, endTime);
        if (iTopSellingMedicineDTOS.isEmpty()) {
            return new ResponseEntity<>("Không có thuốc nào được bán trong khoảng thời gian đã chọn", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Số lượng bán ra"};
        String filePath = "D:\\thuocbanchay.xlsx";
        writeWorkbook(iTopSellingMedicineDTOS, columnNames, "Thuốc bán chạy", filePath);
        return new ResponseEntity<>("Báo cáo thuốc bán chạy đã được xuất thành công. File lưu tại: "+filePath, HttpStatus.OK);
    }

    private ResponseEntity<?> salesDiary
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<ISalesDiaryDTO> iSalesDiaryDTOS = iReportService.salesDiary(startDate, endDate, startTime, endTime);
        if (iSalesDiaryDTOS.isEmpty()) {
            return new ResponseEntity<>("Không có dữ liệu nhật ký bán hàng trong khoảng thời gian đã chọn", HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã nhân viên", "Tên nhân viên", "Mã hóa đơn", "Ngày tạo", "Tên bác sĩ", "Số điện thoại", "Triệu chứng", "Chuẩn đoán", "Ghi chú", "Tổng tiền"};
        writeWorkbook(iSalesDiaryDTOS, columnNames, "Nhật ký bán hàng", "D:\\nhatkybanhang.xlsx");
        return new ResponseEntity<>("Báo cáo nhật ký bán hàng đã được xuất thành công trong khoảng thời gian đã chọn", HttpStatus.OK);
    }

    private ResponseEntity<?> getDebtSuppliers
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<ISupplierDTO> suppliers = iReportService.getDebtSuppliers(startDate, endDate, startTime, endTime);
        if (suppliers.isEmpty()) {
            return new ResponseEntity<>("Hiện không có nhà cung cấp nào có công nợ trong khoảng thời gian đã chọn",
                    HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Email", "Số điện thoại", "Công nợ"};
        String filePath = "D:\\congno.xlsx";
        writeWorkbook(suppliers, columnNames, "Nhật ký bán hàng", filePath);
        return new ResponseEntity<>("Báo cáo công nợ của các nhà cung cấp đã được xuất thành công. File lưu tại: "+filePath, HttpStatus.OK);
    }

    private ResponseEntity<?> profit
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<IRevenueDTO> profit = iReportService.profit(startDate, endDate, startTime, endTime);
        boolean allZero = profit.stream().noneMatch(item -> !item.getRevenue().equalsIgnoreCase("0.0") && !item.getRevenue().equals(0.0));

        if (allZero) {
            String message = "Hiện tại, cửa hàng chưa bán được sản phẩm nào trong khoảng thời gian đã chọn, không có dữ liệu lợi nhuận.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Thời gian", "Lợi nhuận"};
        String filePath = "D:\\loinhuan.xlsx";
        writeWorkbook(profit, columnNames, "Lợi nhuận", filePath);
        String successMessage = "Báo cáo lợi nhuận đã được xuất thành công. File lưu tại: "+filePath;
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    private ResponseEntity<?> revenue
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<IRevenueDTO> revenue = iReportService.revenue(startDate, endDate, startTime, endTime);
        boolean allZero = revenue.stream().noneMatch(item -> !item.getRevenue().equalsIgnoreCase("0.0") && !item.getRevenue().equals(0.0));

        if (allZero) {
            String message = "Hiện tại, cửa hàng chưa bán được sản phẩm nào trong khoảng thời gian đã chọn, không có dữ liệu doanh thu.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        String[] columnNames = {"Thời gian", "Doanh thu"};
        String filePath = "D:\\doanhthu.xlsx";

        writeWorkbook(revenue, columnNames, "Doanh thu", filePath);
        return new ResponseEntity<>("Báo cáo doanh thu đã được xuất thành công. File lưu tại: "+filePath, HttpStatus.OK);
    }

    private void writeWorkbook(List<?> data, String[] columnNames, String sheetName, String fileName)
            throws IOException, FileNotFoundException {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fos = new FileOutputStream(fileName);) {
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
                    row.createCell(2).setCellValue(medicine.getExpiredDate().toString());
                } else if (data.get(i) instanceof ITopSellingMedicineDTO) {
                    ITopSellingMedicineDTO medicine = (ITopSellingMedicineDTO) data.get(i);
                    row.createCell(0).setCellValue(medicine.getMedicineId());
                    row.createCell(1).setCellValue(medicine.getMedicineName());
                    row.createCell(2).setCellValue(medicine.getTotalQuantity());
                } else if (data.get(i) instanceof IRevenueDTO) {
                    IRevenueDTO revenueDTO = (IRevenueDTO) data.get(i);
                    row.createCell(0).setCellValue(revenueDTO.getDate());
                    double revenueValue = revenueDTO.getRevenue() != null ? Double.parseDouble(revenueDTO.getRevenue()) : 0;
                    String revenue = String.format("%,.0f", revenueValue).replace(',', '.') + " ₫";
                    row.createCell(1).setCellValue(revenue);
                } else if (data.get(i) instanceof ISalesDiaryDTO) {
                    ISalesDiaryDTO salesDiaryDTO = (ISalesDiaryDTO) data.get(i);
                    row.createCell(0).setCellValue(salesDiaryDTO.getEmployeeId());
                    row.createCell(1).setCellValue(salesDiaryDTO.getEmployeeName());
                    row.createCell(2).setCellValue(salesDiaryDTO.getInvoiceId());
                    row.createCell(3).setCellValue(salesDiaryDTO.getSaleDate());
                    row.createCell(4).setCellValue(salesDiaryDTO.getDoctorName());
                    row.createCell(5).setCellValue(salesDiaryDTO.getDoctorPhone());
                    row.createCell(6).setCellValue(salesDiaryDTO.getSymptom());
                    row.createCell(7).setCellValue(salesDiaryDTO.getDoctorDiagnosis());
                    row.createCell(8).setCellValue(salesDiaryDTO.getNote());
                    String total = String.format("%,.0f ₫", Double.parseDouble(salesDiaryDTO.getTotalInvoiceAmount()))
                            .replace(',', '.');
                    Cell totalCell = row.createCell(9);
                    totalCell.setCellValue(total);
                } else if (data.get(i) instanceof ISupplierDTO) {
                    ISupplierDTO supplier = (ISupplierDTO) data.get(i);
                    row.createCell(0).setCellValue(supplier.getSupplierId());
                    row.createCell(1).setCellValue(supplier.getSupplierName());
                    row.createCell(2).setCellValue(supplier.getAddress());
                    row.createCell(3).setCellValue(supplier.getEmail());
                    row.createCell(4).setCellValue(supplier.getPhoneNumber());
                    String debt = String.format("%,.0f ₫", supplier.getToPayDebt()).replace(',', '.');
                    Cell debtCell = row.createCell(5);
                    debtCell.setCellValue(debt);
                }
            }
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            String errorMessage = "Không thể ghi vào tệp vì nó đang được sử dụng bởi một quá trình khác. Vui lòng đảm bảo rằng tệp không đang được mở và thử lại.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

