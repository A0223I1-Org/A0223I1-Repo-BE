package com.codegym.a0223i1_pharmacy_professional_be.repository.report;

import com.codegym.a0223i1_pharmacy_professional_be.dto.IExpiredMedicineDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.IRevenueDTO;
import com.codegym.a0223i1_pharmacy_professional_be.dto.ITopSellingMedicineDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IReportRepository extends JpaRepository<Medicine, String> {
    @Query("SELECT m FROM Medicine m WHERE m.quantity < 5")
    List<Medicine> getListDrugEnter();

    @Query(value = "SELECT m.medicine_id as medicineId, m.medicine_name as medicineName, w.expired_date as expiredDate \n" +
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



}

//    @Query("SELECT e.employee_name, h.invoice_date, SUM(d.quantity * d.unit_price) as total\n" +
//            "FROM employee e\n" +
//            "JOIN (\n" +
//            "    SELECT * FROM invoice\n" +
//            "    UNION ALL\n" +
//            "    SELECT * FROM invoice_pres\n" +
//            ") h ON e.employee_id = h.employee_id\n" +
//            "JOIN invoice_detail d ON h.invoice_id = d.invoice_id\n" +
//            "WHERE h.invoice_date BETWEEN :startDateTime AND :endDateTime\n" +
//            "GROUP BY e.employee_name, h.invoice_date\n" +
//            "ORDER BY e.employee_name;")
//    List<ISalesDiaryDTO> getSalesDiary(@Param("startDateTime") Date startDateTime, @Param("endDateTime") Date endDateTime);
