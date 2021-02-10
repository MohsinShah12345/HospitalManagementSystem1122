package com.example.hospitalmanagementsystem;

public class Doctors {
    String Name;
    String Age;
    String Number;
    String Specitialization;
    String Address;
    String DocId;
    String Password;

    public Doctors() {
    }

    public Doctors(String name, String age, String number, String specitialization, String address, String docId, String password) {
        Name = name;
        Age = age;
        Number = number;
        Specitialization = specitialization;
        Address = address;
        DocId = docId;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getSpecitialization() {
        return Specitialization;
    }

    public void setSpecitialization(String specitialization) {
        Specitialization = specitialization;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDocId() {
        return DocId;
    }

    public void setDocId(String docId) {
        DocId = docId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "Name='" + Name + '\'' +
                ", Age='" + Age + '\'' +
                ", Number='" + Number + '\'' +
                ", Specitialization='" + Specitialization + '\'' +
                ", Address='" + Address + '\'' +
                ", DocId='" + DocId + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
