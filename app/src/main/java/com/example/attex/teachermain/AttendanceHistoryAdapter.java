package com.example.attex.teachermain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelAttendance;

import java.util.List;

public class AttendanceHistoryAdapter extends RecyclerView.Adapter<AttendanceHistoryAdapter.AttendanceHistoryViewHolder> {

    private final List<ModelAttendance> attendanceList;
    private final Context context;

    public AttendanceHistoryAdapter(List<ModelAttendance> attendanceList, Context context) {
        this.attendanceList = attendanceList;
        this.context = context;
    }

    @NonNull
    @Override
    public AttendanceHistoryAdapter.AttendanceHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AttendanceHistoryAdapter.AttendanceHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_class_attendance, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceHistoryAdapter.AttendanceHistoryViewHolder holder, int position) {
        ModelAttendance attendance = attendanceList.get(position);

        holder.fullNameTV.setText(attendance.getFirstName() + " " + attendance.getLastName());
        holder.attendanceTV.setText(attendance.getAttendance());
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class AttendanceHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView fullNameTV, attendanceTV;

        public AttendanceHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            fullNameTV = itemView.findViewById(R.id.fullNameTV);
            attendanceTV = itemView.findViewById(R.id.attendanceTV);
        }
    }
}
