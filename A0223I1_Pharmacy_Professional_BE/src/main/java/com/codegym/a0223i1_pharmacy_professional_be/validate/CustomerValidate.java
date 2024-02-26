package com.codegym.a0223i1_pharmacy_professional_be.validate;

import com.codegym.a0223i1_pharmacy_professional_be.dto.CustomerDTO;
import com.codegym.a0223i1_pharmacy_professional_be.repository.customermanagement.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class CustomerValidate {
    @Autowired
    private ICustomerRepository teacherRepository;

    private final Pattern PATTERN_NAME = Pattern.compile("^[a-zA-Z\\s]+$");
    private final Pattern PATTERN_ADDRESS = Pattern.compile("^[a-zA-Z\\s]+$");
//    private final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    private final Pattern PATTERN_PHONE = Pattern.compile("^[0-9]+$");

    public Map<String,String> validate(CustomerDTO createUpdateTeacherDTO) {
        Map<String,String> errors = new HashMap<>();
        if (createUpdateTeacherDTO.getCustomerName() == null || createUpdateTeacherDTO.getCustomerName().isEmpty()) {
            errors.put("errorNameEmpty","Tên giáo viên không được trống");
        }
        if (createUpdateTeacherDTO.getCustomerName().length() > 50) {
            errors.put("errorNameLength","Tên không được quá 50 ký tự");
        }
        if (!PATTERN_NAME.matcher(createUpdateTeacherDTO.getCustomerName()).matches()) {
            errors.put("errorNameSpecialCharacter","Tên không được chứa các kí tự đặc biệt");
        }

        LocalDate now = LocalDate.now();
        int age =createUpdateTeacherDTO.getAge();
        if (age < 18) {
            errors.put("errorDateMin","Giáo viên không được nhỏ hơn 18 tuổi");
        }
        if (age > 50) {
            errors.put("errorDateMax","Giáo viên không được lớn hơn 50 tuổi");
        }

        if (createUpdateTeacherDTO.getPhoneNumber() == null || createUpdateTeacherDTO.getPhoneNumber().isEmpty()) {
            errors.put("errorPhoneEmpty","Số điện thoại không được để trống");
        }
        if (createUpdateTeacherDTO.getPhoneNumber().length() != 10) {
            errors.put("errorPhoneLength","Số điện thoại phải có 10 ký tự");
        }
        if (!PATTERN_PHONE.matcher(createUpdateTeacherDTO.getPhoneNumber()).matches()) {
            errors.put("errorPhoneNumber","Số điện thoại chỉ được chứa số");
        }

        if (createUpdateTeacherDTO.getAddress() == null || createUpdateTeacherDTO.getAddress().isEmpty()) {
            errors.put("errorEmailEmpty","Email không được để trống");
        }
        if (createUpdateTeacherDTO.getAddress().length() > 50) {
            errors.put("errorEmailLength","Email không được quá 50 ký tự");
        }
        if (!PATTERN_ADDRESS.matcher(createUpdateTeacherDTO.getAddress()).matches()) {
            errors.put("errorEmailFormat","Email không hợp lệ");
        }


//        List<CustomerDTO> teachers = teacherRepository.getTeacherByPhone(createUpdateTeacherDTO.getPhone());
//        if (!teachers.isEmpty()) {
//            errors.put("errorPhoneDuplicate","Số điện thoại đã tồn tại");
//        }
//
//
//        if (createUpdateTeacherDTO.getAvatar() != null && !createUpdateTeacherDTO.getAvatar().isEmpty()) {
//            String[] allowedExtensions = {"jpg", "png", "jpeg"};
//            String extension = createUpdateTeacherDTO.getAvatar().substring(createUpdateTeacherDTO.getAvatar().lastIndexOf(".") + 1);
//            if (!Arrays.asList(allowedExtensions).contains(extension)) {
//                errors.put("errorFileFormat","File ảnh không đúng định dạng");
//            }
//
//            long size = createUpdateTeacherDTO.getAvatar().length();
//            if (size > 1024 * 1024 * 10) {
//                errors.put("errorFileLength","File ảnh quá lớn");
//            }
//        }
        return errors;
    }
}
