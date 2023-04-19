package com.example.attex.studentprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.R;
import com.example.attex.adminmain.StudentMedicalActivity;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.teachermain.TeacherMessageParentActivity;
import com.example.attex.models.ModelStudent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentProfileActivity extends AppCompatActivity {

    TextView studentName;
    TextView studentNumber;

    ImageView studentDetails;
    ImageView viewAcademic;
    ImageView viewBehav;
    ImageView medical;
    ImageView viewAttendance;




    FirebaseUser fUser;
    DatabaseReference reference;

    public static final String CHILD_ID = "childID";
    public static final String TEACHER_ID = "teacherID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();
        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        studentName = findViewById(R.id.studentName);
        studentNumber = findViewById(R.id.studentNumber);

        /*studentDetails = findViewById(R.id.studentDetails);
        studentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, StudentDetailsActivity.class);
                startActivity(intent);
            }
        });*/


       Intent i;
        i = getIntent();
        String studentID = i.getStringExtra("studentID");
        studentNumber.setText(studentID);
/*
        Intent i2;
        i2 = getIntent();
        String firstName = i.getStringExtra("firstName");

        Intent i3;
        i3 = getIntent();
        String lastName = i.getStringExtra("lastName");*/





    /*    viewBehav = findViewById(R.id.viewBehav);
        viewBehav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, StudentBehaviourActivity.class);
                intent.putExtra("child_id", studentID);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                startActivity(intent);
            }
        });*/


        fUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = fUser.getUid();

        //reference = FirebaseDatabase.getInstance().getReference("Students");
        Query query = FirebaseDatabase.getInstance().getReference("Students").child(userID).orderByChild("studentID").equalTo(studentID);
        query.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelStudent student  = snapshot.getValue(ModelStudent.class);

                    //studentName.setText(student.firstName);
                    studentName.setText(student.getFirstName());
                    //studentNumber.setText(student.studentID);
                    studentNumber.setText(student.getStudentID());

                    String stuID = student.getStudentID();
                    System.out.println(stuID);
                    String teachID = student.getClassID();
                    System.out.println(teachID);
                    String parentEmail = student.getParentEmail1();
                    System.out.println(parentEmail);

                    String schoolID = student.getSchoolID();
                    String classGrade = student.getClassGrade();

                    String studentFullName = student.getFirstName() + " " + student.getLastName();






                    studentDetails = findViewById(R.id.studentDetails);
                    studentDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(StudentProfileActivity.this, StudentDetailsActivity.class);
                            intent.putExtra("classID", teachID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("schoolID", schoolID); //EMPTY VALUE IN RTDB
                            intent.putExtra("studentID", stuID);
                            startActivity(intent);
                        }
                    });


                    viewAcademic = findViewById(R.id.viewAca);
                    viewAcademic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(StudentProfileActivity.this, StudentSubjectsActivity.class);
                            intent.putExtra("classID", teachID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("studentID", stuID);

                            startActivity(intent);
                        }
                    });

                    viewAttendance = findViewById(R.id.viewAttendance);
                    viewAttendance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(StudentProfileActivity.this, StudentAttendanceActivity.class);
                            intent.putExtra("classID", teachID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("studentID", stuID);
                            intent.putExtra("studentName", studentFullName);
                            startActivity(intent);
                        }
                    });

                    viewBehav = findViewById(R.id.viewBehav);
                    viewBehav.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                           /* Intent i2;
                            i2 = getIntent();
                            String firstName = i2.getStringExtra("firstName");

                            Intent i3;
                            i3 = getIntent();
                            String lastName = i3.getStringExtra("lastName");*/

                            Intent i;
                            i = getIntent();
                            String studentID = i.getStringExtra("studentID");

                           /* Intent i4;
                            i4 = getIntent();
                            String teachID = i4.getStringExtra("teacherID");*/


                            Intent intent = new Intent(StudentProfileActivity.this, StudentBehaviourActivity.class);
                            intent.putExtra("studentID", student.getStudentID());
                            intent.putExtra("firstName", student.getFirstName());
                            intent.putExtra("lastName", student.getLastName());
                            intent.putExtra("classGrade", student.getClassGrade());
                            intent.putExtra("schoolID", student.getSchoolID());
                            intent.putExtra("teacherID", teachID);
                            startActivity(intent);
                        }
                    });

                    medical = findViewById(R.id.medical);
                    medical.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(StudentProfileActivity.this, StudentMedicalActivity.class);
                            intent.putExtra("studentID", stuID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classID", teachID);
                            startActivity(intent);
                        }
                    });


                }
                //adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(StudentProfileActivity.this, "Student Not Found", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}