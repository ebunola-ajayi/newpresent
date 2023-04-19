package com.example.attex.adminmain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelTeacher;
import com.example.attex.teacheracademics.TeacherStandardSelectStudentsAdapter;

import java.util.List;

public class AdminTeacherListAdapter extends RecyclerView.Adapter<AdminTeacherListAdapter.AdminTeacherListViewHolder> {

    private final List<ModelTeacher> teacherList;
    private final Context context;

    String schoolID;
    String classGrade;

    public AdminTeacherListAdapter(List<ModelTeacher> teacherList, Context context, String schoolID, String classGrade) {
        this.teacherList = teacherList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
    }

    @NonNull
    @Override
    public AdminTeacherListAdapter.AdminTeacherListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminTeacherListAdapter.AdminTeacherListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teachers_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTeacherListAdapter.AdminTeacherListViewHolder holder, int position) {
        ModelTeacher teachers = teacherList.get(position);

        String teacherID = teachers.getTeacherID();
        holder.lastName.setText(teachers.getTeacherName() + " - " + teachers.getTeacherID());

        holder.lastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminTeacherProfileActivity.class);
                intent.putExtra("classID", teacherID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public static class AdminTeacherListViewHolder extends RecyclerView.ViewHolder{

        Button lastName;

        public AdminTeacherListViewHolder(@NonNull View itemView) {
            super(itemView);

            lastName = itemView.findViewById(R.id.lastName);
        }
    }
}
