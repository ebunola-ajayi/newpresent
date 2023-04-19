package com.example.attex.studentprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelAttendance;
import com.example.attex.models.ModelBehaviour;
import com.example.attex.teachermain.TeacherNotesAdapter;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>{

    private Context mContext;
    private List<ModelBehaviour> feedback;

    public FeedbackAdapter(Context mContext, List<ModelBehaviour> feedback) {
        this.mContext = mContext;
        this.feedback = feedback;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedbackAdapter.FeedbackViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        ModelBehaviour behaviour = feedback.get(position);
        holder.feedbackTV.setText(behaviour.getFeedback());

    }

    @Override
    public int getItemCount() {
        return feedback.size();
    }

    public static class FeedbackViewHolder extends RecyclerView.ViewHolder{

        TextView feedbackTV;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);

            feedbackTV = itemView.findViewById(R.id.feedbackTV);
        }
    }
}
