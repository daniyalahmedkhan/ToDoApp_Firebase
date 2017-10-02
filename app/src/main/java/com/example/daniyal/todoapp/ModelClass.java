package com.example.daniyal.todoapp;

/**
 * Created by Daniyal on 9/29/2017.
 */

public class ModelClass {

    String id;
    String sub;
    String des;
    String status;

    public ModelClass() {
    }

//    public ModelClass(String id, String sub, String des) {
//        this.id = id;
//        this.sub = sub;
//        this.des = des;
//    }

    public ModelClass(String id, String sub, String des, String status) {
        this.id = id;
        this.sub = sub;
        this.des = des;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getSub() {
        return sub;
    }

    public String getDes() {
        return des;
    }
}
