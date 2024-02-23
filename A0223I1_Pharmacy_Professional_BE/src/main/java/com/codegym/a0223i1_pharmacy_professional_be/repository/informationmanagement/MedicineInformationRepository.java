package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineInformationRepository extends JpaRepository<Medicine, String> {
}
