package com.example.attex.teacheracademics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelStandardExam;

import java.util.List;

public class TeacherStandardResultAdapter extends RecyclerView.Adapter<TeacherStandardResultAdapter.TeacherStandardResultViewHolder> {

    private final List<ModelStandardExam> resultList;
    private final Context context;

    public TeacherStandardResultAdapter(List<ModelStandardExam> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherStandardResultAdapter.TeacherStandardResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherStandardResultAdapter.TeacherStandardResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_result_layout, null));

    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStandardResultAdapter.TeacherStandardResultViewHolder holder, int position) {
        ModelStandardExam result = resultList.get(position);

        holder.studentName.setText(result.getStudentName());
        holder.studentID.setText(result.getStudentID());
        holder.grade.setText(result.getGrade());
        holder.note.setText(result.getNote());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class TeacherStandardResultViewHolder extends RecyclerView.ViewHolder {

        TextView studentID, studentName, grade, note;

        public TeacherStandardResultViewHolder(@NonNull View itemView) {
            super(itemView);


            studentName = itemView.findViewById(R.id.studentName);
            studentID = itemView.findViewById(R.id.studentID);
            grade = itemView.findViewById(R.id.grade);
            note = itemView.findViewById(R.id.note);
        }
    }


}
