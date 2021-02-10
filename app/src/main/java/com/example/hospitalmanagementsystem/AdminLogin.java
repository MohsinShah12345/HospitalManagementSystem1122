package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {


    EditText etadmname;
    EditText etadmpass;
    Button btnlogin;
    DatabaseReference firebase;
    String name="";
    String password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        init();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AdminLogin.this, AdminRights.class));
                fetchuserdata();
            }
        });


    }
    public void fetchuserdata(){

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                name = snapshot.child("name").getValue(String.class);
                password = snapshot.child("password").getValue(String.class);
                if(etadmname.getText().equals("") || etadmpass.getText().equals("")){
                    Toast.makeText(AdminLogin.this,"Username or password can not be empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if((etadmname.getText().toString().trim().equals(name)) && (etadmpass.getText().toString().trim().equals(password))) {
                        etadmpass.setText("");
                        startActivity(new Intent(AdminLogin.this, AdminRights.class));
                    }else{
                        Toast.makeText(AdminLogin.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void init(){
        etadmname= findViewById(R.id.etpatnam);
        etadmpass= findViewById(R.id.etdocpass);
        btnlogin= findViewById(R.id.btndoclogin);
        firebase = FirebaseDatabase.getInstance().getReference().child("Admin");// fetching admin data

    }
}