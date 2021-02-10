package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorPatient extends AppCompatActivity {

    RecyclerView docpatrecyclerview;
    DoctorPatientAdapter doctorPatientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String disease=i.getStringExtra("Diseases");
        setContentView(R.layout.activity_doctor_patient);
        docpatrecyclerview= findViewById(R.id.docpatrecyclerview);
        docpatrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        docpatrecyclerview.setHasFixedSize(true);
        FirebaseRecyclerOptions<PatientDoc> option= new FirebaseRecyclerOptions
                .Builder<PatientDoc>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Disease").child(disease) , PatientDoc.class)
                .build();
        doctorPatientAdapter=new DoctorPatientAdapter(option);
        docpatrecyclerview.setAdapter(doctorPatientAdapter);
        //doctorPatientAdapter.

    }
    @Override
    protected void onStart(){
        super.onStart();
        doctorPatientAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        doctorPatientAdapter.stopListening();
    }
}