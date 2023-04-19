package com.example.attex.models;

public class ModelStudent {

    public String firstName;
    public String lastName;
    public String middleName;
    public String dateOfBirth;
    public String studentID;
    public String teacherID; //change to classID
    public String parent1Name;
    public String parent2Name;
    public String parentEmail1;
    public String parentEmail2;
    public String parentUsername2;
    public String parent1No;
    public String parent2No;
    public String schoolID;
    public String classGrade;
    public String classID;
    public String teacherEmail;
    public String addressLine1;
    public String addressLine2;
    public String county;



    public ModelStudent(){

    }

    public ModelStudent(String firstName, String lastName, String middleName, String dateOfBirth, String studentID, String teacherID, String parent1Name, String parent2Name, String parentEmail1, String parentEmail2, String parentUsername2, String parent1No, String parent2No, String schoolID, String classGrade, String classID, String teacherEmail, String addressLine1, String addressLine2, String county) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.studentID = studentID;
        this.teacherID = teacherID;
        this.parent1Name = parent1Name;
        this.parent2Name = parent2Name;
        this.parentEmail1 = parentEmail1;
        this.parentEmail2 = parentEmail2;
        this.parentUsername2 = parentUsername2;
        this.parent1No = parent1No;
        this.parent2No = parent2No;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
        this.teacherEmail = teacherEmail;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.county = county;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getParentEmail2() {
        return parentEmail2;
    }

    public void setParentEmail2(String parentEmail2) {
        this.parentEmail2 = parentEmail2;
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

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getParent1Name() {
        return parent1Name;
    }

    public void setParent1Name(String parent1Name) {
        this.parent1Name = parent1Name;
    }

    public String getParent2Name() {
        return parent2Name;
    }

    public void setParent2Name(String parent2Name) {
        this.parent2Name = parent2Name;
    }

    public String getParentEmail1() {
        return parentEmail1;
    }

    public void setParentEmail1(String parentEmail) {
        this.parentEmail1 = parentEmail;
    }

    public String getParentUsername2() {
        return parentUsername2;
    }

    public void setParentUsername2(String parentUsername2) {
        this.parentUsername2 = parentUsername2;
    }

    public String getParent1No() {
        return parent1No;
    }

    public void setParent1No(String parent1No) {
        this.parent1No = parent1No;
    }

    public String getParent2No() {
        return parent2No;
    }

    public void setParent2No(String parent2No) {
        this.parent2No = parent2No;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
