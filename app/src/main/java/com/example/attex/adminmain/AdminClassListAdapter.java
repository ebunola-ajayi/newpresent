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
import com.example.attex.teachermain.ChooseDateActivity;

import java.util.List;

public class AdminClassListAdapter extends RecyclerView.Adapter<AdminClassListAdapter.AdminClassListViewHolder> {

    private final List<ModelTeacher> teacherList;
    private final Context context;
    String schoolID, classGrade;

    public AdminClassListAdapter(List<ModelTeacher> teacherList, Context context, String schoolID, String classGrade) {
        this.teacherList = teacherList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
    }

    @NonNull
    @Override
    public AdminClassListAdapter.AdminClassListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminClassListAdapter.AdminClassListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teachers_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminClassListAdapter.AdminClassListViewHolder holder, int position) {
        ModelTeacher teacher = teacherList.get(position);

        holder.lastName.setText(teacher.getTeacherName());
        String classID = teacher.getClassID();

        holder.lastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminAddStudentActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("teacherEmail", teacher.getEmail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public class AdminClassListViewHolder extends RecyclerView.ViewHolder{
        Button lastName;

        public AdminClassListViewHolder(@NonNull View itemView) {
            super(itemView);

            lastName = itemView.findViewById(R.id.lastName);
        }
    }
}
