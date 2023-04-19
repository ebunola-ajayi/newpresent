package com.example.attex.models;

public class ModelParent {

    public String pFirstName;
    public String pSurName;
    public String pEmail;
    public String pChildID;
    public String parentUsername;
    public String parentID;
    public String schoolID;
    public String classGrade;
    public String classID;
    public String childFirstName;
    public String childLastName;
    public String parentEmail;
    public String studentID;
    public String schoolName;

    public ModelParent(String pFirstName, String pSurName, String pEmail, String pChildID, String parentUsername, String parentID, String schoolID, String classGrade, String classID, String childFirstName, String childLastName, String parentEmail, String studentID) {
        this.pFirstName = pFirstName;
        this.pSurName = pSurName;
        this.pEmail = pEmail;
        this.pChildID = pChildID;
        this.parentUsername = parentUsername;
        this.parentID = parentID;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
        this.childFirstName = childFirstName;
        this.childLastName = childLastName;
        this.parentEmail = parentEmail;
        this.studentID = studentID;
    }

    public ModelParent(){

    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getChildLastName() {
        return childLastName;
    }

    public void setChildLastName(String childLastName) {
        this.childLastName = childLastName;
    }

    public String getChildFirstName() {
        return childFirstName;
    }

    public void setChildFirstName(String childFirstName) {
        this.childFirstName = childFirstName;
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

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public String getpFirstName() {
        return pFirstName;
    }

    public void setpFirstName(String pFirstName) {
        this.pFirstName = pFirstName;
    }

    public String getpSurName() {
        return pSurName;
    }

    public void setpSurName(String pSurName) {
        this.pSurName = pSurName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getpChildID() {
        return pChildID;
    }

    public void setpChildID(String pChildID) {
        this.pChildID = pChildID;
    }
}
