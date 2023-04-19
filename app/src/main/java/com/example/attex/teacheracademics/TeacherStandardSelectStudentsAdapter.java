package com.example.attex.teacheracademics;

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

import java.util.List;

public class TeacherStandardSelectStudentsAdapter extends RecyclerView.Adapter<TeacherStandardSelectStudentsAdapter.TeacherStandardSelectStudentsViewHolder> {

    private final List<ModelStudent> studentsList;
    private final Context context;
    String classGrade;
    String schoolID;
    String teacherID;

    public TeacherStandardSelectStudentsAdapter(List<ModelStudent> studentsList, Context context, String classGrade, String schoolID, String teacherID) {
        this.studentsList = studentsList;
        this.context = context;
        this.classGrade = classGrade;
        this.schoolID = schoolID;
        this.teacherID = teacherID;
    }

    @NonNull
    @Override
    public TeacherStandardSelectStudentsAdapter.TeacherStandardSelectStudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherStandardSelectStudentsAdapter.TeacherStandardSelectStudentsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_student_grade_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStandardSelectStudentsViewHolder holder, int position) {
        ModelStudent student = studentsList.get(position);
        holder.firstName.setText(student.getFirstName());
        holder.lastName.setText(student.getLastName());
        holder.id.setText(student.getStudentID());

        // holder.topic.setText();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TeacherStandardRecordActivity.class);




                intent.putExtra("studentID", student.getStudentID());
                intent.putExtra("firstName", student.getFirstName());
                intent.putExtra("lastName", student.getLastName());
                intent.putExtra("teacherID", teacherID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                //intent.putExtra("subject", );

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class TeacherStandardSelectStudentsViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, id, subject, topic;

        public TeacherStandardSelectStudentsViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            id = itemView.findViewById(R.id.studentNo);

            subject = itemView.findViewById(R.id.subject);
            topic = itemView.findViewById(R.id.topic);
        }
    }
}
