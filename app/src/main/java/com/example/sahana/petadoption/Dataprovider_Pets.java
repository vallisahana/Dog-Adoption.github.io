package com.example.sahana.petadoption;

// class with getter and setter methods to create single userdata as an object.

import java.io.Serializable;

public class Dataprovider_Pets implements Serializable {

    String name,des,breed,gender,phone,weight;

    public Dataprovider_Pets() {
    }

    public Dataprovider_Pets(String name, String des, String breed, String gender, String phone, String weight) {
        this.name = name;
        this.des = des;
        this.breed = breed;
        this.gender = gender;
        this.phone = phone;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
