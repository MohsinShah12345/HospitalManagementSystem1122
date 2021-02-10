package com.example.hospitalmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.core.content.ContextCompat.startActivity;


public class DoctorAdapter  extends FirebaseRecyclerAdapter<Doctors, DoctorAdapter.DoctorViewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;

    public DoctorAdapter(@NonNull FirebaseRecyclerOptions<Doctors> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorViewholder holder, int position, @NonNull Doctors model) {
        holder.tvdoctor.setText(model.getName());
        holder.tvdoctorid.setText(model.getDocId());
        holder.docimg.setImageResource(R.mipmap.maledoctor);

        if(model.getSpecitialization().equals("Brain")){

            holder.diseaseimg.setImageResource(R.mipmap.brain2);
        }
        else if(model.getSpecitialization().equals("Cancer")){

            holder.diseaseimg.setImageResource(R.mipmap.cancer1);
        }
        else if(model.getSpecitialization().equals("Lungs")){

            holder.diseaseimg.setImageResource(R.mipmap.lungs1);
        }
        else if(model.getSpecitialization().equals("Eye")){

            holder.diseaseimg.setImageResource(R.mipmap.eye1);
        }
        else if(model.getSpecitialization().equals("Corona")){

            holder.diseaseimg.setImageResource(R.mipmap.corona1);
        }
        else if(model.getSpecitialization().equals("Diabetes")){

            holder.diseaseimg.setImageResource(R.mipmap.diabetes1);
        }
        else if(model.getSpecitialization().equals("Ear")){

            holder.diseaseimg.setImageResource(R.mipmap.ear1);
        }
        else if(model.getSpecitialization().equals("Heart")){

            holder.diseaseimg.setImageResource(R.mipmap.heart1);
        }
        else if(model.getSpecitialization().equals("Kidney")){

            holder.diseaseimg.setImageResource(R.mipmap.kidney1);
        }
        else{
            holder.diseaseimg.setImageResource(R.mipmap.corona1);
        }

          holder.diseaseimg.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                        String Name=model.getName();
                        String Age= model.getAge();
                        String Number= model.getNumber();
                        String Address= model.getAddress();
                        String Specitialization= model.getSpecitialization();
                        String DocId= model.getDocId();
                        String Password= model.getPassword();
/*

 DialogPlus dialog = DialogPlus.newDialog(context)
                          .setContentHolder(new ViewHolder(R.layout.updatedoctor))
                          .setGravity(Gravity.CENTER)
                          .setMargin(35,0,35,0)
                          .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                          .create();
                  dialog.show();
                  View vi= dialog.getHolderView();
                  EditText nameok;
                  EditText Ageok;
                  EditText numok;
                  AutoCompleteTextView diseaseok;
                  EditText Addrok;
                  EditText idok;
                  EditText pasok;
                  Button Appointok;

                 nameok= vi.findViewById(R.id.nameok);
                  Ageok= vi.findViewById(R.id.Ageok);
                  numok= vi.findViewById(R.id.numok);
                  diseaseok= vi.findViewById(R.id.diseaseok);
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

                  ArrayAdapter<String> adapter1= new ArrayAdapter<String>( context, R.layout.customdesignlayout , MyArrayList);
                  diseaseok.setThreshold(1);
                  diseaseok.setAdapter(adapter1);
                  Addrok= vi.findViewById(R.id.Addrok);
                  idok= vi.findViewById(R.id.idok);
                  pasok= vi.findViewById(R.id.pasok);
                  Appointok= vi.findViewById(R.id.Appointok);
                  nameok.setText(model.getName());
                  Ageok.setText(model.getAge());
                  numok.setText(model.getNumber());
                  diseaseok.setText(model.getSpecitialization());
                  Addrok.setText(model.getAddress());
                  idok.setText(model.getDocId());
                  pasok.setText(model.getPassword());


                  Appointok.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Map<String,Object> upDoctor1= new HashMap<>();
                          upDoctor1.put("Name",nameok.getText().toString().trim());
                          upDoctor1.put("Age",Ageok.getText().toString().trim());
                          upDoctor1.put("Number",numok.getText().toString().trim());
                          upDoctor1.put("Specitialization",diseaseok.getText().toString().trim());
                          upDoctor1.put("Address",Addrok.getText().toString().trim());
                          upDoctor1.put("DocId",idok.getText().toString().trim());
                          upDoctor1.put("Password",pasok.getText().toString().trim());

                          FirebaseDatabase.getInstance().getReference().child("Doctors")
                                  .child(getRef(position).getKey().toString().trim())
                                  .updateChildren(upDoctor1).addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  Toast.makeText(context,"Data Added Successfully",Toast.LENGTH_SHORT).show();
                              }
                          });
                          nameok.setText("");
                          Ageok.setText("");
                          numok.setText("");
                          diseaseok.setText("");
                          Addrok.setText("");
                          idok.setText("");
                          pasok.setText("");
                      }
                  });

*/
                 Intent obj= new Intent(context , UpdateDoctorData.class);
                  obj.putExtra("Name",Name);
                  obj.putExtra("Age",Age);
                  obj.putExtra("Number",Number);
                  obj.putExtra("Address",Address);
                  obj.putExtra("Specitialization",Specitialization);
                  obj.putExtra("DocId",DocId);
                  obj.putExtra("Password",Password);

                  startActivity(context,obj,null);
              }
          });
    }

    @NonNull
    @Override
    public DoctorViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorlayout,parent,false);
        return new DoctorViewholder(view);
    }

    public class DoctorViewholder extends RecyclerView.ViewHolder{
           TextView tvdoctor;
           TextView tvdoctorid;
        ImageView docimg;
        ImageView diseaseimg;
        public DoctorViewholder(@NonNull View itemView) {
            super(itemView);
            tvdoctor = itemView.findViewById(R.id.tvdoctor);
            tvdoctorid = itemView.findViewById(R.id.tvdoctorid);
            docimg = itemView.findViewById(R.id.docimg);
            diseaseimg = itemView.findViewById(R.id.diseaseimg);
        }
    }
}
/* holder.diseaseimg.setImageResource(R.mipmap.diabetes1);
       /*if(model.getSpecitialization().equals("Brain")){

           holder.diseaseimg.setImageResource(R.mipmap.diabetes1);
       }
        else if(model.getSpecitialization().equals("cancer")){

           holder.diseaseimg.setImageResource(R.mipmap.cancer);
        }
        else if(model.getSpecitialization().equals("lungs")){

           holder.diseaseimg.setImageResource(R.mipmap.lungs);
        }
        else if(model.getSpecitialization().equals("eyes")){

           holder.diseaseimg.setImageResource(R.mipmap.eye);
        }
        else if(model.getSpecitialization().equals("corona")){

           holder.diseaseimg.setImageResource(R.mipmap.corona1);
        }
        else{

           holder.diseaseimg.setImageResource(R.mipmap.kidney);
       }
//holder.docimg.setImageResource(R.mipmap.maledoctor);
//holder.diseaseimg.setImageResource(R.mipmap.kidney);
// String age=model.getAge();
//String Address=model.getAddress();
//String Number=model.getNumber();
//String Password=model.getPassword();
//String Specitialization=model.getSpecitialization();*/
/**/
/* DialogPlus dialog = DialogPlus.newDialog(context)
                          .setContentHolder(new ViewHolder(R.layout.updatedoctor))
                          .setGravity(Gravity.CENTER)
                          .setMargin(35,0,35,0)
                          .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                          .create();
                  dialog.show();
                  View vi= dialog.getHeaderView();
                  // TextView utxt;
                  EditText upetdocname;
                  EditText upetdocAge;
                  EditText upetdoctnum;
                  AutoCompleteTextView upetdiseaseSpec;
                  EditText upetdocAddr;
                  EditText upetdoctid;
                  EditText upetdocpas;
                  Button upbtnfixAppoint;
                  //utxt= view.findViewById(R.id.utxt);
                /* upetdocname= vi.findViewById(R.id.updetdocname);
                  upetdocAge= vi.findViewById(R.id.updetdocAge);
                  upetdoctnum= vi.findViewById(R.id.updetdoctnum);
                  upetdiseaseSpec= vi.findViewById(R.id.updetdiseaseSpec);
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
                  //ArrayAdapter<String> adapter1= new ArrayAdapter<String>( context, R.layout.customdesignlayout , MyArrayList);
                  //upetdiseaseSpec.setThreshold(1);
                  //upetdiseaseSpec.setAdapter(adapter1);
                //  upetdocAddr= vi.findViewById(R.id.updetdocAddr);
                  //upetdoctid= vi.findViewById(R.id.updetdoctid);
                  //upetdocpas= vi.findViewById(R.id.updetdocpas);
                  //upbtnfixAppoint= vi.findViewById(R.id.updbtnfixAppoint);
               /*   upetdocname.setText(model.getName());
                  upetdocAge.setText(model.getAge());
                  upetdoctnum.setText(model.getNumber());
                  upetdiseaseSpec.setText(model.getSpecitialization());
                  upetdocAddr.setText(model.getAddress());
                  upetdoctid.setText(model.getDocId());
                  upetdocpas.setText(model.getPassword());*/

