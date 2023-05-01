package com.example.attex.models;

public class ModelSubjectTopics {

    String topic;
    String note;
    String grade;

    public ModelSubjectTopics(String topic, String note, String grade) {
        this.topic = topic;
        this.note = note;
        this.grade = grade;
    }

    public ModelSubjectTopics(){

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
