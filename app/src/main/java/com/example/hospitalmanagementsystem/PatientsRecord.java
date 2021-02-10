package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PatientsRecord extends AppCompatActivity {

    RecyclerView patientrecyclerview;
    PatientAdapter patientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_record);
        patientrecyclerview=findViewById(R.id.patientrecyclerview);
        patientrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        patientrecyclerview.setHasFixedSize(true);
        FirebaseRecyclerOptions<Patient> options= new FirebaseRecyclerOptions
                .Builder<Patient>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Patient") , Patient.class)
                .build();
        patientAdapter= new PatientAdapter(options,this);
        patientrecyclerview.setAdapter(patientAdapter);

    }
    @Override
    protected void onStart(){
        super.onStart();
        patientAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        patientAdapter.stopListening();
    }
}