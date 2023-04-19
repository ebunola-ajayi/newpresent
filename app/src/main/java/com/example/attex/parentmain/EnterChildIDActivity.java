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
   // EditText tID;

    public static final String CHILD_ID = "childID";
    public static final String TEACHER_ID = "teacherID";

    EditText name;
    //String cid = childIDET.getText().toString();
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

     /*   Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i2 = getIntent();
        String classGrade = i2.getStringExtra("classGrade");
        System.out.println(classGrade);*/

       /* Intent i3 = getIntent();
        String classID = i3.getStringExtra("classID");
        System.out.println(classID);

*/


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

      //  tID = findViewById(R.id.teachID);
      //  String teacherID = tID.getText().toString();

       // name = findViewById(R.id.childName);
        String childName;

        /*Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i2 = getIntent();
        String classGrade = i2.getStringExtra("classGrade");
        System.out.println(classGrade);*/

        Intent i3 = getIntent();
        String classID = i3.getStringExtra("classID");
        System.out.println(classID);

        //********************
        //Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("studentID").equalTo(childID);

/* IMPORTANT
        Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("studentID").equalTo(childID);
        query.addListenerForSingleValueEvent(valueEventListener);*/



        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");

        Intent i2 = getIntent();
        String classGrade = i2.getStringExtra("classGrade");

        Query query = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID)
                .orderByChild("studentID")
                .equalTo(childID);

        query.addListenerForSingleValueEvent(valueEventListener);


      /*  Query query  = FirebaseDatabase.getInstance().getReference("StudentDetails").child(teacherID).child(childID).orderByChild("studentID").equalTo(childID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelStudent student = snapshot.getValue(ModelStudent.class);
                if (student != null) {
                    name.setText("Name of Child: " + student.getFirstName());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/






    }

// private List<ModelStudent> mStudents
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mStudents.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelStudent student  = snapshot.getValue(ModelStudent.class);
                    mStudents.add(student);
                    //name.setText(student.firstName);

                    String child_id = childIDET.getText().toString();
                    //String teacher_id = tID.getText().toString();

                    String studentID = student.getStudentID();
                    System.out.println("HEYYY" + studentID);
                    String schoolID2 = student.getSchoolID();
                    System.out.println("HEYYY" + schoolID2);
                    String classID = student.getClassID();
                    System.out.println("HEYYY" + classID);
                    String classGrade2 = student.getClassGrade();
                    System.out.println("HEYYY" + classGrade2);

                    Intent intent = new Intent(EnterChildIDActivity.this, ParentMainActivity.class);
                    intent.putExtra("studentID", studentID);
                    intent.putExtra("schoolID2", schoolID2);
                    intent.putExtra("classID", classID);
                    intent.putExtra("classGrade2", classGrade2);

                    startActivity(intent);


                }
                //adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(EnterChildIDActivity.this, "Child ID Does Not Exist", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    //IMPORTANT
   /* ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            childIDET = findViewById(R.id.childID);
            String cid = childIDET.getText().toString();
            mStudents.clear();
            if (snapshot.exists()) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Student student = dataSnapshot.getValue(Student.class);

                    mStudents.add(student);

                    //if(mStudents.contains(cid)){
                    if(student.getStudentNo().equals(cid)){
                        Intent intent = new Intent(EnterChildIDActivity.this, ParentMain.class);
                        startActivity(intent);
                    }


                }
                } else {
                Toast.makeText(EnterChildIDActivity.this, "Child ID Doesn't Exist", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };*/

}