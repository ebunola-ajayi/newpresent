package com.example.attex.studentprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelAcademics;
import com.example.attex.models.ModelAttendance;
import com.example.attex.teachermain.TeacherNotesAdapter;

import java.util.List;

public class StudenceAttendanceAdapter extends RecyclerView.Adapter<StudenceAttendanceAdapter.StudenceAttendanceViewHolder> {

    private final List<ModelAttendance> attendanceList;
    private final Context context;

    public StudenceAttendanceAdapter(List<ModelAttendance> attendanceList, Context context) {
        this.attendanceList = attendanceList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudenceAttendanceAdapter.StudenceAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudenceAttendanceAdapter.StudenceAttendanceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull StudenceAttendanceAdapter.StudenceAttendanceViewHolder holder, int position) {
        ModelAttendance attendance = attendanceList.get(position);

        holder.dateTV.setText(attendance.getDate());
        holder.attendanceTV.setText(attendance.getAttendance());

    }


    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class StudenceAttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView attendanceTV, dateTV;
        public StudenceAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.dateTV);
            attendanceTV = itemView.findViewById(R.id.attendanceTV);
        }
    }
}
