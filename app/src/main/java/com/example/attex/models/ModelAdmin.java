package com.example.attex.models;

public class ModelAdmin {

    String schoolName;
    String schoolID;
    String adminEmail;
    String adminID;
    String addressLine1;
    String addressLine2;
    String county;

    public ModelAdmin(String schoolName, String schoolID, String adminEmail, String adminID, String addressLine1, String addressLine2, String county) {
        this.schoolName = schoolName;
        this.schoolID = schoolID;
        this.adminEmail = adminEmail;
        this.adminID = adminID;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.county = county;
    }

    public ModelAdmin(){
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
