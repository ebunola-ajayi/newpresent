package com.example.attex.models;

public class ModelBehaviour {

    String studentID;
    String classID;
    String firstName;
    String lastName;
    String feedback;
    String date;
    String comment;
    String behaviourID;

    public ModelBehaviour(String studentID, String classID, String firstName, String lastName, String feedback, String date, String comment, String behaviourID) {
        this.studentID = studentID;
        this.classID = classID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.feedback = feedback;
        this.date = date;
        this.comment = comment;
        this.behaviourID = behaviourID;
    }

    public ModelBehaviour(){

    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getBehaviourID() {
        return behaviourID;
    }

    public void setBehaviourID(String behaviourID) {
        this.behaviourID = behaviourID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
