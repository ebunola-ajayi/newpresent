package com.example.attex.parentmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelMemo;

import java.util.List;

public class SchoolMemoAdapter extends RecyclerView.Adapter<SchoolMemoAdapter.SchoolMemoViewHolder> {

    private final List<ModelMemo> memoList;
    private final Context context;

    public SchoolMemoAdapter(List<ModelMemo> memoList, Context context) {
        this.memoList = memoList;
        this.context = context;
    }

    @NonNull
    @Override
    public SchoolMemoAdapter.SchoolMemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SchoolMemoAdapter.SchoolMemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_memos, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolMemoAdapter.SchoolMemoViewHolder holder, int position) {
        ModelMemo memo = memoList.get(position);

        holder.memoTitleTV.setText(memo.getMemoTitle());
        holder.memoTV.setText(memo.getMemo());
        holder.memoDateTV.setText("  - " + memo.getDate());
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public class SchoolMemoViewHolder extends RecyclerView.ViewHolder{
        TextView memoTitleTV, memoTV, memoDateTV;
        public SchoolMemoViewHolder(@NonNull View itemView) {
            super(itemView);

            memoTitleTV = itemView.findViewById(R.id.memoTitleTV);
            memoTV = itemView.findViewById(R.id.memoTV);
            memoDateTV = itemView.findViewById(R.id.memoDateTV);
        }
    }
}
