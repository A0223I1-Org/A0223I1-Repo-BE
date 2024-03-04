package com.codegym.a0223i1_pharmacy_professional_be.dto;
import java.util.Date;

public interface IExpiredMedicineDTO {
    String getMedicineId();
    String getMedicineName();
    Date getExpiredDate();
}
