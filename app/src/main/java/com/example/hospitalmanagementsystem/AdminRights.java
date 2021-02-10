package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminRights extends AppCompatActivity {

    Button btnadmindata;
    Button btndoctordata;
    Button btnPatients;
    Button btnPatAppoint;
    Button btnAdddoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_rights);
        init();
    }
    public void init(){

        btnadmindata=findViewById(R.id.btnadmindata);
        btndoctordata=findViewById(R.id.btndoctordata);
        btnPatients=findViewById(R.id.btnPatients);
        btnPatAppoint=findViewById(R.id.btnPatAppoint);
        btnAdddoctor= findViewById(R.id.btnAdddoctor);
    }
    public void bAdmindata(View v){
      startActivity(new Intent(AdminRights.this,AdminRecord.class));
    }
    public void bDoctordata(View v){
        startActivity(new Intent(AdminRights.this,DoctorsRecord.class));
    }
    public void bPatients(View v){
        startActivity(new Intent(AdminRights.this,PatientsRecord.class));
    }
    public void bpatientAppoint(View v){
        startActivity(new Intent(AdminRights.this,PatientAppointment.class));
    }
    public void bAddDoctor(View v){
        startActivity(new Intent(AdminRights.this,AddDoctor.class));
    }

}