package com.example.attex.parentmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attex.models.ModelStudent;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EnterChildIDActivity extends AppCompatActivity {

    EditText childIDET;
    Button enterBtn;
    private List<ModelStudent> mStudents;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_child_idactivity);

        mStudents = new ArrayList<>();


        if (currentUser == null) {
            Intent intent = new Intent(this, ParentLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        enterBtn = findViewById(R.id.enter);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childIDSearch();
            }
        });


    }

    private void childIDSearch() {
        childIDET = findViewById(R.id.childID);
        String childID = childIDET.getText().toString();


        Intent i3 = getIntent();
        String classID = i3.getStringExtra("classID");
        System.out.println(classID);

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");

        Intent i2 = getIntent();
        String classGrade = i2.getStringExtra("classGrade");

        Query query = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID)
                .orderByChild("studentID")
                .equalTo(childID);

        query.addListenerForSingleValueEvent(valueEventListener);

    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mStudents.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelStudent student  = snapshot.getValue(ModelStudent.class);
                    mStudents.add(student);

                    String studentID = student.getStudentID();
                    String schoolID2 = student.getSchoolID();
                    String classID = student.getClassID();
                    String classGrade2 = student.getClassGrade();

                    Intent intent = new Intent(EnterChildIDActivity.this, ParentMainActivity.class);
                    intent.putExtra("studentID", studentID);
                    intent.putExtra("schoolID", schoolID2);
                    intent.putExtra("classID", classID);
                    intent.putExtra("classGrade", classGrade2);

                    startActivity(intent);


                }

            } else {
                Toast.makeText(EnterChildIDActivity.this, "Child ID Does Not Exist", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


}