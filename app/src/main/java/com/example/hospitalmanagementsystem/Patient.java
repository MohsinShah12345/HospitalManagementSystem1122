package com.example.hospitalmanagementsystem;

public class Patient {
    String Name;
    String Age;
    String Gender;
    String Disease;
    String ContactNo;
    String Address;
    String Cnic;
    String Date;
    String MyDoctor;
    String Appointment;


    public Patient() {
    }

    public Patient(String name, String age, String gender, String disease, String address, String contactNo, String cnic, String date, String myDoctor, String appointment) {
        Name = name;
        Age = age;
        Gender= gender;
        Address = address;
        Disease = disease;
        ContactNo = contactNo;
        Cnic = cnic;
        Date = date;
        MyDoctor = myDoctor;
        Appointment=appointment;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getCnic() {
        return Cnic;
    }

    public void setCnic(String cnic) {
        Cnic = cnic;
    }

    public String getDate() {
        return Date;
    }

    public String getAppointment() {
        return Appointment;
    }

    public void setAppointment(String appointment) {
        Appointment = appointment;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMyDoctor() {
        return MyDoctor;
    }

    public void setMyDoctor(String myDoctor) {
        MyDoctor = myDoctor;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "Name='" + Name + '\'' +
                ", Age='" + Age + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Disease='" + Disease + '\'' +
                ", ContactNo='" + ContactNo + '\'' +
                ", Address='" + Address + '\'' +
                ", Cnic='" + Cnic + '\'' +
                ", Date='" + Date + '\'' +
                ", MyDoctor='" + MyDoctor + '\'' +
                ", Appointment='" + Appointment + '\'' +
                '}';
    }
}
