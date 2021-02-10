package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DoctorRights extends AppCompatActivity {

    ImageView Myivdoc ;
    ImageView Myivedit ;
    Button Mybuttonupdat;
    Button MybuttonSeePatients;
    EditText MyNameupdat;
    EditText MyAgeupdat;
    EditText MyNumberupdat;
    AutoCompleteTextView MySpecitializationupdat;
    EditText MyAddressupdat;
    EditText MyPasswordupdat;
    DatabaseReference firebase;
     private String disease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_rights);
        init();
        Intent obj=getIntent();
        String Name=obj.getStringExtra("Name");
        String Age=obj.getStringExtra("Age");
        String Number=obj.getStringExtra("Number");
        String Specitialization=obj.getStringExtra("Specitialization");
        String Address=obj.getStringExtra("Address");
        String DocIds=obj.getStringExtra("DocId");
        String Password=obj.getStringExtra("Password");
        MyNameupdat.setText(Name);
        MyAgeupdat.setText(Age);
        MyNumberupdat.setText(Number);
        MySpecitializationupdat.setText(Specitialization);
        MyAddressupdat.setText(Address);
        MyPasswordupdat.setText(Password);
        if(!(MyNameupdat.equals(""))){
            Name=MyNameupdat.getText().toString().trim();
        }
        if(!(MyAgeupdat.equals(""))){
            Age=MyAgeupdat.getText().toString().trim();
        }
        if(!(MyNumberupdat.equals(""))){
            Number=MyNumberupdat.getText().toString().trim();
        }
        if(!(MySpecitializationupdat.equals(""))){
            Specitialization=MySpecitializationupdat.getText().toString().trim();
        }
        if(!(MyAddressupdat.equals(""))){
            Address=MyAddressupdat.getText().toString().trim();
        }
        if(!(MyPasswordupdat.equals(""))){
            Password=MyPasswordupdat.getText().toString().trim();
        }

        Mybuttonupdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> updoct= new HashMap<>();
                updoct.put("Name",MyNameupdat.getText().toString().trim());
                updoct.put("Age",MyAgeupdat.getText().toString().trim());
                updoct.put("Number",MyNumberupdat.getText().toString().trim());
                updoct.put("Specitialization",MySpecitializationupdat.getText().toString().trim());
                updoct.put("Address",MyAddressupdat.getText().toString().trim());
                updoct.put("DocId",DocIds);
                updoct.put("Password",MyPasswordupdat.getText().toString().trim());
                FirebaseDatabase.getInstance().getReference().child("Doctors")
                        .child(DocIds)
                        .updateChildren(updoct).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(DoctorRights.this,"Your Record Successfully Updated",Toast.LENGTH_SHORT).show();
                        MyNameupdat.setText("");
                        MyAgeupdat.setText("");
                        MyNumberupdat.setText("");
                        MySpecitializationupdat.setText("");
                        MyAddressupdat.setText("");
                        MyPasswordupdat.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DoctorRights.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        MybuttonSeePatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.child(DocIds).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                    disease= snapshot.child("Specitialization").getValue(String.class);
                        Intent in= new Intent(DoctorRights.this,DoctorPatient.class);
                        in.putExtra("Diseases",disease);
                        startActivity(in);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
    void init(){
        Myivdoc= findViewById(R.id.Myivdoc);
        Myivedit= findViewById(R.id.Myivedit);
        MyNameupdat= findViewById(R.id.MyNameupdat);
        MyAgeupdat= findViewById(R.id.MyAgeupdat);
        MyNumberupdat= findViewById(R.id.MyNumberupdat);
        MySpecitializationupdat= findViewById(R.id.MySpecitializationupdat);
        ArrayList<String> MyArrayList= new ArrayList<>();
        MyArrayList.add("Heart");
        MyArrayList.add("Lungs");
        MyArrayList.add("Eye");
        MyArrayList.add("Brain");
        MyArrayList.add("Kidney");
        MyArrayList.add("Corona");
        MyArrayList.add("Diabetes");
        MyArrayList.add("Cancer");
        MyArrayList.add("Ear");
        //String[] names={"Mohsin", "Ali","Zain", "Aqib", "Tanzel"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.customdesignlayout,MyArrayList);
        MySpecitializationupdat.setThreshold(1);
        MySpecitializationupdat.setAdapter(adapter);
        MyAddressupdat= findViewById(R.id.MyAddressupdat);
        MyPasswordupdat= findViewById(R.id.MyPasswordupdat);
        Mybuttonupdat= findViewById(R.id.Mybuttonupdat);
        MybuttonSeePatients= findViewById(R.id.MybuttonSeePatients);
        firebase= FirebaseDatabase.getInstance().getReference().child("Doctors");
    }
}