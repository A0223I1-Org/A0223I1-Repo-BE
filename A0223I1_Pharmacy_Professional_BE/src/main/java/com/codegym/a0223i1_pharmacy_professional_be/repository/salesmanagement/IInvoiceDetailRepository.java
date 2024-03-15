package com.codegym.a0223i1_pharmacy_professional_be.repository.salesmanagement;


import com.codegym.a0223i1_pharmacy_professional_be.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {

}