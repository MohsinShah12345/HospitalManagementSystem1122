package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AddDoctor extends AppCompatActivity {
    EditText etdocname;
    EditText etdocAge;
    EditText etdoctnum;
    AutoCompleteTextView etdiseaseSpec;
    EditText etdocAddr;
    EditText etdoctid;
    EditText etdocpas;
    EditText etdocpascnfm;
    Button btnfixAppoint;
    DatabaseReference firebase;
    DatabaseReference firebase1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        init();

        btnfixAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etdocname.getText().toString().trim().isEmpty()){
                       etdocname.setError("This field can't be empty");
                }
                else if(etdocAge.getText().toString().trim().isEmpty()){
                    etdocAge.setError("This field can't be empty");
                }
                else if(etdoctnum.getText().toString().trim().isEmpty()){
                    etdoctnum.setError("This field can't be empty");
                }
                else if(etdiseaseSpec.getText().toString().trim().isEmpty()){
                    etdiseaseSpec.setError("This field can't be empty");
                }
                else if(etdocAddr.getText().toString().trim().isEmpty()){
                    etdocAddr.setError("This field can't be empty");
                }
                else if(etdoctid.getText().toString().trim().isEmpty()){
                    etdoctid.setError("This field can't be empty");
                }
                else if(etdocpas.getText().toString().trim().isEmpty()){
                    etdocpas.setError("This field can't be empty");
                }
                else if(etdocpascnfm.getText().toString().trim().isEmpty()){
                    etdocpascnfm.setError("This field can't be empty");
                }
                else if(!(etdocpas.getText().toString().trim()).equals(etdocpascnfm.getText().toString().trim())){
                    Toast.makeText(AddDoctor.this,"Passwords didn't match",Toast.LENGTH_SHORT).show();
                }
                else {
                    HashMap<String,String> Doctor3= new HashMap<>();
                    Doctor3.put("Name",etdocname.getText().toString().trim());
                    Doctor3.put("Age",etdocAge.getText().toString().trim());
                    Doctor3.put("Number",etdoctnum.getText().toString().trim());
                    Doctor3.put("Specitialization",etdiseaseSpec.getText().toString().trim());
                    Doctor3.put("Address",etdocAddr.getText().toString().trim());
                    Doctor3.put("DocId",etdoctid.getText().toString().trim());
                    Doctor3.put("Password",etdocpas.getText().toString().trim());
                   // firebase1.child(etdiseaseSpec.getText().toString().trim()).child(etdoctid.getText().toString().trim()).setValue(Doctor3);
                    firebase.child(etdoctid.getText().toString().trim())
                            .setValue(Doctor3)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            String Message="Assalam o Alaikum "+"\n"
                                    +"Dear Coctor"+"\n"
                                    +"Your Account has been Created"+"\n"
                                    +"Your Details"+"\n"
                                    +"Name:"+etdocname.getText().toString().trim()+"\n"
                                    +"Age:"+etdocAge.getText().toString().trim()+"\n"
                                    +"Specitialization:"+etdiseaseSpec.getText().toString().trim()+"\n"
                                    +"Address:"+etdoctid.getText().toString().trim()+"\n"
                                    +"Cnic/Id:"+etdoctid.getText().toString().trim()
                                    +"Password:"+etdocpas.getText().toString().trim();
                           // SmsManager smsManager=SmsManager.getDefault();
                            //smsManager.sendTextMessage(etdoctnum.getText().toString().trim(),null,Message,null,null);
                            Toast.makeText(AddDoctor.this,"Doctor has successfully entered",Toast.LENGTH_SHORT).show();

                        }
                    });

                    etdocname.setText("");
                    etdocAge.setText("");
                    etdoctnum.setText("");
                    etdiseaseSpec.setText("");
                    etdocAddr.setText("");
                    etdoctid.setText("");
                    etdocpas.setText("");
                    etdocpascnfm.setText("");
                }
                
            }
        });
    }
    public void init(){
        etdocname= findViewById(R.id.etdocname);
        etdocAge= findViewById(R.id.etdocAge);
        etdoctnum= findViewById(R.id.etdoctnum);
        etdiseaseSpec= findViewById(R.id.etdiseaseSpec);
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
        etdiseaseSpec.setThreshold(1);
        etdiseaseSpec.setAdapter(adapter);

        etdocAddr= findViewById(R.id.etdocAddr);
        etdoctid= findViewById(R.id.etdoctid);
        etdocpas= findViewById(R.id.etdocpas);
        etdocpascnfm= findViewById(R.id.etdocpascnfm);
        btnfixAppoint= findViewById(R.id.btnfixAppoint);
        firebase= FirebaseDatabase.getInstance().getReference()
        .child("Doctors");
        firebase1= FirebaseDatabase.getInstance().getReference()
                .child("Disease");


    }
}