package com.example.attex.parentmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelBehaviour;

import java.util.List;

public class ChildBehaviorAdapter extends RecyclerView.Adapter<ChildBehaviorAdapter.ChildBehaviorViewHolder> {

    private final List<ModelBehaviour> behaviourList;
    private final Context context;

    public ChildBehaviorAdapter(List<ModelBehaviour> behaviourList, Context context) {
        this.behaviourList = behaviourList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildBehaviorAdapter.ChildBehaviorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildBehaviorAdapter.ChildBehaviorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.student_behaviour_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildBehaviorAdapter.ChildBehaviorViewHolder holder, int position) {
        ModelBehaviour behaviour = behaviourList.get(position);
        holder.dateTV.setText("  - " + behaviour.getDate());
        holder.feedbackTV.setText(behaviour.getFeedback());
        holder.commentTV.setText(behaviour.getComment());
    }

    @Override
    public int getItemCount() {
        return behaviourList.size();
    }

    public static class ChildBehaviorViewHolder extends RecyclerView.ViewHolder{

        TextView dateTV, commentTV, feedbackTV;

        public ChildBehaviorViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.dateTV);
            commentTV = itemView.findViewById(R.id.commentTV);
            feedbackTV = itemView.findViewById(R.id.feedbackTV);
        }
    }
}
