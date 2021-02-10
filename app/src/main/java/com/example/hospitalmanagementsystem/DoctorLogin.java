package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorLogin extends AppCompatActivity {
    EditText etdocnam;
    EditText etdocpass;
    Button btndoclogin;
    DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        init();

        btndoclogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   fetchdata();
            }
        });
    }
    public void init(){
        etdocnam= findViewById(R.id.etpatnam);
        etdocpass= findViewById(R.id.etdocpass);
        btndoclogin= findViewById(R.id.btndoclogin);
        firebase= FirebaseDatabase.getInstance().getReference("Doctors");
    }
    public void fetchdata(){
        firebase.child(etdocnam.getText().toString().trim())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String Name=snapshot.child("Name").getValue(String.class);
                        String Age=snapshot.child("Age").getValue(String.class);
                        String Address=snapshot.child("Address").getValue(String.class);
                        String Specitialization=snapshot.child("Specitialization").getValue(String.class);
                        String DocId =snapshot.child("DocId").getValue(String.class);
                        String Password=snapshot.child("Password").getValue(String.class);
                        String Number=snapshot.child("Number").getValue(String.class);
                        if(etdocnam.getText().toString().trim().equals("")){
                            etdocnam.setError("This Field can't be empty");
                        }
                        else if (etdocpass.getText().toString().trim().equals("")){
                            etdocnam.setError("This Field can't be empty");
                        }
                        else {
                            if(etdocnam.getText().toString().trim().equals(DocId) && etdocpass.getText().toString().trim().equals(Password)){
                                //startActivity(new Intent(DoctorLogin.this,DoctorRights.class));
                                Intent intent=new Intent(DoctorLogin.this,DoctorRights.class);
                                intent.putExtra("Name",Name);
                                intent.putExtra("Age",Age);
                                intent.putExtra("Address",Address);
                                intent.putExtra("DocId",DocId);
                                intent.putExtra("Number",Number);
                                intent.putExtra("Password",Password);
                                intent.putExtra("Specitialization",Specitialization);
                                startActivity(intent);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}