package com.example.attex.parentmain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelParent;
import com.example.attex.models.ModelStudent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ParentProfileFragment extends Fragment {

    ImageView imageView;
    EditText firstName, lastName;
    TextView childClassGrade, classIDTV, schoolName;
    String schoolID, studentID, classID, classGrade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent_profile, container, false);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(getActivity(), InitialLoginActivity.class);
            startActivity(intent);
        }

        imageView = view.findViewById(R.id.imageView);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        childClassGrade = view.findViewById(R.id.childClassGrade);
        classIDTV = view.findViewById(R.id.classIDTV);
        schoolName = view.findViewById(R.id.schoolName);





        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Parents").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelParent parent = snapshot.getValue(ModelParent.class);

                firstName.setText(parent.getpFirstName());
                lastName.setText(parent.getpSurName());
                childClassGrade.setText(parent.getClassGrade());
                classIDTV.setText(parent.getClassID());
                schoolID = parent.getSchoolID();
                classID = parent.getClassID();
                studentID = parent.getStudentID();
                classGrade = parent.getClassGrade();

                System.out.println(schoolID + studentID + classID + classGrade);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


       /* DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID).child(studentID);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelStudent student = snapshot.getValue(ModelStudent.class);
                System.out.println(student.getAddressLine1());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


                //smartdraw

        return view;
    }
}