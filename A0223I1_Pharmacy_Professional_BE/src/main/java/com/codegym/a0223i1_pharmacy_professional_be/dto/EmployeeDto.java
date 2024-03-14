package com.codegym.a0223i1_pharmacy_professional_be.dto;

import java.util.Date;

public interface EmployeeDto {
     String getEmployee_id();
     String getEmployee_name();

     String getPhone_number();

     Date getDate_start();

     String getAddress();

     String getNote();

     int getSalary();
     String getImage();
     Integer getAccount_id();
     String getEmail();
     String getPassword();
     Long getRole_id();
}
