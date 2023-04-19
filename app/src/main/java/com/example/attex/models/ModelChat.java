package com.example.attex.models;

public class ModelChat {
    //chat object class

    private String sender;
    private String receiver;
    private String message;
    private int image;
    //private boolean isseen;
    //I wanted to add a feature that would show if the reciver has seen the message, but because I kept getting errors I decided to comment it out
    //so my app could run without problems

    //constructor
    public ModelChat(String sender, String receiver, String message) { //, boolean isseen
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        // this.isseen = isseen;
    }

    //empty constructor
    public ModelChat(){
    }

    //getter and setter methods
    public String getSender() {
        return sender;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }*/
}
