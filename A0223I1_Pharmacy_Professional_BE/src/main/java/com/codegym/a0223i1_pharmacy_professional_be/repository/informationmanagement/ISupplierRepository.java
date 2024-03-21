package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Supplier;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, String> {

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 " +
            "AND (?1 IS NULL OR supplier.supplier_id LIKE CONCAT('%', ?1, '%')) " +
            "AND (?2 IS NULL OR supplier.supplier_name LIKE CONCAT('%', ?2, '%')) " +
            "AND (?3 IS NULL OR supplier.address LIKE CONCAT('%', ?3, '%')) " +
            "AND (?4 IS NULL OR supplier.phone_number LIKE CONCAT('%', ?4, '%')) " +
            "ORDER BY supplier.supplier_id", nativeQuery = true)
    Page<Supplier> findAllBySupplierId(String id, String supplierName, String address, String phoneNumber, Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 " +
            "AND (?1 IS NULL OR supplier.supplier_id LIKE CONCAT('%', ?1, '%')) " +
            "AND (?2 IS NULL OR supplier.supplier_name LIKE CONCAT('%', ?2, '%')) " +
            "AND (?3 IS NULL OR supplier.address LIKE CONCAT('%', ?3, '%')) " +
            "AND (?4 IS NULL OR supplier.phone_number LIKE CONCAT('%', ?4, '%')) " +
            "ORDER BY supplier.supplier_name", nativeQuery = true)
    Page<Supplier> findAllBySupplierName(String id, String supplierName, String address, String phoneNumber, Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 " +
            "AND (?1 IS NULL OR supplier.supplier_id LIKE CONCAT('%', ?1, '%')) " +
            "AND (?2 IS NULL OR supplier.supplier_name LIKE CONCAT('%', ?2, '%')) " +
            "AND (?3 IS NULL OR supplier.address LIKE CONCAT('%', ?3, '%')) " +
            "AND (?4 IS NULL OR supplier.phone_number LIKE CONCAT('%', ?4, '%')) " +
            "ORDER BY supplier.address", nativeQuery = true)
    Page<Supplier> findAllByAddress(String id, String supplierName, String address, String phoneNumber, Pageable pageable);

    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.delete_flag = 0 " +
            "AND (?1 IS NULL OR supplier.supplier_id LIKE CONCAT('%', ?1, '%')) " +
            "AND (?2 IS NULL OR supplier.supplier_name LIKE CONCAT('%', ?2, '%')) " +
            "AND (?3 IS NULL OR supplier.address LIKE CONCAT('%', ?3, '%')) " +
            "AND (?4 IS NULL OR supplier.phone_number LIKE CONCAT('%', ?4, '%')) " +
            "ORDER BY supplier.phone_number", nativeQuery = true)
    Page<Supplier> findAllByPhoneNumber(String id, String supplierName, String address, String phoneNumber, Pageable pageable);


    @Query(value = "SELECT supplier_id, supplier_name, address, phone_number, to_pay_debt, note, delete_flag, email " +
            "FROM a0223i1_pharmacy.supplier " +
            "WHERE supplier.supplier_id = ?1" , nativeQuery = true)
    Supplier findSupplierById(String id);

    @Query(value = "SELECT s FROM Supplier s WHERE " +
            "s.deleteFlag = false AND " +
            "(:supplierId IS NULL OR s.supplierId LIKE CONCAT('%', :supplierId, '%')) " +
            "AND (:supplierName IS NULL OR s.supplierName LIKE CONCAT('%', :supplierName, '%')) " +
            "AND (:address IS NULL OR s.address LIKE CONCAT('%', :address, '%')) " +
            "AND (:phoneNumber IS NULL OR s.phoneNumber LIKE CONCAT('%', :phoneNumber, '%')) " +
            "ORDER BY " +
            "CASE " +
            "WHEN :orderBy = 'supplierId' THEN s.supplierId " +
            "WHEN :orderBy = 'supplierName' THEN s.supplierName " +
            "WHEN :orderBy = 'address' THEN s.address " +
            "WHEN :orderBy = 'phoneNumber' THEN s.phoneNumber " +
            "ELSE s.supplierId END")
    Page<Supplier> findAllWithSortingAndFiltering(
            @Param("supplierId") String supplierId,
            @Param("supplierName") String supplierName,
            @Param("address") String address,
            @Param("phoneNumber") String phoneNumber,
            @Param("orderBy") String orderBy,
            Pageable pageable);


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