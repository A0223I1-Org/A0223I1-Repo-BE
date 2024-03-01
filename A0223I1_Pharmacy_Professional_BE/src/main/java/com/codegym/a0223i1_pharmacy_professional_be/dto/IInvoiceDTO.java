package com.codegym.a0223i1_pharmacy_professional_be.dto;

import com.codegym.a0223i1_pharmacy_professional_be.entity.*;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IInvoiceDTO {
     String getInvoiceId();
     Date getDate();
     Time getTime();
     Float getTotal();
     String getCustomerName();

}
