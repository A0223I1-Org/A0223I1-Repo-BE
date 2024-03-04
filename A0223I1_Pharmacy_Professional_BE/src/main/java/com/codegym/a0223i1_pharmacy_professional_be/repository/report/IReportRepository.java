package com.codegym.a0223i1_pharmacy_professional_be.repository.report;

import com.codegym.a0223i1_pharmacy_professional_be.dto.*;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IReportRepository extends JpaRepository<Medicine, String> {
    @Query("SELECT m FROM Medicine m WHERE m.quantity < 5")
    List<Medicine> getListDrugEnter();

    @Query(value = "SELECT m.medicine_id as medicineId, m.medicine_name as medicineName, DATE(w.expired_date) as expiredDate \n" +
            "FROM Medicine m\n" +
            "JOIN ware_in_detail w ON m.medicine_id = w.medicine_id \n" +
            "WHERE DATEDIFF(w.expired_date, CURRENT_DATE) <= 10" , nativeQuery = true)
    List<IExpiredMedicineDTO> findMedicinesExpiringSoon();

    @Query(nativeQuery = true, value =
            "SELECT m.medicine_id as medicineId , m.medicine_name as medicineName, SUM(id.quantity) as totalQuantity \n" +
                    "FROM medicine m \n" +
                    "JOIN invoice_detail id ON m.medicine_id = id.medicine_id\n" +
                    "LEFT JOIN invoice i ON id.invoice_id = i.invoice_id \n" +
                    "LEFT JOIN invoice_pres ip ON id.invoice_pres_id = ip.invoice_pres_id\n" +
                    "WHERE (\n" +
                    "    (DATE(i.date_create) BETWEEN :startDay AND :endDay) AND\n" +
                    "    (TIME(i.date_create) BETWEEN :startHour AND :endHour) OR\n" +
                    "    (DATE(ip.date_create) BETWEEN :startDay AND :endDay) AND\n" +
                    "    (TIME(ip.date_create) BETWEEN :startHour AND :endHour)\n" +
                    ")\n" +
                    "GROUP BY m.medicine_id, m.medicine_name \n" +
                    "ORDER BY totalQuantity DESC \n" +
                    "LIMIT 100")
    List<ITopSellingMedicineDTO> findTopSellingMedicines(@Param("startDay") String startDay
            , @Param("endDay") String endDay,@Param("startHour") String startHour,@Param("endHour") String endHour);


    @Query(nativeQuery = true, value =
            "SELECT DATE(date_create) as date, SUM(total) as revenue\n" +
                    "FROM (\n" +
                    "    SELECT date_create, total\n" +
                    "    FROM invoice\n" +
                    "    WHERE (\n" +
                    "        DATE(date_create)  BETWEEN :startDay AND :endDay AND\n" +
                    "        TIME(date_create) BETWEEN :startHour AND :endHour\n" +
                    "    )\n" +
                    "    UNION ALL\n" +
                    "    SELECT date_create, total\n" +
                    "    FROM invoice_pres\n" +
                    "    WHERE (\n" +
                    "        DATE(date_create)  BETWEEN :startDay AND :endDay AND\n" +
                    "        TIME(date_create) BETWEEN :startHour AND :endHour\n" +
                    "    )\n" +
                    ") AS combined\n" +
                    "GROUP BY date\n" +
                    "ORDER BY date")
    List<IRevenueDTO> revenue(@Param("startDay") String startDay, @Param("endDay") String endDay
            ,@Param("startHour") String startHour,@Param("endHour") String endHour);

    @Query(nativeQuery = true, value =
            "SELECT date(date) as date,SUM(profit) AS 'revenue'\n" +
                    "FROM\n" +
                    "(SELECT invoice.date_create AS 'date',\n" +
                    "        SUM(invoice_detail.quantity * invoice_detail.price) - SUM(invoice_detail.quantity * (medicine.import_price/medicine.conversion_rate)) AS 'profit'\n" +
                    "    FROM invoice \n" +
                    "    JOIN invoice_detail ON invoice.invoice_id = invoice_detail.invoice_id \n" +
                    "    JOIN medicine ON invoice_detail.medicine_id = medicine.medicine_id\n" +
                    "    WHERE (\n" +
                    "        DATE(date_create)  BETWEEN :startDay AND :endDay AND\n" +
                    "        TIME(date_create) BETWEEN :startHour AND :endHour\n" +
                    "    )\n" +
                    "    GROUP BY date\n" +
                    "    UNION ALL\n" +
                    "    SELECT invoice_pres.date_create AS 'date',\n" +
                    "        SUM(invoice_detail.quantity * invoice_detail.price) - SUM(invoice_detail.quantity * (medicine.import_price/medicine.conversion_rate)) AS 'profit'\n" +
                    "    FROM invoice_pres \n" +
                    "    JOIN invoice_detail ON invoice_pres.invoice_pres_id = invoice_detail.invoice_pres_id \n" +
                    "    JOIN medicine ON invoice_detail.medicine_id = medicine.medicine_id\n" +
                    "    WHERE (\n" +
                    "        DATE(date_create)  BETWEEN :startDay AND :endDay AND\n" +
                    "        TIME(date_create) BETWEEN :startHour AND :endHour\n" +
                    "    )\n" +
                    "    GROUP BY date\n" +
                    ") AS combined GROUP BY date\n")
    List<IRevenueDTO> profit(@Param("startDay") String startDay, @Param("endDay") String endDay
            ,@Param("startHour") String startHour,@Param("endHour") String endHour);


    @Query(nativeQuery = true, value =
            "SELECT e.employee_name as employeeName , \n" +
                    "       i.date_create as dateCreate, \n" +
                    "       i.invoice_id as invoiceId, \n" +
                    "       i.total\n" +
                    "FROM invoice i\n" +
                    "JOIN employee e ON i.employee_id = e.employee_id\n" +
                    "    WHERE (\n" +
                    "        DATE(date_create)  BETWEEN :startDay AND :endDay AND\n" +
                    "        TIME(date_create) BETWEEN :startHour AND :endHour\n" +
                    "    )\n" +
                    "UNION ALL\n" +
                    "SELECT e.employee_name, \n" +
                    "       ip.date_create, \n" +
                    "       ip.invoice_pres_id, \n" +
                    "       ip.total\n" +
                    "FROM invoice_pres ip\n" +
                    "JOIN employee e ON ip.employee_id = e.employee_id\n" +
                    "    WHERE (\n" +
                    "        DATE(date_create)  BETWEEN :startDay AND :endDay AND\n" +
                    "        TIME(date_create) BETWEEN :startHour AND :endHour\n" +
                    "    )\n" +
                    "ORDER BY dateCreate")
    List<ISalesDiaryDTO> salesDiary(@Param("startDay") String startDay, @Param("endDay") String endDay
            , @Param("startHour") String startHour, @Param("endHour") String endHour);

    @Query(nativeQuery = true, value =
            "SELECT supplier_id as supplierId, supplier_name as supplierName, address, email, phone_number as phoneNumber, to_pay_debt as toPayDebt\n" +
                    "FROM supplier\n" +
                    "WHERE to_pay_debt > 0 and delete_flag=0 "+
                    "ORDER BY to_pay_debt DESC")
    List<ISupplierDTO> getDebtSuppliers();

    @Query(nativeQuery = true, value =
            "SELECT date(date) AS 'date',SUM(revenue) AS 'revenue',SUM(profit) AS 'profit'\n" +
                    "   FROM(SELECT invoice.date_create AS 'date',\n" +
                    "        SUM(invoice_detail.quantity * invoice_detail.price)  AS 'revenue',\n" +
                    "        SUM(invoice_detail.quantity * invoice_detail.price) - SUM(invoice_detail.quantity * (medicine.import_price/medicine.conversion_rate)) AS 'profit'\n" +
                    "    FROM invoice \n" +
                    "    JOIN invoice_detail ON invoice.invoice_id = invoice_detail.invoice_id \n" +
                    "    JOIN medicine ON invoice_detail.medicine_id = medicine.medicine_id\n" +
                    "    GROUP BY invoice.date_create\n" +
                    "    UNION ALL\n" +
                    "    SELECT invoice_pres.date_create AS 'date',\n" +
                    "        SUM(invoice_detail.quantity * invoice_detail.price)  AS 'revenue',\n" +
                    "        SUM(invoice_detail.quantity * invoice_detail.price) - SUM(invoice_detail.quantity * (medicine.import_price/medicine.conversion_rate)) AS 'profit'\n" +
                    "    FROM invoice_pres \n" +
                    "    JOIN invoice_detail ON invoice_pres.invoice_pres_id = invoice_detail.invoice_pres_id \n" +
                    "    JOIN medicine ON invoice_detail.medicine_id = medicine.medicine_id\n" +
                    "    GROUP BY invoice_pres.date_create\n" +
                    ") AS combined\n" +
                    "GROUP BY date")
    List<IRevenueProfitDTO> getRevenueAndProfit(@Param("chartType") String chartType, @Param("startDate") String startDate
            , @Param("endDate") String endDate);


}
