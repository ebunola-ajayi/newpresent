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

import com.example.attex.InitialLoginActivity;
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
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);

        }

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        String classID = i.getStringExtra("classID");
        System.out.println(classID);


        //from editing
        Intent i2 = getIntent();
        String noteID = i2.getStringExtra("noteID");
        String noteTitle = i2.getStringExtra("noteTitle");
        String note = i2.getStringExtra("note");
        String noteDate = i2.getStringExtra("noteDate");
        System.out.println(noteDate);


        delete = findViewById(R.id.delete);
        title = findViewById(R.id.title);
        noteTitleET = findViewById(R.id.noteTitle);
        noteET = findViewById(R.id.note);

        if(noteID!=null && !noteID.isEmpty()){
            isEditting = true;
        }



        if(isEditting){
            title.setText("Edit Note");
            noteET.setText(note);
            noteTitleET.setText(noteTitle);
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

                    /*SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                    Date todayDate = new Date();
                    String date = currentDate.format(todayDate);*/

                    Timestamp timestamp = notes.getTimestamp();

                    HashMap<String, Object> edittedHashmap = new HashMap<>();

                    edittedHashmap.put("noteTitle", editNoteTitle);
                    edittedHashmap.put("note", editNoteET);
                    edittedHashmap.put("date", noteDate);
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

                        String theID = reference.push().getKey();
                        noteHashmap.put("noteID", theID);
                        reference.child(theID).setValue(noteHashmap);
                }


                }

            }
        });


    }
}