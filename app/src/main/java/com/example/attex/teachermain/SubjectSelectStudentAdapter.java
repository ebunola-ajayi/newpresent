package com.example.attex.teachermain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelStudent;
import com.example.attex.teacheracademics.SubjectAddRecordActivity;

import java.util.List;

public class SubjectSelectStudentAdapter extends RecyclerView.Adapter<SubjectSelectStudentAdapter.SubjectSelectStudentViewHolder> {

    private final List<ModelStudent> studentsList;
    private final Context context;
    String subject, topic, classGrade, classID, schoolID;

    public SubjectSelectStudentAdapter(List<ModelStudent> studentsList, Context context, String subject, String topic, String classGrade, String classID, String schoolID) {
        this.studentsList = studentsList;
        this.context = context;
        this.subject = subject;
        this.topic = topic;
        this.classGrade = classGrade;
        this.classID = classID;
        this.schoolID = schoolID;
    }

    @NonNull
    @Override
    public SubjectSelectStudentAdapter.SubjectSelectStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectSelectStudentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_student_grade_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectSelectStudentViewHolder holder, int position) {

        ModelStudent student = studentsList.get(position);
        holder.firstName.setText(student.getFirstName());
        holder.lastName.setText(student.getLastName());
        holder.id.setText(student.getStudentID());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubjectAddRecordActivity.class);
                intent.putExtra("studentID", student.getStudentID());
                intent.putExtra("firstName", student.getFirstName());
                intent.putExtra("lastName", student.getLastName());
                intent.putExtra("classID", classID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("subject", subject);
                intent.putExtra("topic", topic);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class SubjectSelectStudentViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, id, subject, topic;

        public SubjectSelectStudentViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            id = itemView.findViewById(R.id.studentNo);
            subject = itemView.findViewById(R.id.subject);
            topic = itemView.findViewById(R.id.topic);
        }
    }
}
