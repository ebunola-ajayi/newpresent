package com.example.attex.adminmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelMedical;
import com.example.attex.parentmain.MedicalListAdapter;

import java.util.List;

public class StudentMedicalAdapter extends RecyclerView.Adapter<StudentMedicalAdapter.StudentMedicalViewHolder> {


    private final List<ModelMedical> medicalList;
    private final Context context;

    public StudentMedicalAdapter(List<ModelMedical> medicalList, Context context) {
        this.medicalList = medicalList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentMedicalAdapter.StudentMedicalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentMedicalAdapter.StudentMedicalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_list_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentMedicalAdapter.StudentMedicalViewHolder holder, int position) {
        ModelMedical medical = medicalList.get(position);

        holder.medicalType.setText(medical.getMedicalType());
        holder.medicalTitle.setText(medical.getMedicalTitle());
        holder.note.setText(medical.getNote());
    }

    @Override
    public int getItemCount() {
        return medicalList.size();
    }

    public class StudentMedicalViewHolder extends RecyclerView.ViewHolder{
        TextView medicalType, medicalTitle, note;

        public StudentMedicalViewHolder(@NonNull View itemView) {
            super(itemView);

            medicalType = itemView.findViewById(R.id.medicalType);
            medicalTitle = itemView.findViewById(R.id.medicalTitle);
            note = itemView.findViewById(R.id.note);
        }
    }

}
