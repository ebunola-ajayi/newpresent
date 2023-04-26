package com.example.attex.parentacademics;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelAcademics;

import java.util.List;

public class ParentViewSubjectsAdapter extends RecyclerView.Adapter<ParentViewSubjectsAdapter.ParentViewSubjectsViewHolder> {
    private final List<ModelAcademics> subjectList;
    private final Context context;

    String schoolID, classID, classGrade, studentID, subject;

    public ParentViewSubjectsAdapter(List<ModelAcademics> subjectList, Context context, String schoolID, String classGrade, String classID, String studentID, String subject) {
        this.subjectList = subjectList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
        this.studentID = studentID;
        this.subject = subject;

    }

    @NonNull
    @Override
    public ParentViewSubjectsAdapter.ParentViewSubjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentViewSubjectsAdapter.ParentViewSubjectsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_subjects_layout, null));

    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewSubjectsAdapter.ParentViewSubjectsViewHolder holder, int position) {
        ModelAcademics subjects = subjectList.get(position);

        holder.subject.setText(subjects.getTopic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ParentViewTopicGradeActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                intent.putExtra("topic", subjects.getTopic());
                intent.putExtra("studentID", studentID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class ParentViewSubjectsViewHolder extends RecyclerView.ViewHolder{
        TextView subject;

        public ParentViewSubjectsViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.topic);

        }
    }
}
