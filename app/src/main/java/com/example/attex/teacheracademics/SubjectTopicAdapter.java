package com.example.attex.teacheracademics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelSubjectTopics;

import java.util.List;

public class SubjectTopicAdapter extends RecyclerView.Adapter<SubjectTopicAdapter.SubjectTopicViewHolder> {

    private final List<ModelSubjectTopics> list;
    private final Context context;

    public SubjectTopicAdapter(@NonNull Context context, List<ModelSubjectTopics> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectTopicAdapter.SubjectTopicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.maths_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectTopicViewHolder holder, int position) {
        ModelSubjectTopics topics = list.get(position);

        holder.gradeET.setText(topics.getGrade());
        holder.topicET.setText(topics.getTopic());
        holder.noteET.setText(topics.getNote());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(int position, ModelSubjectTopics topics){
        list.add(position, topics);
        notifyItemInserted(position);
    }

    public static class SubjectTopicViewHolder extends RecyclerView.ViewHolder{

        TextView topicET;
        TextView gradeET;
        TextView noteET;

        public SubjectTopicViewHolder(@NonNull View itemView) {
            super(itemView);

            topicET = itemView.findViewById(R.id.topicET);
            gradeET = itemView.findViewById(R.id.gradeET);
            noteET = itemView.findViewById(R.id.noteET);



        }
    }

}
