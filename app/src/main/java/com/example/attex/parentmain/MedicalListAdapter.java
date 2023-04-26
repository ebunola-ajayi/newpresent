package com.example.attex.parentmain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelMedical;

import java.util.List;

public class MedicalListAdapter extends RecyclerView.Adapter<MedicalListAdapter.MedicalListViewHolder> {

    private final List<ModelMedical> medicalList;
    private final Context context;

    String schoolID, classGrade, classID, studentID;

    public MedicalListAdapter(List<ModelMedical> medicalList, Context context, String schoolID, String classGrade, String classID, String studentID) {
        this.medicalList = medicalList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
        this.studentID = studentID;
    }

    @NonNull
    @Override
    public MedicalListAdapter.MedicalListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicalListAdapter.MedicalListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_list_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalListAdapter.MedicalListViewHolder holder, int position) {
        ModelMedical medical = medicalList.get(position);

        holder.medicalType.setText(medical.getMedicalType());
        holder.medicalTitle.setText(medical.getMedicalTitle());
        holder.note.setText(medical.getNote());

        holder.medicalType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChildAddMedicalActivity.class);
                intent.putExtra("medicalType", medical.getMedicalType());
                intent.putExtra("medicalTitle", medical.getMedicalTitle());
                intent.putExtra("note", medical.getNote());
                intent.putExtra("medicalID", medical.getMedicalID());
                intent.putExtra("studentID", studentID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicalList.size();
    }

    public class MedicalListViewHolder extends RecyclerView.ViewHolder{
        TextView medicalType, medicalTitle, note;

        public MedicalListViewHolder(@NonNull View itemView) {
            super(itemView);

            medicalType = itemView.findViewById(R.id.medicalType);
            medicalTitle = itemView.findViewById(R.id.medicalTitle);
            note = itemView.findViewById(R.id.note);
        }
    }
}
