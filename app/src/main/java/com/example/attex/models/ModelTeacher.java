package com.example.attex.models;

public class ModelTeacher {
    public String firstName;
    public String lastName;
    public String email;
    public String teacherUsername;
    public String teacherID;
    public String schoolID;
    public String teacherName;
    public String classGrade;
    public String classID;
    public String number;
    public String addressLine1;
    public String addressLine2;


    public ModelTeacher(String firstName, String lastName, String email, String teacherUsername, String teacherID, String schoolID, String classGrade, String classID, String teacherName, String number, String addressLine1, String addressLine2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.teacherUsername = teacherUsername;
        this.teacherID = teacherID;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
        this.teacherName = teacherName;
        this.number = number;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
    }

    public ModelTeacher() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return teacherUsername;
    }

    public void setUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }
}
