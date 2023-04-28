package com.example.attex.adminmain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelMemo;

import java.util.List;

public class AdminMemoAdapter extends RecyclerView.Adapter<AdminMemoAdapter.AdminMemoViewHolder> {

    private final List<ModelMemo> memoList;
    private final Context context;
    String schoolID;

    public AdminMemoAdapter(List<ModelMemo> memoList, Context context, String schoolID) {
        this.memoList = memoList;
        this.context = context;
        this.schoolID = schoolID;
    }

    @NonNull
    @Override
    public AdminMemoAdapter.AdminMemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminMemoAdapter.AdminMemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_memos, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminMemoAdapter.AdminMemoViewHolder holder, int position) {
        ModelMemo memo = memoList.get(position);

        holder.memoTitleTV.setText(memo.getMemoTitle() + "  -  ");
        holder.memoTV.setText(memo.getMemo());
        holder.memoDateTV.setText(memo.getDate());

        String memoID = memo.getMemoID();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminAddMemoActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("memoTitle", memo.getMemoTitle());
                intent.putExtra("memo", memo.getMemo());
                intent.putExtra("memoID", memoID);
                intent.putExtra("memoDate", memo.getDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public class AdminMemoViewHolder extends RecyclerView.ViewHolder{
        TextView memoTitleTV, memoTV, memoDateTV;
        public AdminMemoViewHolder(@NonNull View itemView) {
            super(itemView);

            memoTitleTV = itemView.findViewById(R.id.memoTitleTV);
            memoTV = itemView.findViewById(R.id.memoTV);
            memoDateTV = itemView.findViewById(R.id.memoDateTV);
        }
    }
}
