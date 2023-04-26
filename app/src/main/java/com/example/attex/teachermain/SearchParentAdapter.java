package com.example.attex.teachermain;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelParent;
import com.example.attex.teacherchat.TeacherMessageActivity;

import java.util.List;

public class SearchParentAdapter extends RecyclerView.Adapter<SearchParentAdapter.SearchParentViewHolder> {

    private Context mContext;
    private List<ModelParent> mParent;


    public SearchParentAdapter(Context mContext, List <ModelParent> mParent){
        this.mContext = mContext;
        this.mParent = mParent;
    }


    @NonNull
    @Override
    public SearchParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.parents_layout, parent, false);
        return new SearchParentAdapter.SearchParentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchParentViewHolder holder, int position) {

        ModelParent parents = mParent.get(position);
        holder.username.setText("Parents of: " + parents.getChildFirstName() + " " + parents.getChildLastName());

        holder.profile_image.setImageResource(R.drawable.profitem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TeacherMessageActivity.class);
                //intent.putExtra("username", parents.getpUsername());
                //intent.putExtra("parentUsername", parents.getpUsername());
                intent.putExtra("parentID", parents.getParentID());
                //intent.putExtra("teacherID", teacherID);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mParent.size();
    }

    public class SearchParentViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public ImageView profile_image;

        public SearchParentViewHolder(View itemView){
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_icon);
        }



    }




}
