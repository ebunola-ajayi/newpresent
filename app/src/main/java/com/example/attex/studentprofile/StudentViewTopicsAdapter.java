package com.example.attex.studentprofile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelAcademics;

import java.util.List;

public class StudentViewTopicsAdapter extends RecyclerView.Adapter<StudentViewTopicsAdapter.StudentViewTopicsViewHolder> {

    private final List<ModelAcademics> topicList;
    private final Context context;

    String schoolID, classGrade, classID, studentID, subject;

    public StudentViewTopicsAdapter(List<ModelAcademics> topicList, Context context, String schoolID, String classGrade, String classID, String studentID, String subject) {
        this.topicList = topicList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
        this.studentID = studentID;
        this.subject = subject;
    }

    @NonNull
    @Override
    public StudentViewTopicsAdapter.StudentViewTopicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewTopicsAdapter.StudentViewTopicsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_subjects_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewTopicsAdapter.StudentViewTopicsViewHolder holder, int position) {
        ModelAcademics topics = topicList.get(position);

        holder.topic.setText(topics.getTopic());

        holder.topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentGradeActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("studentID", studentID);
                intent.putExtra("topic", topics.getTopic());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public static class StudentViewTopicsViewHolder extends  RecyclerView.ViewHolder{

        Button topic;

        public StudentViewTopicsViewHolder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.topic);
        }
    }


}
