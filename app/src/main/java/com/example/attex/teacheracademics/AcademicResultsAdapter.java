package com.example.attex.teacheracademics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelAcademics;

import java.util.List;

public class AcademicResultsAdapter extends RecyclerView.Adapter<AcademicResultsAdapter.AcademicResultsViewHolder> {

    private final List<ModelAcademics> resultList;
    private final Context context;

    public AcademicResultsAdapter(List<ModelAcademics> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public AcademicResultsAdapter.AcademicResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AcademicResultsAdapter.AcademicResultsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_result_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AcademicResultsAdapter.AcademicResultsViewHolder holder, int position) {
        ModelAcademics result = resultList.get(position);

        holder.studentName.setText(result.getName() + " - ");
        holder.studentID.setText(result.getStudentID());
        holder.grade.setText(result.getGrade() + "%");
        holder.note.setText(result.getNote());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class AcademicResultsViewHolder extends  RecyclerView.ViewHolder{

        TextView studentID, studentName, grade, note;

        public AcademicResultsViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.studentName);
            studentID = itemView.findViewById(R.id.studentID);
            grade = itemView.findViewById(R.id.grade);
            note = itemView.findViewById(R.id.note);
        }
    }


}
