package com.example.attex.models;

public class ModelMedical {

    String note;
    String medicalType;
    String medicalTitle;
    String medicalID;

    public ModelMedical(){

    }

    public ModelMedical(String note, String medicalType, String medicalTitle, String medicalID) {
        this.note = note;
        this.medicalType = medicalType;
        this.medicalTitle = medicalTitle;
        this.medicalID = medicalID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMedicalType() {
        return medicalType;
    }

    public void setMedicalType(String medicalType) {
        this.medicalType = medicalType;
    }

    public String getMedicalTitle() {
        return medicalTitle;
    }

    public void setMedicalTitle(String medicalTitle) {
        this.medicalTitle = medicalTitle;
    }

    public String getMedicalID() {
        return medicalID;
    }

    public void setMedicalID(String medicalID) {
        this.medicalID = medicalID;
    }
}
