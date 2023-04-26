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

public class AdminTeacherList3Adapter extends RecyclerView.Adapter<AdminTeacherList3Adapter.AdminTeacherList3ViewHolder> {
    private final List<ModelTeacher> teacherList;
    private final Context context;
    String schoolID, classGrade;

    public AdminTeacherList3Adapter(List<ModelTeacher> teacherList, Context context, String schoolID, String classGrade) {
        this.teacherList = teacherList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
    }

    @NonNull
    @Override
    public AdminTeacherList3Adapter.AdminTeacherList3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminTeacherList3Adapter.AdminTeacherList3ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teachers_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTeacherList3Adapter.AdminTeacherList3ViewHolder holder, int position) {
        ModelTeacher teacher = teacherList.get(position);

        holder.lastName.setText(teacher.getLastName());
        String classID = teacher.getClassID();//change to classID
        holder.lastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("working");
                Intent intent = new Intent(context, ChooseDateActivity.class);
                intent.putExtra("classID", classID);
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

    public class AdminTeacherList3ViewHolder extends RecyclerView.ViewHolder{
        Button lastName;

        public AdminTeacherList3ViewHolder(@NonNull View itemView) {
            super(itemView);
            lastName = itemView.findViewById(R.id.lastName);
        }
    }
}
