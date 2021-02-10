package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UpdateDoctorData extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 58;
    ImageView ivdoc ;
    ImageView ivedit ;
    ImageView ivphone ;
    ImageView ivdelete ;
    Button buttonupdat;
    EditText Nameupdat;
    EditText Ageupdat;
    EditText Numberupdat;
    AutoCompleteTextView Specitializationupdat;
    EditText Addressupdat;
    EditText Passwordupdat;
    String DocId;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
   FirebaseStorage storage;
    StorageReference storageReference;
    private ProgressBar mProgressbar;

    private StorageReference mstorage;
    private DatabaseReference dbref;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data!=null && data.getData()!=null){
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(ivdoc);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivdoc.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
          //  ivdoc.setImageURI(imageUri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor_data);
      init();

        Intent obj=getIntent();
        String Name=obj.getStringExtra("Name");
        String Age=obj.getStringExtra("Age");
        String Number=obj.getStringExtra("Number");
        String Specitialization=obj.getStringExtra("Specitialization");
        String Address=obj.getStringExtra("Address");
        DocId=obj.getStringExtra("DocId");
        String Password=obj.getStringExtra("Password");
        //String Key=obj.getStringExtra("Key");
        Nameupdat.setText(Name);
        Ageupdat.setText(Age);
        Numberupdat.setText(Number);
        Specitializationupdat.setText(Specitialization);
        Addressupdat.setText(Address);
        Passwordupdat.setText(Password);
        ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                //uploadimg();
                //String DocId=obj.getStringExtra("DocId");
                uploadImage();
            }
        });
        buttonupdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message="Assalam o Alaikum "
                        +"Dear Coctor"
                        +"Your Account has been Updated"
                        +"Your Details"
                        +"Name:"+Nameupdat.getText().toString()
                        +"Age:"+Ageupdat.getText().toString()
                        +"Specitialization:"+Specitializationupdat.getText().toString()
                        +"Address:"+Addressupdat.getText().toString()
                        +"Cnic/Id:"+DocId
                        +"Password:"+Passwordupdat.getText().toString();
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(Numberupdat.getText().toString().trim(),null,"Data has been Updated",null,null);
                Map<String,Object> upDoctor= new HashMap<>();
                upDoctor.put("Name",Nameupdat.getText().toString().trim());
                upDoctor.put("Age",Ageupdat.getText().toString().trim());
                upDoctor.put("Number",Numberupdat.getText().toString().trim());
                upDoctor.put("Specitialization",Specitializationupdat.getText().toString().trim());
                upDoctor.put("Address",Addressupdat.getText().toString().trim());
                upDoctor.put("DocId",DocId);
                upDoctor.put("Password",Passwordupdat.getText().toString().trim());
                FirebaseDatabase.getInstance().getReference().child("Doctors")
                        .child(DocId)
                        .updateChildren(upDoctor)
                         .addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {
                              Toast.makeText(UpdateDoctorData.this,"Doctor Record has been updated",Toast.LENGTH_SHORT).show();
                             }
                         })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateDoctorData.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                Nameupdat.setText("");
                Ageupdat.setText("");
                Numberupdat.setText("");
                Specitializationupdat.setText("");
                Addressupdat.setText("");
                Passwordupdat.setText("");
            }
        });
        ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Doctors")
                        .child(DocId)
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(UpdateDoctorData.this,"Doctor has been deleted",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateDoctorData.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                Nameupdat.setText("");
                Ageupdat.setText("");
                Numberupdat.setText("");
                Specitializationupdat.setText("");
                Addressupdat.setText("");
                Passwordupdat.setText("");
            }
        });
        ivphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Number));
                startActivity(intent);
            }
        });

    }
    private void openGallery() {
      /*  Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);*/
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    private String getFileExtension(Uri uri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mine= MimeTypeMap.getSingleton();
        return mine.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadImage(){
        if(imageUri!=null){
            StorageReference fileReference= mstorage.child(DocId+"."+getFileExtension(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler= new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                   // mProgressBar.setProgress(0);
                                }
                            },10000);
                            Toast.makeText(UpdateDoctorData.this,"Upload Successfully",Toast.LENGTH_LONG).show();
                            Upload upload= new Upload(DocId,taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                            String uploadId=dbref.push().getKey();
                            dbref.child(uploadId).setValue(upload);
                        }
                    })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                   Toast.makeText(UpdateDoctorData.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress =(100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    //mProgressBar.setProgress((int) progress);
                }
            });
        }
    }


    void init(){
        ivdoc= findViewById(R.id.ivdoc);
        ivedit= findViewById(R.id.ivedit);
        ivphone= findViewById(R.id.ivphone);
        ivdelete= findViewById(R.id.ivdelete);
        Nameupdat= findViewById(R.id.Nameupdat);
        Ageupdat= findViewById(R.id.Ageupdat);
        Numberupdat= findViewById(R.id.Numberupdat);
        Specitializationupdat= findViewById(R.id.Specitializationupdat);
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
        Specitializationupdat.setThreshold(1);
        Specitializationupdat.setAdapter(adapter);
        Addressupdat= findViewById(R.id.Addressupdat);
        Passwordupdat= findViewById(R.id.Passwordupdat);
        buttonupdat= findViewById(R.id.buttonupdat);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mstorage= FirebaseStorage.getInstance().getReference("DoctorImages");
        dbref=FirebaseDatabase.getInstance().getReference("DoctorsImages");
       // mProgressbar= findViewById(R.id.mProgressbar);
    }
    public void uploadimg(){

        if(imageUri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(UpdateDoctorData.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast
                            .makeText(UpdateDoctorData.this,
                                    "Failed " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress
                            = (100.0
                            * snapshot.getBytesTransferred()
                            / snapshot.getTotalByteCount());
                    progressDialog.setMessage(
                            "Uploaded "
                                    + (int)progress + "%");
                }
            });
    }
    }

}