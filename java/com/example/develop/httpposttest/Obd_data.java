package com.example.develop.httpposttest;

/**
 * Created by develop on 2017/2/18.
 */

public class Obd_data {
    private String idsn;
    private String date;

    public void setIdsn(String id){
        idsn = id;
    }

    public String getIdsn(){
        return idsn;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }
}
