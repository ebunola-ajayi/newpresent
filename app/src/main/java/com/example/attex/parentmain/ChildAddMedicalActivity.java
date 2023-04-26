package com.example.attex.parentmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ChildAddMedicalActivity extends AppCompatActivity {

    String[] meds = {"Medical Condition", "Allergy", "Intolerance"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    EditText medTitleET, noteET;
    TextView title;
    Button save;
    ImageButton delete;

    boolean isEditting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_add_medical);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String studentID = i.getStringExtra("studentID");

        //from editting
        String medicalID = i.getStringExtra("medicalID");
        String medicalTitle = i.getStringExtra("medicalTitle");
        String medicalType = i.getStringExtra("medicalType");
        String note = i.getStringExtra("note");
        System.out.println(medicalID + medicalTitle + medicalType + note);

        //define variables
        title = findViewById(R.id.title);
        medTitleET = findViewById(R.id.medTitle);
        noteET = findViewById(R.id.noteET);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);

        //test if medicalID is empty (is it being edited?)
        if(medicalID!=null && !medicalID.isEmpty()){
            isEditting = true;
        }

        medTitleET.setText(medicalTitle);
        noteET.setText(note);
        if(isEditting){
            title.setText("Edit Medical Information");
            delete.setVisibility(View.VISIBLE);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MedicalInfo").child(schoolID).child(classGrade).child(classID).child(studentID).child(medicalID);
                reference.removeValue();
                medTitleET.setText("");
                noteET.setText("");
                Toast.makeText(ChildAddMedicalActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });



        //dropdown list
        autoCompleteTextView = findViewById(R.id.autoComplete);
        adapter = new ArrayAdapter<String>(this, R.layout.list_meds, meds);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String medicalType1 = adapterView.getItemAtPosition(i).toString();
                System.out.println(medicalType1);

                String finalMedicalType = medicalType1;
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MedicalInfo").child(schoolID).child(classGrade).child(classID).child(studentID);

                        if(isEditting){
                            String editMedicalTitle = medTitleET.getText().toString();
                            String editMedicalNote = noteET.getText().toString();

                            HashMap<String, Object> edittedHashmap = new HashMap<>();
                            edittedHashmap.put("note", editMedicalNote);
                            edittedHashmap.put("medicalTitle", editMedicalTitle);
                            edittedHashmap.put("medicalType", medicalType);
                            edittedHashmap.put("medicalID", medicalID);
                            reference.child(medicalID).setValue(edittedHashmap);
                            Toast.makeText(ChildAddMedicalActivity.this, "Changes Made Successfully", Toast.LENGTH_SHORT).show();



                        } else {
                            String note = noteET.getText().toString();
                            String medTitle = medTitleET.getText().toString();
                            String medicalID = reference.push().getKey();

                            HashMap<String, Object> medicalHashmap = new HashMap<>();
                            medicalHashmap.put("note", note);
                            medicalHashmap.put("medicalTitle", medTitle);
                            medicalHashmap.put("medicalType", finalMedicalType);
                            medicalHashmap.put("medicalID", medicalID);

                            reference.child(medicalID).setValue(medicalHashmap);
                            Toast.makeText(ChildAddMedicalActivity.this, "Medical Entry Entered Successfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });



    }
}