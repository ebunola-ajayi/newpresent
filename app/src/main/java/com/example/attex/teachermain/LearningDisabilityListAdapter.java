package com.example.attex.teachermain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelLearningDisability;

import java.util.ArrayList;
import java.util.List;

public class LearningDisabilityListAdapter extends RecyclerView.Adapter<LearningDisabilityListAdapter.LearningDisabilityListViewHolder> {

    private final List<ModelLearningDisability> learnList;
    private final Context context;

    public LearningDisabilityListAdapter(List<ModelLearningDisability> learnList, Context context) {
        this.learnList = learnList;
        this.context = context;
    }

    @NonNull
    @Override
    public LearningDisabilityListAdapter.LearningDisabilityListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LearningDisabilityListAdapter.LearningDisabilityListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_learn_db, null));
    }

    @Override
    public void onBindViewHolder(@NonNull LearningDisabilityListAdapter.LearningDisabilityListViewHolder holder, int position) {
        ModelLearningDisability learningDisability = learnList.get(position);

        holder.titleTV.setText(learningDisability.getTitle());
        holder.learnDescTV.setText(learningDisability.getShortDescription());

        ArrayList<String> symptoms = learningDisability.getSymptoms();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LearningDisabilityDetailsActivity.class);
                intent.putExtra("title", learningDisability.getTitle());
                intent.putExtra("shortDesc", learningDisability.getShortDescription());
                intent.putExtra("symptoms", symptoms);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return learnList.size();
    }

    public class LearningDisabilityListViewHolder extends RecyclerView.ViewHolder{

        TextView titleTV, learnDescTV;

        public LearningDisabilityListViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);
            learnDescTV = itemView.findViewById(R.id.shortDescTV);
        }
    }
}
