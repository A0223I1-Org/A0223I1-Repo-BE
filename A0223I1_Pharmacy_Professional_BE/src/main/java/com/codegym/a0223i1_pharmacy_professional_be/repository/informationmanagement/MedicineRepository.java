package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.FlatMedicineDto;
import com.codegym.a0223i1_pharmacy_professional_be.dto.MedicineDto;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import jakarta.transaction.Transactional;
import org.hibernate.mapping.List;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface MedicineRepository extends JpaRepository<Medicine, String> {

    @Query("SELECT m FROM Medicine m JOIN FETCH m.medicineImgs WHERE m.medicineId = :id")
    Medicine findMedicineByMedicineId(String id);

}

