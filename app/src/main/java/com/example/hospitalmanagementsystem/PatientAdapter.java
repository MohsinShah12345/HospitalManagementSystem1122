package com.example.hospitalmanagementsystem;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import static androidx.core.content.ContextCompat.startActivity;
import static com.example.hospitalmanagementsystem.R.layout.updatepatientrecord;

public class PatientAdapter extends FirebaseRecyclerAdapter<Patient,PatientAdapter.PatientViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public PatientAdapter(@NonNull FirebaseRecyclerOptions options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PatientViewHolder holder, int position, @NonNull Patient model) {

        holder.tvpatient.setText(model.getName());
        holder.tvpatientid.setText(model.getCnic());
        if(model.getGender().equals("Female")){
            holder.patientimg.setImageResource(R.mipmap.women);
        }
        else
        {
            holder.patientimg.setImageResource(R.mipmap.men);
        }
            if(model.getDisease().equals("Brain")){

                holder.imgdisease.setImageResource(R.mipmap.brain);
            }
            else if(model.getDisease().equals("Cancer")){

                holder.imgdisease.setImageResource(R.mipmap.cancer1);
            }
            else if(model.getDisease().equals("Lungs")){

                holder.imgdisease.setImageResource(R.mipmap.lungs1);
            }
            else if(model.getDisease().equals("Eye")){

                holder.imgdisease.setImageResource(R.mipmap.eye1);
            }
            else if(model.getDisease().equals("Corona")){

                holder.imgdisease.setImageResource(R.mipmap.corona1);
            }
            else if(model.getDisease().equals("Diabetes")){

                holder.imgdisease.setImageResource(R.mipmap.diabetes1);
            }
            else if(model.getDisease().equals("Ear")){

                holder.imgdisease.setImageResource(R.mipmap.ear1);
            }
            else if(model.getDisease().equals("Heart")){

                holder.imgdisease.setImageResource(R.mipmap.heart1);
            }
            else if(model.getDisease().equals("Kidney")){

                holder.imgdisease.setImageResource(R.mipmap.kidney1);
            }
            else{
                holder.imgdisease.setImageResource(R.mipmap.corona1);
            }
            holder.imgdisease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Name=model.getName();
                    String Age=model.getAge();
                    String Gender=model.getGender();
                    String Disease=model.getDisease();
                    String Address=model.getAddress();
                    String ContactNo=model.getContactNo();
                    String Cnic=model.getCnic();
                    String Date=model.getDate();
                    String Appointment=model.getAppointment();
                    //String MyDoctor=model.getMyDoctor();
                    String Key=getRef(position).getKey();
                    Intent obj= new Intent(context , UpdatePatientData.class);
                    obj.putExtra("Name",Name);
                    obj.putExtra("Age",Age);
                    obj.putExtra("Gender",Gender);
                    obj.putExtra("Disease",Disease);
                    obj.putExtra("Address",Address);
                    obj.putExtra("ContactNo",ContactNo);
                    obj.putExtra("Cnic",Cnic);
                    obj.putExtra("Date",Date);
                    obj.putExtra("Appointment",Appointment);
                   // obj.putExtra("MyDoctor",MyDoctor);
                    obj.putExtra("Key",Key);
                    startActivity(context,obj,null);


                }
            });

    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.patientlayout,parent,false);
        return new PatientViewHolder(v);
    }


    public class PatientViewHolder extends RecyclerView.ViewHolder{
        TextView tvpatient;
        TextView tvpatientid;
        ImageView patientimg;
        ImageView imgdisease;
        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvpatient= itemView.findViewById(R.id.tvpatient);
            tvpatientid= itemView.findViewById(R.id.tvpatientid);
            patientimg= itemView.findViewById(R.id.patientimg);
            imgdisease= itemView.findViewById(R.id.imgdisease);

        }
    }

}
//3399085078983
/*DialogPlus dialog= DialogPlus.newDialog(context)
                            .setContentHolder(new ViewHolder(R.layout.updatepatientrecord))
                            .setGravity(Gravity.CENTER)
                            .setMargin(35,0,35,0)
                            .setExpanded(true)
                            .create();

                    View vi= dialog.getHolderView();
                    EditText patient1;
                    EditText patientAge1;
                    EditText Cninc1;
                    Button button1;
                    patient1= vi.findViewById(R.id.patient1);
                     patientAge1= vi.findViewById(R.id.patientAge1);
                     Cninc1= vi.findViewById(R.id.Cnic1);
                     button1= vi.findViewById(R.id.button1);
                    dialog.show();*/