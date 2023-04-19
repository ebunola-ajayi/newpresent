package com.example.attex.teachermain;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelAttendance;

import java.util.List;

public class TakeAttendance2Adapter extends RecyclerView.Adapter<TakeAttendance2Adapter.TakeAttendance2ViewHolder> {

    private final List<ModelAttendance> attendanceList;
    private final Context context;

    public TakeAttendance2Adapter(List<ModelAttendance> attendanceList, Context context) {
        this.attendanceList = attendanceList;
        this.context = context;
    }

    @NonNull
    @Override
    public TakeAttendance2Adapter.TakeAttendance2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TakeAttendance2Adapter.TakeAttendance2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.take_attendance_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull TakeAttendance2Adapter.TakeAttendance2ViewHolder holder, int position) {
        ModelAttendance attendance = attendanceList.get(position);

        holder.tvForeName.setText(attendance.getFirstName());
        holder.tvLastName.setText(attendance.getLastName());
        holder.tvStudentNo.setText(attendance.getStudentID());

        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.present.setText("Present");
                attendance.setAttendance("Present");
                System.out.println("Present" + holder.tvStudentNo.getText().toString());
                holder.present.setBackgroundColor(Color.BLUE);
                holder.absent.setBackgroundColor(Color.GRAY);
            }
        });

        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.absent.setText("Absent");
                attendance.setAttendance("Absent");
                System.out.println("Absent" + holder.tvStudentNo.getText().toString());
                holder.absent.setBackgroundColor(Color.BLUE);
                holder.present.setBackgroundColor(Color.GRAY);
            }
        });

    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public static class TakeAttendance2ViewHolder extends RecyclerView.ViewHolder{

        TextView tvForeName, tvLastName, tvStudentNo;
        Button present, absent;

        public TakeAttendance2ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvForeName = itemView.findViewById(R.id.tvForeName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvStudentNo = itemView.findViewById(R.id.tvStudentNo);

            present = itemView.findViewById(R.id.presentBtn);
            absent = itemView.findViewById(R.id.absentBtn);
        }

    }
}
