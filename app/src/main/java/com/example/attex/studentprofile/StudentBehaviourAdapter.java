package com.example.attex.studentprofile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelBehaviour;
import com.example.attex.teachermain.AddNoteActivity;

import java.util.List;

public class StudentBehaviourAdapter extends RecyclerView.Adapter<StudentBehaviourAdapter.StudentBehaviourViewHolder> {

    private final List<ModelBehaviour> behaviourList;
    private final Context context;

    String schoolID, classGrade, classID, studentID;

    public StudentBehaviourAdapter(Context context, List<ModelBehaviour> behaviourList, String schoolID, String classGrade, String classID, String studentID) {
        this.behaviourList = behaviourList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
        this.studentID = studentID;
    }


    @NonNull
    @Override
    public StudentBehaviourAdapter.StudentBehaviourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentBehaviourViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.student_behaviour_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentBehaviourViewHolder holder, int position) {
        ModelBehaviour behaviour = behaviourList.get(position);

        holder.dateTV.setText(behaviour.getDate());
        holder.feedbackTV.setText(behaviour.getFeedback());
        holder.commentTV.setText(behaviour.getComment());

        holder.dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentBehavAddActivity.class);
                intent.putExtra("date", behaviour.getDate());
                intent.putExtra("feedback", behaviour.getFeedback());
                intent.putExtra("comment", behaviour.getComment());
                intent.putExtra("behaviourID", behaviour.getBehaviourID());
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("studentID", studentID);
                Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return behaviourList.size();
    }


    public static class StudentBehaviourViewHolder extends RecyclerView.ViewHolder{

        TextView dateTV, commentTV, feedbackTV;

        public StudentBehaviourViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.dateTV);
            commentTV = itemView.findViewById(R.id.commentTV);
            feedbackTV = itemView.findViewById(R.id.feedbackTV);
        }
    }
}
