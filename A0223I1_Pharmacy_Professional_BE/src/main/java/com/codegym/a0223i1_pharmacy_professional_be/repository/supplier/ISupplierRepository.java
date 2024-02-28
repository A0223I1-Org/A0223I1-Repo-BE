package com.codegym.a0223i1_pharmacy_professional_be.repository.supplier;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, String> {

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 " +
            "ORDER BY supplier_id", nativeQuery = true)
    Page<Supplier> findAllOrderBySupplierId(Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 " +
            "ORDER BY supplier_name", nativeQuery = true)
    Page<Supplier> findAllOrderBySupplierName(Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 " +
            "ORDER BY address", nativeQuery = true)
    Page<Supplier> findAllOrderByAddress(Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 " +
            "ORDER BY phone_number", nativeQuery = true)
    Page<Supplier> findAllOrderByPhoneNumber(Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 AND supplier.supplier_id LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    Page<Supplier> findAllBySupplierId(String id, Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    Page<Supplier> findAllBySupplierName(String supplierName, Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    Page<Supplier> findAllByAddress(String address, Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    Page<Supplier> findAllByPhoneNumber(String phoneNumber, Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.supplier_id = ?1" , nativeQuery = true)
    Supplier findSupplierById(String id);

    @Modifying
    @Query(value = "UPDATE a0223i1_pharmacy.supplier " +
            "SET supplier.delete_flag = 1 " +
            "WHERE supplier_id = ?1",nativeQuery = true)
    void deleteById(String id);

    @Modifying
    @Query(value = "INSERT INTO supplier(supplier_id, supplier_name, address, email, phone_number, note, delete_flag, to_pay_debt) " +
            "VALUE (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void addNewSupplier(String supplierId, String supplierName, String address, String email, String phoneNumber, String note, Integer deleteFlag, int toPayDebt);

    @Modifying
    @Query(value = "UPDATE supplier SET supplier.supplier_id = ?1, supplier.supplier_name = ?2, supplier.address = ?3, supplier.email = ?4, supplier.phone_number = ?5, supplier.note = ?6 , supplier.delete_flag = ?7 , supplier.to_pay_debt = ?8 " +
            "WHERE supplier.supplier_id = ?9", nativeQuery = true)
    void editSupplier(String newId, String supplierName, String address, String email, String phoneNumber, String note, int deleteFlag, int toPayDebt, String oldId);
}
