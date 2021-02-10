package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdatePatientData extends AppCompatActivity {

    ImageView ivdocP ;
    ImageView iveditP ;
    ImageView imgphone ;
    ImageView imgdelete ;
    Button buttonupdatP;
    Button buttondateP;
    EditText NameupdatP;
    EditText AgeupdatP;
    TextView dateupdatP;
    EditText ContactNoupdatP;
    AutoCompleteTextView DiseaseupdatP;
    EditText AddressupdatP;
    EditText GenderdupdatP;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient_data);
        init();
        Intent obj=getIntent();
        String Name=obj.getStringExtra("Name");
        String Age=obj.getStringExtra("Age");
        String Gender=obj.getStringExtra("Gender");
        String Disease=obj.getStringExtra("Disease");
        String Address=obj.getStringExtra("Address");
        String ContactNo=obj.getStringExtra("ContactNo");
        String Cnic=obj.getStringExtra("Cnic");
        String Date=obj.getStringExtra("Date");
        String Appointment=obj.getStringExtra("Appointment");
        //String MyDoctor=obj.getStringExtra("MyDoctor");
        String Key=obj.getStringExtra("Key").toString();
        imgphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ContactNo));
                startActivity(intent);
            }
        });
        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(ContactNo,null,"Your Appointment has been cancelled",null,null);
                FirebaseDatabase.getInstance().getReference().child("Patient")
                        .child(Cnic)
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                      Toast.makeText(UpdatePatientData.this,"Patient Deleted Successfully",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdatePatientData.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                NameupdatP.setText("");
                AgeupdatP.setText("");
                GenderdupdatP.setText("");
                DiseaseupdatP.setText("");
                AddressupdatP.setText("");
                ContactNoupdatP.setText("");
                dateupdatP.setText("");
            }
        });
        NameupdatP.setText(Name);
        AgeupdatP.setText(Age);
        dateupdatP.setText(Date);
        ContactNoupdatP.setText(ContactNo);
        DiseaseupdatP.setText(Disease);
        AddressupdatP.setText(Address);
        GenderdupdatP.setText(Gender);
        buttonupdatP= findViewById(R.id.buttonupdatP);
        buttonupdatP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message="Assalam o Alaikum "
                        +"Your Appointment has Fixed"
                        +"Your Details"
                        +"Name:"+NameupdatP.getText().toString()
                        +"Age:"+AgeupdatP.getText().toString().trim()
                        +"Disease:"+DiseaseupdatP.getText().toString()
                        +"Date:"+dateupdatP.getText().toString()
                        +"Address:"+AddressupdatP.getText().toString()
                        +"Cnic:"+Cnic
                        +"Regards: Doctor Hospital"
                        +"Thank you for choosing our Institute";
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(ContactNoupdatP.getText().toString(),null,Message,null,null);
                Map<String,Object> upPatient= new HashMap<>();
                upPatient.put("Name",NameupdatP.getText().toString().trim());
                upPatient.put("Age",AgeupdatP.getText().toString().trim());
                upPatient.put("Gender",GenderdupdatP.getText().toString().trim());
                upPatient.put("Disease",DiseaseupdatP.getText().toString().trim());
                upPatient.put("Address",AddressupdatP.getText().toString().trim());
                upPatient.put("ContactNo",ContactNoupdatP.getText().toString().trim());
                upPatient.put("Cnic",Cnic);
                upPatient.put("Date",dateupdatP.getText().toString().trim());
                upPatient.put("Appointment",Appointment);
                FirebaseDatabase.getInstance().getReference().child("Patient")
                        .child(Cnic)
                        .updateChildren(upPatient).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(UpdatePatientData.this,"Data has been Updtaed",Toast.LENGTH_SHORT).show();
                    }
                });
                NameupdatP.setText("");
                AgeupdatP.setText("");
                GenderdupdatP.setText("");
                DiseaseupdatP.setText("");
                AddressupdatP.setText("");
                ContactNoupdatP.setText("");
                dateupdatP.setText("");
            }
        });


    }
    void init(){
    ivdocP= findViewById(R.id.ivdocP);
    iveditP= findViewById(R.id.iveditP);
    imgphone= findViewById(R.id.imgphone);
    NameupdatP= findViewById(R.id.NameupdatP);
    AgeupdatP= findViewById(R.id.AgeupdatP);
    dateupdatP= findViewById(R.id.dateupdatP);
    ContactNoupdatP= findViewById(R.id.ContactNoupdatP);
    DiseaseupdatP= findViewById(R.id.DiseaseupdatP);
    AddressupdatP= findViewById(R.id.AddressupdatP);
    GenderdupdatP= findViewById(R.id.GenderdupdatP);
    buttonupdatP= findViewById(R.id.buttonupdatP);
    buttondateP= findViewById(R.id.buttondateP);
        imgdelete= findViewById(R.id.imgdelete);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }
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
        dateupdatP.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
public void call(View view){

}
}