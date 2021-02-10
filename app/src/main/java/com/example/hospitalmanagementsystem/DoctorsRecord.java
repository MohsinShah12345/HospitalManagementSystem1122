package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorsRecord extends AppCompatActivity {

    RecyclerView doctorrecyclerview;
    DoctorAdapter doctorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_record);
        doctorrecyclerview= findViewById(R.id.doctorrecyclerview);
        doctorrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        doctorrecyclerview.setHasFixedSize(true);
        FirebaseRecyclerOptions<Doctors> option= new FirebaseRecyclerOptions
                .Builder<Doctors>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Doctors") , Doctors.class)
                .build();

        doctorAdapter= new DoctorAdapter(option,this);
        doctorrecyclerview.setAdapter(doctorAdapter);

    }
    @Override
    protected void onStart(){
      super.onStart();
      doctorAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        doctorAdapter.stopListening();
    }
}