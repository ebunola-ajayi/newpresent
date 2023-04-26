package com.example.attex.parentmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelChat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ParentMessageAdapter extends RecyclerView.Adapter<ParentMessageAdapter.ParentMessageViewHolder>{

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<ModelChat> mChat;

    FirebaseUser fuser;

    public ParentMessageAdapter(Context mContext, List<ModelChat> mChat) {
        this.mContext = mContext;
        this.mChat = mChat;
    }

    @NonNull
    @Override
    public ParentMessageAdapter.ParentMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new ParentMessageAdapter.ParentMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new ParentMessageAdapter.ParentMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ParentMessageAdapter.ParentMessageViewHolder holder, int position) {
        ModelChat chat = mChat.get(position);
        holder.show_message.setText(chat.getMessage());
        holder.profile_image.setImageResource(R.drawable.profitem);

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ParentMessageViewHolder extends RecyclerView.ViewHolder {
        public TextView show_message;
        public ImageView profile_image;

        public ParentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
        }

        public int getItemViewType(int position){
            fuser = FirebaseAuth.getInstance().getCurrentUser();
            if(mChat.get(position).getSender().equals(fuser.getUid())){
                return MSG_TYPE_RIGHT;
            } else {
                return MSG_TYPE_LEFT;
            }
        }
    }
}
