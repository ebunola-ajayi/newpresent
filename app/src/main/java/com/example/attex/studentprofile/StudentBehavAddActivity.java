package com.example.attex.studentprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class StudentBehavAddActivity extends AppCompatActivity {

    EditText commentET;
    Button saveBtn;
    ImageButton delete;

    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton positive, negative;
    TextView behaviourTitle, dateET;

    boolean isEditting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_behav_add);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }



        dateET = findViewById(R.id.date);
        commentET = findViewById(R.id.commentET);

        radioGroup = findViewById(R.id.radioGroup);

        Intent i = getIntent();
        String studentID = i.getStringExtra("studentID");
        String firstName = i.getStringExtra("firstName");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String lastName = i.getStringExtra("lastName");
        String classID = i.getStringExtra("classID");


        //from editing
        Intent i2 = getIntent();
        String behaviourID = i2.getStringExtra("behaviourID");
        String date = i2.getStringExtra("date");
        String comment = i2.getStringExtra("comment");
        String feedback = i2.getStringExtra("feedback");

        if(behaviourID!=null && !behaviourID.isEmpty()){
            isEditting = true;
            System.out.println("Yippee");
            if(feedback.equalsIgnoreCase("Positive")){
                positive = findViewById(R.id.positive);
                positive.setChecked(true);
            } else if(feedback.equalsIgnoreCase("Negative")){
                negative = findViewById(R.id.negative);
                negative.setChecked(true);
            }
        }


        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        String todaysDate = currentDate.format(todayDate);

        dateET.setText(todaysDate);

        delete = findViewById(R.id.delete);
        behaviourTitle = findViewById(R.id.behaviourTitle);
        if(isEditting){
            behaviourTitle.setText("Edit Behaviour Entry");
            delete.setVisibility(View.VISIBLE);
            dateET.setText(date);
            commentET.setText(comment);

        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(classID).child(studentID).child(behaviourID);
                reference1.removeValue();
                commentET.setText("");
                dateET.setText("");
                Toast.makeText(StudentBehavAddActivity.this, "Behaviour entry Deleted", Toast.LENGTH_SHORT).show();
            }
        });




        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("BehaviourRecord").child(schoolID).child(classGrade).child(classID).child(studentID);

                if(isEditting){
                    int radioID1 = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID1);

                    String editComment = commentET.getText().toString();
                    String editFeedback = radioButton.getText().toString();

                    if(editComment.isEmpty()){
                        Toast.makeText(StudentBehavAddActivity.this, "Please Enter A Comment", Toast.LENGTH_SHORT).show();
                        commentET.requestFocus();
                    } else {

                        HashMap<String, Object> edittedHashmap = new HashMap<>();
                        edittedHashmap.put("comment", editComment);
                        edittedHashmap.put("feedback", editFeedback);
                        edittedHashmap.put("date", date);
                        edittedHashmap.put("studentID", studentID);
                        edittedHashmap.put("firstName", firstName);
                        edittedHashmap.put("lastName", lastName);
                        edittedHashmap.put("classID", classID);
                        edittedHashmap.put("behaviourID", behaviourID);

                        reference.child(behaviourID).setValue(edittedHashmap);
                        Toast.makeText(StudentBehavAddActivity.this, "Changes Made Successfully", Toast.LENGTH_SHORT).show();

                    }

                } else {

                    int radioID = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID);

                    String feedback1 = radioButton.getText().toString();
                    String comment = commentET.getText().toString();
                    String behaviourID = reference.push().getKey();
                    String date2 = dateET.getText().toString();

                    if(comment.isEmpty()){
                        Toast.makeText(StudentBehavAddActivity.this, "Please Enter A Comment", Toast.LENGTH_SHORT).show();
                        commentET.requestFocus();
                    } else {

                        HashMap<String, Object> behaviorHashmap = new HashMap<>();
                        behaviorHashmap.put("date", date2);
                        behaviorHashmap.put("comment", comment);
                        behaviorHashmap.put("feedback", feedback1);
                        behaviorHashmap.put("studentID", studentID);
                        behaviorHashmap.put("firstName", firstName);
                        behaviorHashmap.put("lastName", lastName);
                        behaviorHashmap.put("classID", classID);
                        behaviorHashmap.put("behaviourID", behaviourID);

                        // reference.push().setValue(behaviorHashmap);
                        reference.child(behaviourID).setValue(behaviorHashmap);
                        Toast.makeText(StudentBehavAddActivity.this, "Behaviour Entry Added", Toast.LENGTH_SHORT).show();
                    }



                }



            }
        });

    }

   public void checkButton(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioID);
        Toast.makeText(this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}