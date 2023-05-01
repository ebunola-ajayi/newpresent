package com.example.attex.parentmain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;


public class ParentProfileFragment extends Fragment {

    EditText firstNameET, lastNameET;
    TextView classGradeTV, classIDTV, schoolIDTV, studentIDTV, emailIDTV, childFirstNameTV, childLastNameTV;
    Button update;

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

        //add intents from mainfragment
        Intent i =getActivity().getIntent();
        String studentID = i.getStringExtra("studentID");
        String classID = i.getStringExtra("classID");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");

        firstNameET = view.findViewById(R.id.firstNameET);
        lastNameET = view.findViewById(R.id.lastNameET);
        classGradeTV = view.findViewById(R.id.classGradeTV);
        classIDTV = view.findViewById(R.id.classIDTV);
        schoolIDTV = view.findViewById(R.id.schoolIDTV);
        studentIDTV = view.findViewById(R.id.studentIDTV);
        emailIDTV = view.findViewById(R.id.emailIDTV);
        childFirstNameTV = view.findViewById(R.id.childFirstNameTV);
        childLastNameTV = view.findViewById(R.id.childLastNameTV);

        emailIDTV.setText(currentUser.getEmail());


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Parents").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelParent parent = snapshot.getValue(ModelParent.class);
                firstNameET.setText(parent.getFirstName());
                lastNameET.setText(parent.getLastName());
                classGradeTV.setText(parent.getClassGrade());
                classIDTV.setText(parent.getClassID());
                studentIDTV.setText(parent.getStudentID());
                childFirstNameTV.setText(parent.getChildFirstName());
                childLastNameTV.setText(parent.getChildLastName());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        update = view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String childFirstName = childFirstNameTV.getText().toString();
                String childLastName = childLastNameTV.getText().toString();
                String email = currentUser.getEmail();
                String firstName = firstNameET.getText().toString();
                String lastName = lastNameET.getText().toString();
                String parentID = currentUser.getUid();

                if(firstName.isEmpty()){
                    firstNameET.requestFocus();
                    Toast.makeText(getActivity(), "Please Enter First Name", Toast.LENGTH_SHORT).show();
                }

                else if(lastName.isEmpty()){
                    lastNameET.requestFocus();
                    Toast.makeText(getActivity(), "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                } else {

                    HashMap<String, Object> edittedHashmap = new HashMap<>();

                    edittedHashmap.put("childFirstName", childFirstName);
                    edittedHashmap.put("childLastName", childLastName);
                    edittedHashmap.put("classGrade", classGrade);
                    edittedHashmap.put("classID", classID);
                    edittedHashmap.put("email", email);
                    edittedHashmap.put("firstName", firstName);
                    edittedHashmap.put("lastName", lastName);
                    edittedHashmap.put("parentID", parentID);
                    edittedHashmap.put("schoolID", schoolID);
                    edittedHashmap.put("studentID", studentID);

                    reference.setValue(edittedHashmap);
                    Toast.makeText(getActivity(), "Changes Saved", Toast.LENGTH_SHORT).show();


                }


            }
        });


        return view;
    }

}