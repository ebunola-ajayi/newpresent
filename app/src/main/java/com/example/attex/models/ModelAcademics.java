package com.example.attex.models;

public class ModelAcademics {

    String grade;
    String topic;
    String note;
    String studentID;
    String subject;
    String name;

    public ModelAcademics(String grade, String studentID, String topic, String note, String subject, String name) { //
        this.grade = grade;
        this.topic = topic;
        this.note = note;
        this.studentID = studentID;
        this.subject = subject;
        this.name = name;
    }

    ModelAcademics(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
