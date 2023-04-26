package com.example.attex.models;

import java.util.ArrayList;

public class ModelLearningDisability {

    String title;
    String shortDescription;
    ArrayList<String> symptoms;

    public ModelLearningDisability(String title, String shortDescription, ArrayList<String> symptoms) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.symptoms = symptoms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }
}
