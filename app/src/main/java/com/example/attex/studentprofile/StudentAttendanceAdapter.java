package com.example.attex.studentprofile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import com.example.attex.R;
import com.example.attex.models.ModelAttendance;

import java.util.List;


public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.StudentAttendanceViewHolder> {

    private Context mContext;
    private List<ModelAttendance> attendanceList;


    public StudentAttendanceAdapter(Context mContext, List<ModelAttendance> attendanceList) {
        this.mContext = mContext;
        this.attendanceList = attendanceList;
    }

    @NonNull
    @Override
    public StudentAttendanceAdapter.StudentAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentAttendanceAdapter.StudentAttendanceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.student_attendance_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAttendanceAdapter.StudentAttendanceViewHolder holder, int position) {
        ModelAttendance att = attendanceList.get(position);

        holder.attendance.setText(att.getAttendance());
        holder.date.setText(att.getDate());
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public static class StudentAttendanceViewHolder extends  RecyclerView.ViewHolder{

        TextView attendance;
        TextView date;

        public StudentAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            attendance = itemView.findViewById(R.id.attendance);
            date = itemView.findViewById(R.id.date);

        }
    }

}
