package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class logins extends AppCompatActivity {
    Button btnadminlogin;
    Button btndoctorlogin;
    Button btnAboutUs;
    final int Uniquenumber=12345;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==Uniquenumber){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(logins.this,"Thank you for permission", Toast.LENGTH_SHORT).show();
            }
            else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(logins.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    AlertDialog.Builder dialog= new AlertDialog.Builder(this);
                    dialog.setMessage("This Permission is necessary");
                    dialog.setTitle("Permission is required");
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.setNegativeButton("No Thank's", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        init();
        if(ContextCompat.checkSelfPermission(logins.this,Manifest.permission.SEND_SMS)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(logins.this,new String[]{Manifest.permission.SEND_SMS},2);

        }
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SmsManager smsManager=SmsManager.getDefault();
                //smsManager.sendTextMessage("03167472516",null,"Hello",null,null);

                if(ContextCompat.checkSelfPermission(logins.this,Manifest.permission.READ_EXTERNAL_STORAGE)
                        !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(logins.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                else {
                    Toast.makeText(logins.this,"Thank you",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    void init(){
        btnadminlogin= findViewById(R.id.btnadminlogin);
        btndoctorlogin= findViewById(R.id.btndoctorlogin);
        btnAboutUs= findViewById(R.id.btnAboutUs);
    }
    public void btnAdmin(View v){
        startActivity(new Intent(logins.this,AdminLogin.class));
    }
    public void btnDoctor(View v){
        startActivity(new Intent(logins.this,DoctorLogin.class));
    }
}