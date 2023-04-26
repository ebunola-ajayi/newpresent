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
import com.example.attex.models.ModelNote;
import com.example.attex.models.ModelStudent;

import java.util.List;

public class TeacherNotesAdapter extends RecyclerView.Adapter<TeacherNotesAdapter.TeacherNotesViewHolder> {

    private final List<ModelNote> noteList;
    private final Context context;

    String schoolID, classGrade, classID;

    public TeacherNotesAdapter(List<ModelNote> noteList, Context context, String schoolID, String classGrade, String classID) {
        this.noteList = noteList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
        this.classID = classID;
    }

    @NonNull
    @Override
    public TeacherNotesAdapter.TeacherNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherNotesAdapter.TeacherNotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_notes_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherNotesAdapter.TeacherNotesViewHolder holder, int position) {
        ModelNote notes = noteList.get(position);

        holder.note.setText(notes.getNote());
        holder.noteTitle.setText(notes.getNoteTitle());
        holder.date.setText(notes.getDate());

        String noteDate = notes.getDate();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddNoteActivity.class);
                intent.putExtra("noteTitle", notes.getNoteTitle());
                intent.putExtra("note", notes.getNote());
                intent.putExtra("noteDate", noteDate);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("noteID", notes.getNoteID());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class TeacherNotesViewHolder extends RecyclerView.ViewHolder{
        TextView note, noteTitle, date;

        public TeacherNotesViewHolder(@NonNull View itemView) {
            super(itemView);

            note = itemView.findViewById(R.id.note);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            date = itemView.findViewById(R.id.date);
        }
    }
}
