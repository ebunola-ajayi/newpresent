package com.example.attex.teachermain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attex.R;
import com.example.attex.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddStudentActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    //add class name
    EditText firstNameET;
    EditText lastNameET;
    EditText dobET;
    EditText studentIDET;
    EditText parent1NameET;
    //EditText parentUsernameET;
    EditText parentEmailET;
    EditText parent1NoET;
    EditText teachID;
    Button addNewStudButton;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String mUserID;
    private DatabaseReference reference;
    private DatabaseReference ref;

   // public static final String EXTRA_NAME = "tUsername";
    //Intent i = getIntent();
    //String username = i.getStringExtra(TeacherMainActivity.EXTRA_NAME3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        /*binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeItem:
                    replaceFragment(new TeacherHomeFragment());
                    break;
               *//* case R.id.settingsItem:
                    replaceFragment(new SettingsFragment());
                    break;
                case R.id.profileItem:
                    replaceFragment(new ProfileFragment());
                    break;*//*
            }
            return true;
        });*/



        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();


        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
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

        addNewStudButton = findViewById(R.id.submit);
        addNewStudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addAStudent();
                mAuth = FirebaseAuth.getInstance();
                mUser = mAuth.getCurrentUser();
                //mUserID = mUser.getUid();
                mUserID = mUser.getUid();
                //reference = FirebaseDatabase;.getInstance().getReference().child("Students").child(mUserID);
                reference = FirebaseDatabase.getInstance().getReference().child("Students").child(mUserID);
                //ref = FirebaseDatabase.getInstance().getReference().child("Students").child(teacherID);

                //rename database


                //initialise values
                firstNameET = findViewById(R.id.firstNameET);
                lastNameET = findViewById(R.id.lastNameET);
                dobET = findViewById(R.id.dobET);
                studentIDET = findViewById(R.id.studentIDET);
                parent1NameET = findViewById(R.id.parent1NameET);
                parentEmailET = findViewById(R.id.parentEmail);
                parent1NoET = findViewById(R.id.parent1NoET);
             //   teachID = findViewById(R.id.teachID); //should be an intent!!!
                // parent2NoET = findViewById(R.id.parent2NoET);

                //convert values to String
                String firstName = firstNameET.getText().toString();
                String lastName = lastNameET.getText().toString();
                String dateOfBirth =  dobET.getText().toString();
                String studentID = studentIDET.getText().toString(); //will be same id!!
                String parent1Name = parent1NameET.getText().toString();
                String parentEmail = parentEmailET.getText().toString();
                String parent1No = parent1NoET.getText().toString();

                //used for chatting and parent reg
               // String parentSpecialID = String.valueOf(firstName.charAt(0) + parent1Name.charAt(1) + lastName.charAt(3));

                String parentSpecialID = classID.toLowerCase() + "--" + studentID;

                //String teacherID = teachID.getText().toString();
                //String parent2No = parent2NoET.getText().toString();

                ref = FirebaseDatabase.getInstance().getReference().child("StudentDetails").child(schoolID).child(classGrade).child(classID);


                //String id = reference.push().getKey();

                //if any fields are empty - except parent 2 details (as they may not be available)
                if(firstName.isEmpty() || lastName.isEmpty() || dateOfBirth.isEmpty() || parent1Name.isEmpty() || parent1No.isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "Please enter value for all fields", Toast.LENGTH_SHORT).show();
                    return;
                } else {                                                                      //("Students")
                    //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Student Name").setValue(firstName + lastName);
                    //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Date of Birth").setValue(dateOfBirth);
                    //Student student = new Student(firstName, lastName, dateOfBirth, studentID);
                    //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Students").setValue(student);
                    // this one: FirebaseDatabase.getInstance().getReference().push().child("Students").setValue(student);
                    // FirebaseDatabase.getInstance().getReference().child("users").push().child("Students").setValue(student);
                    //String id = reference.push().getKey();



                    HashMap<String, Object> studentHashmap = new HashMap<>();
                    studentHashmap.put("firstName", firstName);
                    studentHashmap.put("lastName", lastName);
                    studentHashmap.put("dateOfBirth", dateOfBirth);
                    studentHashmap.put("studentID", studentID);
                    studentHashmap.put("parent1Name", parent1Name);
                    studentHashmap.put("parentEmail", parentEmail);
                    studentHashmap.put("classID", classID);
                    studentHashmap.put("classGrade", classGrade);
                    studentHashmap.put("schoolID", schoolID);
                    studentHashmap.put("parent1Number", parent1No);
                    studentHashmap.put("parentSpecialID", parentSpecialID);

                    //Student student = new Student(firstName, lastName, dateOfBirth, studentID);
                    reference.child(studentID).setValue(studentHashmap);

                    //FirebaseDatabase.getInstance().getReference().child("StudentDetails").child(teacherID);
                    ref.child(studentID).setValue(studentHashmap);





                    //FirebaseDatabase.getInstance().getReference().child("users").getCurrentUser().push().child("Students").setValue(student);
                    //FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    Toast.makeText(AddStudentActivity.this, "New Student Has Been Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //private void addAStudent() {


/*
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //mUserID = mUser.getUid();
        mUserID = mUser.getUid();
        //reference = FirebaseDatabase;.getInstance().getReference().child("Students").child(mUserID);
        reference = FirebaseDatabase.getInstance().getReference().child("Students").child(mUserID);
        //ref = FirebaseDatabase.getInstance().getReference().child("Students").child(teacherID);

        //rename database


        //initialise values
        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        dobET = findViewById(R.id.dobET);
        studentIDET = findViewById(R.id.studentIDET);
        parent1NameET = findViewById(R.id.parent1NameET);
        parentUsernameET = findViewById(R.id.parentusername);
        parent1NoET = findViewById(R.id.parent1NoET);
        teachID = findViewById(R.id.teachID);
        // parent2NoET = findViewById(R.id.parent2NoET);

        //convert values to String
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String dateOfBirth =  dobET.getText().toString();
        String studentID = studentIDET.getText().toString(); //will be same id!!
        String parent1Name = parent1NameET.getText().toString();
        String parentUsername = parentUsernameET.getText().toString();
        String parent1No = parent1NoET.getText().toString();
        String teacherID = teachID.getText().toString();
        //String parent2No = parent2NoET.getText().toString();

        ref = FirebaseDatabase.getInstance().getReference().child("StudentDetails").child(teacherID);


        //String id = reference.push().getKey();

        //if any fields are empty - except parent 2 details (as they may not be available)
        if(firstName.isEmpty() || lastName.isEmpty() || dateOfBirth.isEmpty() || parent1Name.isEmpty() || parent1No.isEmpty() || teacherID.isEmpty()) {
            Toast.makeText(this, "Please enter value for all fields", Toast.LENGTH_SHORT).show();
            return;
        } else {                                                                      //("Students")
            //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Student Name").setValue(firstName + lastName);
            //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Date of Birth").setValue(dateOfBirth);
            //Student student = new Student(firstName, lastName, dateOfBirth, studentID);
            //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Students").setValue(student);
            // this one: FirebaseDatabase.getInstance().getReference().push().child("Students").setValue(student);
            // FirebaseDatabase.getInstance().getReference().child("users").push().child("Students").setValue(student);
            //String id = reference.push().getKey();



            HashMap<String, Object> studentHashmap = new HashMap<>();
            studentHashmap.put("firstName", firstName);
            studentHashmap.put("lastName", lastName);
            studentHashmap.put("dateOfBirth", dateOfBirth);
            studentHashmap.put("studentID", studentID);
            studentHashmap.put("parent1Name", parent1Name);
            studentHashmap.put("parentUsername", parentUsername);
            studentHashmap.put("teacherID", teacherID);

            //Student student = new Student(firstName, lastName, dateOfBirth, studentID);
            reference.child(studentID).setValue(studentHashmap);
            ref.child(studentID).setValue(studentHashmap);





            //FirebaseDatabase.getInstance().getReference().child("users").getCurrentUser().push().child("Students").setValue(student);
            //FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            Toast.makeText(AddStudentActivity.this, "New Student Has Been Added", Toast.LENGTH_SHORT).show();
        }*/

        //use hashmap for creating students




   // }

   /* private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }*/
}