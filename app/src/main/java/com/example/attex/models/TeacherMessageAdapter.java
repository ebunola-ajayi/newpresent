package com.example.attex.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class TeacherMessageAdapter extends RecyclerView.Adapter<TeacherMessageAdapter.TeacherMessageViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<ModelChat> mChat;
    private String imageURL;

    FirebaseUser fuser;

    public TeacherMessageAdapter(Context mContext, List<ModelChat> mChat) {
        this.mContext = mContext;
        this.mChat = mChat;
       // this.imageURL = imageURL;
    }


    @NonNull
    @Override
    public TeacherMessageAdapter.TeacherMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
                return new TeacherMessageAdapter.TeacherMessageViewHolder(view);
            } else {
                View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
                return new TeacherMessageAdapter.TeacherMessageViewHolder(view);
            }
        }


    @Override
    public void onBindViewHolder(@NonNull TeacherMessageAdapter.TeacherMessageViewHolder holder, int position) {
        ModelChat chat = mChat.get(position);

        holder.show_message.setText(chat.getMessage());
        holder.profile_image.setImageResource(R.drawable.profitem);

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class TeacherMessageViewHolder extends RecyclerView.ViewHolder {

        public TextView show_message;
        public ImageView profile_image;

        public TeacherMessageViewHolder(@NonNull View itemView){
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
        }

       // @Override


    }

    public int getItemViewType(int position){
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        //if(mChat.get(position).getSender().equals(fuser.getUid())){
        if(mChat.get(position).getSender().equals(fuser.getEmail())){

            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }


}
