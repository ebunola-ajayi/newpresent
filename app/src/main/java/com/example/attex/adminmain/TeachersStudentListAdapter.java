package com.example.attex.adminmain;

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
import com.example.attex.studentprofile.StudentProfileActivity;
import com.example.attex.teachermain.ViewStudentsAdapter;

import java.util.List;

public class TeachersStudentListAdapter extends RecyclerView.Adapter<TeachersStudentListAdapter.TeachersStudentListViewHolder> {

    private final List<ModelStudent> studentList;
    private final Context context;
    String schoolID, classGrade, classID;

    public TeachersStudentListAdapter(List<ModelStudent> studentList, Context context, String schoolID, String classGrade, String classID) {
        this.studentList = studentList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
    }

    @NonNull
    @Override
    public TeachersStudentListAdapter.TeachersStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeachersStudentListAdapter.TeachersStudentListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersStudentListAdapter.TeachersStudentListViewHolder holder, int position) {
        ModelStudent student = studentList.get(position);

        //setting student details to text view
        holder.firstNameTxt.setText(student.getFirstName());
        holder.lastNameTxt.setText(student.getLastName());
        holder.idTxt.setText(student.getStudentID());
        holder.dobTxt.setText(student.getDateOfBirth());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminStudentProfileActivity.class);
                intent.putExtra("studentID", student.getStudentID());
                intent.putExtra("firstName", student.getFirstName());
                intent.putExtra("lastName", student.getLastName());
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class TeachersStudentListViewHolder extends RecyclerView.ViewHolder{

        TextView firstNameTxt, lastNameTxt, idTxt, dobTxt;

        public TeachersStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTxt = itemView.findViewById(R.id.tvForeName);
            lastNameTxt = itemView.findViewById(R.id.tvLastName);
            idTxt = itemView.findViewById(R.id.tvStudentNo);
            dobTxt = itemView.findViewById(R.id.tvDOB);
        }
    }
}
