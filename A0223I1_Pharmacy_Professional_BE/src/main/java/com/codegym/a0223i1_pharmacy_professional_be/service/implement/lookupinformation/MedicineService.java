package com.codegym.a0223i1_pharmacy_professional_be.service.implement.lookupinformation;

import com.codegym.a0223i1_pharmacy_professional_be.dto.MedicineDto;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.MedicineRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;

@Service
public class MedicineService implements IMedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

//    ModelMapper mapper = new ModelMapper();

    @Override
    public MedicineDto findMedicineById(String id) {
//        return medicineRepository.findMedicineByMedicineId(id);
        return mapToDto(medicineRepository.findMedicineByMedicineId(id));
    }

    private MedicineDto mapToDto(Medicine medicine){
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setMedicineId(medicine.getMedicineId());
        medicineDto.setMedicineName(medicine.getMedicineName());
        medicineDto.setMaterial(medicine.getMaterial());
        medicineDto.setActiveIngredient(medicine.getActiveIngredient());
        medicineDto.setUnit(medicine.getUnit());
        medicineDto.setConversionUnit(medicine.getConversionUnit());
        medicineDto.setQuantity(medicine.getQuantity());
        medicineDto.setImportPrice(medicine.getImportPrice());
        medicineDto.setWholesalePrice(medicine.getWholesalePrice());
        medicineDto.setRetailPrice(medicine.getRetailPrice());
        medicineDto.setSupplierDiscount(medicine.getSupplierDiscount());
        medicineDto.setProfitMarginWholesale(medicine.getProfitMarginWholesale());
        medicineDto.setProfitMarginRetail(medicine.getProfitMarginRetail());
        medicineDto.setOrigin(medicine.getOrigin());
        medicineDto.setStatus(medicine.getStatus());

        for (int i = 0; i < medicine.getMedicineImgs().size(); i++) {
            medicineDto.getMedicineImgLinkList().add(medicine.getMedicineImgs().get(i).getLink());
        }


        return medicineDto;
    }
}
