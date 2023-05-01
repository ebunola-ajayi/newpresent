package com.example.attex.teacheracademics;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelAcademics;

import java.util.List;

public class SubjectViewAcademicAdapter extends RecyclerView.Adapter<SubjectViewAcademicAdapter.SubjectViewAcademicViewHolder> {
    private final List<ModelAcademics> subjectList;
    private final Context context;

    String teacherID, subject, schoolID, classGrade;

    public SubjectViewAcademicAdapter(List<ModelAcademics> subjectList, Context context, String teacherID, String subject, String schoolID, String classGrade) {
        this.subjectList = subjectList;
        this.context = context;
        this.teacherID = teacherID;
        this.subject = subject;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
    }

    @NonNull
    @Override
    public SubjectViewAcademicAdapter.SubjectViewAcademicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectViewAcademicAdapter.SubjectViewAcademicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_subjects_layout, null));

    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewAcademicAdapter.SubjectViewAcademicViewHolder holder, int position) {
        ModelAcademics subjects = subjectList.get(position);

        holder.topic.setText(subjects.getTopic());

        holder.topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubjectChartActivity.class);
                intent.putExtra("teacherID", teacherID);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("topic", subjects.getTopic());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }


    public static class SubjectViewAcademicViewHolder extends RecyclerView.ViewHolder{

        Button topic;

        public SubjectViewAcademicViewHolder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.topic);

        }
    }


}
