package com.example.attex.teacherchat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.models.ModelParent;
import com.example.attex.R;
import com.example.attex.teachermain.TeacherMessageActivity;

import java.util.List;

public class ListParentsAdapter extends RecyclerView.Adapter<ListParentsAdapter.ListParentsViewHolder> {

    private Context mContext;
    private List<ModelParent> mParent;

    public ListParentsAdapter(Context mContext, List<ModelParent> mParent) {
        this.mContext = mContext;
        this.mParent = mParent;
    }

    @NonNull
    @Override
    public ListParentsAdapter.ListParentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListParentsAdapter.ListParentsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_list_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ListParentsAdapter.ListParentsViewHolder holder, int position) {
        ModelParent parent = mParent.get(position);

        holder.parentOf.setText("Parent Of: " + parent.getChildFirstName() + " " + parent.getChildLastName());
//        holder.studentID.setText(parent.getStudentID());
       // holder.parentNumber.setText(parent.getParentNumber());
        holder.parentEmail.setText(parent.getParentEmail());
        String parentID = parent.getParentID();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TeacherMessageActivity.class);
                intent.putExtra("parentEmail", parent.getParentEmail());
                intent.putExtra("parentID", parentID);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mParent.size();
    }

    public class ListParentsViewHolder extends RecyclerView.ViewHolder {

        TextView parentOf;
        TextView studentID;
        //TextView parentNumber;
        TextView parentEmail;


        public ListParentsViewHolder(@NonNull View itemView) {
            super(itemView);

            parentOf = itemView.findViewById(R.id.parentOf);
            studentID = itemView.findViewById(R.id.studentID);
            //parentNumber = itemView.findViewById(R.id.parentNumber);
            parentEmail = itemView.findViewById(R.id.parentEmail);
        }
    }
}
