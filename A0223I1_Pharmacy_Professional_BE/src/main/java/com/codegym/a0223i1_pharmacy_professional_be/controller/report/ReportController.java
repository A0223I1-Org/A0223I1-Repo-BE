package com.codegym.a0223i1_pharmacy_professional_be.controller.report;

import com.codegym.a0223i1_pharmacy_professional_be.dto.IExpiredMedicineDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.IRevenueDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.ITopSellingMedicineDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.repository.report.IReportRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ReportController {
    @Autowired
    IReportRepository iReportRepository;

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
            case "revenue":
                return revenue(startDate, endDate, startTime, endTime);
            default:
                return new ResponseEntity<>("Loại báo cáo không hợp lệ", HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> getAllDrugEnterReport() throws IOException {
        List<Medicine> medicines = iReportRepository.getListDrugEnter();
        if (medicines.isEmpty()) {
            return new ResponseEntity<>("Không có thuốc cần nhập thêm", HttpStatus.NOT_FOUND);
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Thuốc cần nhập thêm");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã thuốc");
        headerRow.createCell(1).setCellValue("Tên thuốc");
        headerRow.createCell(2).setCellValue("Số lượng");

        for (int i = 0; i < medicines.size(); i++) {
            Medicine medicine = medicines.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(medicine.getMedicineId());
            row.createCell(1).setCellValue(medicine.getMedicineName());
            row.createCell(2).setCellValue(medicine.getQuantity());
        }

        try (FileOutputStream fos = new FileOutputStream("D:\\thuoccannhapthem.xlsx")) {
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        workbook.close();
        return new ResponseEntity<>("Báo cáo thuốc cần nhập thêm đã được tạo", HttpStatus.OK);
    }

    private ResponseEntity<?> getAllMedicinesExpiringSoon() throws IOException {
        List<IExpiredMedicineDTO> expiredMedicines = iReportRepository.findMedicinesExpiringSoon();

        if (expiredMedicines.isEmpty()) {
            return new ResponseEntity<>("Không có thuốc nào sắp hết hạn", HttpStatus.NOT_FOUND);
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Thuốc sắp hết hạn");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã thuốc");
        headerRow.createCell(1).setCellValue("Tên thuốc");
        headerRow.createCell(2).setCellValue("Hạn sử dụng");

        for (int i = 0; i < expiredMedicines.size(); i++) {
            IExpiredMedicineDTO medicine = expiredMedicines.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(medicine.getMedicineId());
            row.createCell(1).setCellValue(medicine.getMedicineName());
            row.createCell(2).setCellValue(medicine.getExpiredDate());
        }

        try (FileOutputStream fos = new FileOutputStream("D:\\thuocsaphethan.xlsx")) {
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        workbook.close();
        return new ResponseEntity<>(expiredMedicines, HttpStatus.OK);
    }

    public ResponseEntity<?> findTopSellingMedicines
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<ITopSellingMedicineDTO> iTopSellingMedicineDTOS = iReportRepository.findTopSellingMedicines
                (startDate, endDate, startTime, endTime);
        if (iTopSellingMedicineDTOS.isEmpty()) {
            return new ResponseEntity<>("Cửa hàng hiện chưa bán loại thuốc nào", HttpStatus.NOT_FOUND);
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Thuốc bán chạy");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã thuốc");
        headerRow.createCell(1).setCellValue("Tên thuốc");
        headerRow.createCell(2).setCellValue("Số lượng bán ra");

        for (int i = 0; i < iTopSellingMedicineDTOS.size(); i++) {
            ITopSellingMedicineDTO medicine = iTopSellingMedicineDTOS.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(medicine.getMedicineId());
            row.createCell(1).setCellValue(medicine.getMedicineName());
            row.createCell(2).setCellValue(medicine.getTotalQuantity());
        }

        try (FileOutputStream fos = new FileOutputStream("D:\\thuocbanchay.xlsx")) {
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        workbook.close();
        return new ResponseEntity<>(iTopSellingMedicineDTOS, HttpStatus.OK);
    }

    private ResponseEntity<?> revenue
            (String startDate, String endDate, String startTime, String endTime) throws IOException {
        List<IRevenueDTO> revenue = iReportRepository.revenue(startDate,endDate,startTime,endTime);
        if (revenue.isEmpty()) {
            return new ResponseEntity<>("Cửa hàng chưa bán được trong khoảng thời gian trên", HttpStatus.NOT_FOUND);
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Doanh thu");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Thời gian");
        headerRow.createCell(1).setCellValue("Doanh thu");

        for (int i = 0; i < revenue.size(); i++) {
            IRevenueDTO revenueDTO = revenue.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(revenueDTO.getDate());
            row.createCell(1).setCellValue(revenueDTO.getRevenue());
        }

        try (FileOutputStream fos = new FileOutputStream("D:\\doanhthu.xlsx")) {
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        workbook.close();
        return new ResponseEntity<>("Báo cáo doanh thu đã được tạo", HttpStatus.OK);
    }
}
