package com.example.attex.teachermain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelTeacher;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class TeacherProfileFragment extends Fragment {
    EditText fullName, classGrade, schoolName, classIDTV, schoolIDTV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();


        if(currentUser==null){
            Intent intent=new Intent(getActivity(), InitialLoginActivity.class);
            startActivity(intent);
        }


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("TeacherDetails").child(currentUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelTeacher teacher = snapshot.getValue(ModelTeacher.class);

                fullName = view.findViewById(R.id.fullName);
                fullName.setText(teacher.getFirstName() + " " + teacher.getLastName());

                classGrade = view.findViewById(R.id.classGrade);
                classGrade.setText(teacher.getClassGrade());

                schoolName = view.findViewById(R.id.schoolName);

                classIDTV = view.findViewById(R.id.classIDTV);
                classIDTV.setText(teacher.getClassID());

                schoolIDTV = view.findViewById(R.id.schoolIDTV);
                schoolIDTV.setText(teacher.getSchoolID());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return view;
    }
}