package com.example.hospitalmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DoctorPatientAdapter extends FirebaseRecyclerAdapter<PatientDoc, DoctorPatientAdapter.DoctorPatientViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DoctorPatientAdapter(@NonNull FirebaseRecyclerOptions<PatientDoc> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorPatientViewHolder holder, int position, @NonNull PatientDoc model) {
        holder.dptv.setText(model.getName());
        holder.dp2tv.setText(model.getCnic());
        if(model.getGender().equals("Male")){
            holder.dpimg.setImageResource(R.mipmap.maledoctor);
        }
        else if (model.getGender().equals("Female")){
            holder.dpimg.setImageResource(R.mipmap.femaledoctor);
        }
        else {
            holder.dpimg.setImageResource(R.mipmap.maledoctor);
        }

        if (model.getDisease().equals("Brain")) {

            holder.dpimgdisease.setImageResource(R.mipmap.brain);
        } else if (model.getDisease().equals("Cancer")) {

            holder.dpimgdisease.setImageResource(R.mipmap.cancer1);
        } else if (model.getDisease().equals("Lungs")) {

            holder.dpimgdisease.setImageResource(R.mipmap.lungs1);
        } else if (model.getDisease().equals("Eye")) {

            holder.dpimgdisease.setImageResource(R.mipmap.eye1);
        } else if (model.getDisease().equals("Corona")) {

            holder.dpimgdisease.setImageResource(R.mipmap.corona1);
        } else if (model.getDisease().equals("Diabetes")) {

            holder.dpimgdisease.setImageResource(R.mipmap.diabetes1);
        } else if (model.getDisease().equals("Ear")) {

            holder.dpimgdisease.setImageResource(R.mipmap.ear1);
        } else if (model.getDisease().equals("Heart")) {

            holder.dpimgdisease.setImageResource(R.mipmap.heart1);
        } else if (model.getDisease().equals("Kidney")) {

            holder.dpimgdisease.setImageResource(R.mipmap.kidney1);
        } else {
            holder.dpimgdisease.setImageResource(R.mipmap.corona1);
        }

    }
    @NonNull
    @Override
    public DoctorPatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.docpatlayout,parent,false);
        return new DoctorPatientViewHolder(v);

    }

    public class DoctorPatientViewHolder extends RecyclerView.ViewHolder {

        TextView dptv;
        TextView dp2tv;
        ImageView dpimg;
        ImageView dpimgdisease;
        public DoctorPatientViewHolder(@NonNull View itemView) {
            super(itemView);
            dptv= itemView.findViewById(R.id.dptv);
            dp2tv= itemView.findViewById(R.id.dp2tv);
            dpimg= itemView.findViewById(R.id.dpimg);
            dpimgdisease= itemView.findViewById(R.id.dpimgdisease);
        }
    }
}
