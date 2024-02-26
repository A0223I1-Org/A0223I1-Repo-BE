package com.codegym.a0223i1_pharmacy_professional_be.controller.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.MedicineInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicine")
@CrossOrigin("*")
public class MedicineInformationManagementController {
    //
    @Autowired
    MedicineInformationService medicineService;

    @GetMapping()
    public ResponseEntity<List<Medicine>> getAllMedicine() {
        return new ResponseEntity<>(medicineService.getAllMedicine(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable String id) {
        return new ResponseEntity<>(medicineService.findMedicineById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicineById(@PathVariable String id) {
        System.out.println(id);

        Medicine existingMedicine = medicineService.findMedicineById(id);

        if (existingMedicine == null) {
            return new ResponseEntity<>("Không tìm thấy Medicine có ID: " + id, HttpStatus.NOT_FOUND);
        }

        medicineService.deleteMedicine(existingMedicine);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
