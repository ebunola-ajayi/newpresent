package com.example.attex.teachermain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.R;
import com.example.attex.models.ModelNote;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddNoteActivity extends AppCompatActivity {

    EditText noteTitleET;
    EditText noteET;
    Button save;
    ImageButton delete;
    TextView title;
    boolean isEditting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i2 = getIntent();
        String schoolID = i2.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i3 = getIntent();
        String classID = i3.getStringExtra("classID");
        System.out.println(classID);


        //from editing
        Intent i4 = getIntent();
        String noteID = i3.getStringExtra("noteID");
        System.out.println("NoteID" + noteID);

        Intent i5= getIntent();
        String noteTitle = i5.getStringExtra("noteTitle");
        System.out.println(noteTitle);

        //Intent i = getIntent();
        String note = i.getStringExtra("note");
        System.out.println(note);



        title = findViewById(R.id.title);
        noteTitleET = findViewById(R.id.noteTitle);
        noteET = findViewById(R.id.note);

        if(noteID!=null && !noteID.isEmpty()){
            isEditting = true;
        }

        delete = findViewById(R.id.delete);


       // title.setText(noteTitle);
        noteET.setText(note);
        noteTitleET.setText(noteTitle);
        if(isEditting){
            title.setText("Edit Note");
            delete.setVisibility(View.VISIBLE);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Notes").child(schoolID).child(classGrade).child(classID).child(noteID);
                reference1.removeValue();
                noteET.setText("");
                noteTitleET.setText("");
                Toast.makeText(AddNoteActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        });




//lead this into a new method
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notes").child(schoolID).child(classGrade).child(classID);

                if(isEditting){
                    String editNoteTitle = noteTitleET.getText().toString();
                    String editNoteET = noteET.getText().toString();

                    ModelNote notes = new ModelNote();

                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                    Date todayDate = new Date();
                    String date = currentDate.format(todayDate);

                    Timestamp timestamp = notes.getTimestamp();

                    HashMap<String, Object> edittedHashmap = new HashMap<>();

                    edittedHashmap.put("noteTitle", editNoteTitle);
                    edittedHashmap.put("note", editNoteET);
                    edittedHashmap.put("date", date);
                    edittedHashmap.put("noteID", noteID);
                    edittedHashmap.put("timestamp", timestamp);
                    reference.child(noteID).setValue(edittedHashmap);
                    Toast.makeText(AddNoteActivity.this, "Changes Made Successfully", Toast.LENGTH_SHORT).show();
                }


                else {

                    ModelNote notes = new ModelNote();

                    String noteTitle = noteTitleET.getText().toString();
                    String note = noteET.getText().toString();

                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                    Date todayDate = new Date();
                    String date = currentDate.format(todayDate);
                    Timestamp timestamp = notes.getTimestamp();

                    if (note.isEmpty()){
                        Toast.makeText(AddNoteActivity.this, "Please Enter A Note", Toast.LENGTH_SHORT).show();
                    } else if (noteTitle.isEmpty()){
                        Toast.makeText(AddNoteActivity.this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddNoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();

                        HashMap<String, Object> noteHashmap = new HashMap<>();

                        noteHashmap.put("noteTitle", noteTitle);
                        noteHashmap.put("note", note);
                        noteHashmap.put("date", date);
                        noteHashmap.put("timestamp", timestamp);

                        //note.setTimestamp(Timestamp.now())
                        String theID = reference.push().getKey();
                        noteHashmap.put("noteID", theID);
                        reference.child(theID).setValue(noteHashmap);
                        //reference.push().setValue(noteHashmap);
                       // reference.setValue(noteHashmap);
                }

                /*ModelNote notes = new ModelNote();

                String noteTitle = noteTitleET.getText().toString();
                String note = noteET.getText().toString();

                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                Date todayDate = new Date();
                String date = currentDate.format(todayDate);
                Timestamp timestamp = notes.getTimestamp();

                if (note.isEmpty()){
                    Toast.makeText(AddNoteActivity.this, "Please Enter A Note", Toast.LENGTH_SHORT).show();
                } else if (noteTitle.isEmpty()){
                    Toast.makeText(AddNoteActivity.this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddNoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();

                    HashMap<String, Object> noteHashmap = new HashMap<>();

                    noteHashmap.put("noteTitle", noteTitle);
                    noteHashmap.put("note", note);
                    noteHashmap.put("date", date);
                    noteHashmap.put("timestamp", timestamp);

                    //note.setTimestamp(Timestamp.now())

                    reference.push().setValue(noteHashmap);*/

                }

            }
        });


    }
}