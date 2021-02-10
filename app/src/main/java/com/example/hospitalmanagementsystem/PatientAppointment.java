package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class PatientAppointment extends AppCompatActivity {

    EditText etpatnam;
    EditText etpatAge;
    EditText etpatnum;
    AutoCompleteTextView etdisease;
    EditText etpatAddr;
    EditText etpatid;
    Button btnfixAppoint;
    ImageView imgmen;
    ImageView imgwomen;
    TextView TextViewdate;
    String gender="";
    Button buttondate;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    DatabaseReference firebase;
    DatabaseReference firebase1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment);
       init();
        imgmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgmen.setBackgroundColor(Color.BLUE);
                imgwomen.setBackgroundColor(Color.WHITE);
                gender="Male";
            }
        });
        imgwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgwomen.setBackgroundColor(Color.BLUE);
                imgmen.setBackgroundColor(Color.WHITE);
                gender="Female";
            }
        });
        btnfixAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsMessage="Assalam o Alaikum "
                        + "Your Appointment has Fixed"
                        + "Your Details"
                        + "Name:"+etpatnam.getText().toString()
                        + "Age:"+etpatAge.getText().toString()
                        + "Disease:"+etdisease.getText().toString()
                        + "Date:"+TextViewdate.getText().toString()
                        + "Address:"+etpatAddr.getText().toString()
                        + "Cnic:"+etpatid.getText().toString()
                        + "Regards: Doctor Hospital"
                        + "Thank you for choosing our Institute";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage
                        (etpatnum.getText().toString(), null, smsMessage,
                                null, null);

                if(etpatnam.getText().toString().trim().isEmpty()){
                    etpatnam.setError("This field can't be empty");
                }
                else if(etpatAge.getText().toString().trim().isEmpty()){
                    etpatAge.setError("This field can't be empty");
                }
                else if(etpatnum.getText().toString().trim().isEmpty()){
                    etpatnum.setError("This field can't be empty");
                }
                else if(etdisease.getText().toString().trim().isEmpty()){
                    etdisease.setError("This field can't be empty");
                }
                else if(etpatAddr.getText().toString().trim().isEmpty()){
                    etpatAddr.setError("This field can't be empty");
                }
                else if(etpatid.getText().toString().trim().isEmpty()){
                    etpatid.setError("This field can't be empty");
                }
                else if(gender.isEmpty()){
                    Toast.makeText(PatientAppointment.this,"Select Gender",Toast.LENGTH_SHORT).show();
                }
                else {
                    imgwomen.setBackgroundColor(Color.WHITE);
                    imgmen.setBackgroundColor(Color.WHITE);
                    HashMap<String,String> MyPatient= new HashMap<>();
                    MyPatient.put("Name",etpatnam.getText().toString().trim());
                    MyPatient.put("Age",etpatAge.getText().toString().trim());
                    MyPatient.put("ContactNo",etpatnum.getText().toString().trim());
                    MyPatient.put("Date",TextViewdate.getText().toString().trim());
                    MyPatient.put("Disease",etdisease.getText().toString().trim());
                    MyPatient.put("Address",etpatAddr.getText().toString().trim());
                    MyPatient.put("Cnic",etpatid.getText().toString().trim());
                    MyPatient.put("Gender",gender.trim());
                    MyPatient.put("Appointment","Fixed");
                    firebase1.child(etdisease.getText().toString().trim()).child(etpatid.getText().toString().trim()).setValue(MyPatient);
                    firebase.child(etpatid.getText().toString().trim()).setValue(MyPatient)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            /*String Message="Assalam o Alaikum "+"\n"
                                    +"Your Appointment has Fixed"+"\n"
                                    +"Your Details"+"\n"
                                    +"Name:"+etpatnam.getText().toString().trim()+"\n"
                                    +"Age:"+etpatAge.getText().toString().trim()+"\n"
                                    +"Disease:"+etdisease.getText().toString().trim()+"\n"
                                    +"Date:"+TextViewdate.getText().toString().trim()+"\n"
                                    +"Address:"+etpatAddr.getText().toString().trim()+"\n"
                                    +"Cnic:"+etpatid.getText().toString().trim()+"\n"
                                    +"Regards: Doctor Hospital"
                                    +"Thank you for choosing our Institute";*/
                            // MyNumber=etpatnum.getText().toString();
                            //SmsManager smsManager=SmsManager.getDefault();
                            //smsManager.sendTextMessage(MyNumber,null,"Message",null,null);
                            Toast.makeText(PatientAppointment.this, "Appointment Fixed", Toast.LENGTH_SHORT);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PatientAppointment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    etpatnam.setText("");
                    etpatAge.setText("");
                    etpatnum.setText("");
                    etdisease.setText("");
                    etpatAddr.setText("");
                    etpatid.setText("");
                    gender="";
                }

            }
        });
    }
    public void init(){
        etpatnam= findViewById(R.id.etpatnam);
        etpatAge= findViewById(R.id.etpatAge);
        etpatnum= findViewById(R.id.etpatnum);
        TextViewdate= findViewById(R.id.textViewdate);
        etdisease= findViewById(R.id.etdisease);
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
        etdisease.setThreshold(1);
        etdisease.setAdapter(adapter);

        etpatAddr= findViewById(R.id.etpatAddr);
        etpatid= findViewById(R.id.etpatid);
        btnfixAppoint= findViewById(R.id.btnfixAppoint);
        imgmen = findViewById(R.id.imgmen);
        imgmen.setImageResource(R.mipmap.maledoctor);

        imgwomen = findViewById(R.id.imgwomen);
        imgwomen.setImageResource(R.mipmap.femaledoctor);
        TextViewdate= findViewById(R.id.textViewdate);
        buttondate= findViewById(R.id.buttondate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        firebase = FirebaseDatabase.getInstance().getReference().child("Patient");
        firebase1 = FirebaseDatabase.getInstance().getReference().child("Disease");

    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        TextViewdate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
