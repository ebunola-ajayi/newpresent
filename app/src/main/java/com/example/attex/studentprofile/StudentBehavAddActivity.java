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
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class StudentBehavAddActivity extends AppCompatActivity {

    EditText dateET;
    EditText commentET;
    EditText feedbackType;
    Button saveBtn;
    ImageButton delete;

    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton positive;
    RadioButton negative;
    TextView textView;
    TextView behaviourTitle;


    boolean isEditting = false;

    //private DatabaseReference reference;

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
        //feedbackType = findViewById(R.id.feedbackType);

        radioGroup = findViewById(R.id.radioGroup);
        /*int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);*/
        textView = findViewById(R.id.text_view_selected);


        Intent i = getIntent();
        //i = getIntent();
        String studentID = i.getStringExtra("studentID");
        System.out.println(studentID);
        String firstName = i.getStringExtra("firstName");
        String schoolID = i.getStringExtra("schoolID");
        System.out.println(schoolID);
        String classGrade = i.getStringExtra("classGrade");
        System.out.println(classGrade);
        String lastName = i.getStringExtra("lastName");
        String teacherID = i.getStringExtra("classID");
        System.out.println(teacherID);


        //from editing
        Intent i2 = getIntent();
        String behaviourID = i2.getStringExtra("behaviourID");
        System.out.println(behaviourID);
        String date = i2.getStringExtra("date");
        System.out.println(date);
        String comment = i2.getStringExtra("comment");
        System.out.println(comment);
        String feedback = i2.getStringExtra("feedback");
        System.out.println(feedback);

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

        delete = findViewById(R.id.delete);

        dateET.setText(date);
        commentET.setText(comment);
        /*int radioID1 = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID1);*/


      //  radioButton.setText(feedback);

        behaviourTitle = findViewById(R.id.behaviourTitle);
        if(isEditting){
            behaviourTitle.setText("Edit Behaviour Entry");
            delete.setVisibility(View.VISIBLE);

        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(teacherID).child(studentID).child(behaviourID);
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
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("BehaviourRecord").child(schoolID).child(classGrade).child(teacherID).child(studentID);

                if(isEditting){
                    int radioID1 = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID1);
                    textView.setText("Selected: " + radioButton.getText());

                    String editComment = commentET.getText().toString();
                    String editFeedback = radioButton.getText().toString();
                    String editDate = date;

                    HashMap<String, Object> edittedHashmap = new HashMap<>();

                    edittedHashmap.put("comment", editComment);
                    edittedHashmap.put("feedback", editFeedback);
                    edittedHashmap.put("date", date);
                    edittedHashmap.put("studentID", studentID);
                    edittedHashmap.put("firstName", firstName);
                    edittedHashmap.put("lastName", lastName);
                    edittedHashmap.put("teacherID", teacherID);
                    edittedHashmap.put("behaviourID", behaviourID);

                    reference.child(behaviourID).setValue(edittedHashmap);
                    Toast.makeText(StudentBehavAddActivity.this, "Changes Made Successfully", Toast.LENGTH_SHORT).show();

                } else {

                    int radioID = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID);
                    textView.setText("Selected: " + radioButton.getText());

                    String feedback1 = radioButton.getText().toString();
                    System.out.println(feedback1);



                    String date = dateET.getText().toString();
                    String comment = commentET.getText().toString();
                    //  String feedback = feedbackType.getText().toString();

/*
                    reference = FirebaseDatabase.getInstance().getReference().child("BehaviourRecord").child(schoolID).child(classGrade).child(teacherID).child(studentID);
*/

                    String behaviourID = reference.push().getKey();

                    HashMap<String, Object> behaviorHashmap = new HashMap<>();
                    behaviorHashmap.put("date", date);
                    behaviorHashmap.put("comment", comment);
                    behaviorHashmap.put("feedback", feedback1);
                    behaviorHashmap.put("studentID", studentID);
                    behaviorHashmap.put("firstName", firstName);
                    behaviorHashmap.put("lastName", lastName);
                    behaviorHashmap.put("teacherID", teacherID);
                    behaviorHashmap.put("behaviourID", behaviourID);

                    // reference.push().setValue(behaviorHashmap);
                    reference.child(behaviourID).setValue(behaviorHashmap);
                }



            }
        });





        //for notification
        //get students parentemail (student.getParentEmail)
        //if currentUser.email is equal to parents email -> show notification


    }

   public void checkButton(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioID);
        Toast.makeText(this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}