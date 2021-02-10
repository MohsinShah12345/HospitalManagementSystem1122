package com.example.hospitalmanagementsystem;

public class Admin {
    String Name;
    String Id;
    String Address;
    String Number;
    String Password;

    public Admin() {
    }

    public Admin(String name, String id, String address, String number, String password) {
        Name = name;
        Id = id;
        Address = address;
        Number = number;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "Name='" + Name + '\'' +
                ", Id='" + Id + '\'' +
                ", Address='" + Address + '\'' +
                ", Number='" + Number + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
