package com.example.attex.models;

public class ModelAdmin {

    String schoolName;
    String schoolID;
    String adminEmail;

    public ModelAdmin(String schoolName, String schoolID, String adminEmail) {
        this.schoolName = schoolName;
        this.schoolID = schoolID;
        this.adminEmail = adminEmail;
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
}
