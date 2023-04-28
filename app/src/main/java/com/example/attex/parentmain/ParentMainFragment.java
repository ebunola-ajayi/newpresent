package com.example.attex.parentmain;



import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.attex.R;
import com.example.attex.adminmain.AdminStudentDetailsActivity;
import com.example.attex.models.ModelStudent;
import com.example.attex.studentprofile.StudentAttendanceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ParentMainFragment extends Fragment {

    TextView childName, childNumber;
    ImageView chat, details, behaviour, school, medical, viewAttendance;

    FirebaseAuth auth=  FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();

    //private List<ModelStudent> mStudents;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent_main, container, false);


        Intent i =getActivity().getIntent();
        String studentID = i.getStringExtra("studentID");
        String classID = i.getStringExtra("classID");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");

        childName = view.findViewById(R.id.childName);
        childNumber = view.findViewById(R.id.childNumber);




        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID).child(studentID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelStudent student = snapshot.getValue(ModelStudent.class);
                String teacherEmail = student.getTeacherEmail();
                String firstName = student.getFirstName();
                String lastName = student.getLastName();

                childName.setText(firstName + " " + lastName);
                childNumber.setText(student.getStudentID());


                chat=view.findViewById(R.id.chat);
                chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        Intent intent = new Intent(getActivity(), ParentMessageActivity.class);
                        intent.putExtra("classID", classID);
                        intent.putExtra("teacherEmail", teacherEmail);
                        startActivity(intent);
                    }
                });

                medical = view.findViewById(R.id.medical);
                medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ChildMedicalActivity.class);
                        intent.putExtra("schoolID", schoolID);
                        intent.putExtra("classID", classID);
                        intent.putExtra("classGrade", classGrade);
                        intent.putExtra("studentID", studentID);
                        startActivity(intent);
                    }

                });

                viewAttendance = view.findViewById(R.id.viewAttendance);
                viewAttendance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), StudentAttendanceActivity.class);
                        intent.putExtra("schoolID", schoolID);
                        intent.putExtra("classID", classID);
                        intent.putExtra("classGrade", classGrade);
                        intent.putExtra("studentID", studentID);
                        intent.putExtra("firstName", firstName);
                        intent.putExtra("lastName", lastName);
                        startActivity(intent);
                    }
                });

                details = view.findViewById(R.id.details);
                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), AdminStudentDetailsActivity.class);
                        intent.putExtra("schoolID", schoolID);
                        intent.putExtra("classID", classID);
                        intent.putExtra("classGrade", classGrade);
                        intent.putExtra("studentID", studentID);
                        intent.putExtra("teacherEmail", teacherEmail);
                        startActivity(intent);
                    }
                });

                behaviour = view.findViewById(R.id.behaviour);
                behaviour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ParentViewBehaviourActivity.class);
                        intent.putExtra("schoolID", schoolID);
                        intent.putExtra("classID", classID);
                        intent.putExtra("classGrade", classGrade);
                        intent.putExtra("studentID", studentID);
                        startActivity(intent);
                    }
                });

                school = view.findViewById(R.id.schoolMemos);
                school.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), SchoolMemosActivity.class);
                        intent.putExtra("schoolID", schoolID);
                        startActivity(intent);
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }



}