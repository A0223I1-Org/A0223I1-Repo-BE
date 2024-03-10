package com.codegym.a0223i1_pharmacy_professional_be.dto;

import java.sql.*;

public interface InvoiceListViewDTO {
    String getInvoice_id();

    String getCustomer_id();

    String getEmployee_id();

    Date getCreate_date();

    Time getCreate_time();

    Integer getStatus();

    Float getTotal();

    String getnote();

    String getCustomer_name();

    String getEmployee_name();

    String getInvoice_type();
}
