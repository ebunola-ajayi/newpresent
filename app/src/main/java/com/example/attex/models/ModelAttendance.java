package com.example.attex.models;

public class ModelAttendance {

    String firstName;
    String lastName;
    String studentID;
    String date;
    String classID;
    String attendance;

    public ModelAttendance(String firstName, String lastName, String studentID, String date, String attendance, String classID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.date = date;
        this.classID = classID;
        this.attendance = attendance;
    }

    public ModelAttendance(){

    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public ModelAttendance setAttendance(String attendance) {
        this.attendance = attendance;
        return null;
    }
}
