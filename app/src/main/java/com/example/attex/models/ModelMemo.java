package com.example.attex.models;

public class ModelMemo {

    String memoID;
    String memoTitle;
    String memo;
    String date;

    public ModelMemo(String memoID, String memoTitle, String memo, String date) {
        this.memoID = memoID;
        this.memoTitle = memoTitle;
        this.memo = memo;
        this.date = date;
    }

    public ModelMemo(){

    }

    public String getMemoID() {
        return memoID;
    }

    public void setMemoID(String memoID) {
        this.memoID = memoID;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
