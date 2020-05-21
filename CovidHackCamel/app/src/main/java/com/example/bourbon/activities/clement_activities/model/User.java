package com.example.bourbon.activities.clement_activities.model;

public class User {

    String name="" ;
    String address="" ;

    public User(String name,String addr){
        this.name=name;
        this.address=addr;
    }


    public User(){
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
