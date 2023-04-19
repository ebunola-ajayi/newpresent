package com.example.attex.models;

import com.example.attex.models.ModelAttendance;

public class ModelStudentList {

    ModelAttendance modelAttendance;

    public ModelStudentList(ModelAttendance modelAttendance) {
        this.modelAttendance = modelAttendance;
    }

    public ModelStudentList(){

    }

    public ModelAttendance getModelAttendance() {
        return modelAttendance;
    }

    public void setModelAttendance(ModelAttendance modelAttendance) {
        this.modelAttendance = modelAttendance;
    }
}
