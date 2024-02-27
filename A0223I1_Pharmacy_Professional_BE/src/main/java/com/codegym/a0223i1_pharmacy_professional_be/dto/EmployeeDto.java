package com.codegym.a0223i1_pharmacy_professional_be.dto;

import java.util.Date;

public class EmployeeDto {
    private String employeeId;

    private String employeeName;

    private String phoneNumber;

    private Date dateStart;

    private String address;

    private String note;

    private int salary;

    private String image;
    private String email;
    private String password;
    private Long role_id;

    public EmployeeDto() {
    }

    public EmployeeDto(String employeeId, String employeeName, String phoneNumber, Date dateStart, String address, String note, int salary, String image, String email, String password, Long role_id) {

    public EmployeeDto() {
    }

    public EmployeeDto(String employeeId, String employeeName, String phoneNumber, Date dateStart, String address, String note, int salary, String image) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.dateStart = dateStart;
        this.address = address;
        this.note = note;
        this.salary = salary;
        this.image = image;

        this.email = email;
        this.password = password;
        this.role_id = role_id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getImage() {
        return image;
    }
     
    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}
