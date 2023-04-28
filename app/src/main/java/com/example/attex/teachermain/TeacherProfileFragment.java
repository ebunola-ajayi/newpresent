package com.example.attex.teachermain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;


public class TeacherProfileFragment extends Fragment {
    EditText firstNameET, lastNameET, teacherNameET, dobET, numberET, addressLine1ET, addressLine2ET;
    Button update;
    TextView schoolIDTV, classGradeTV, classIDTV, emailTV;


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

        firstNameET = view.findViewById(R.id.firstNameET);
        lastNameET = view.findViewById(R.id.lastNameET);
        teacherNameET = view.findViewById(R.id.teacherNameET);
        dobET = view.findViewById(R.id.dobET);
        numberET = view.findViewById(R.id.numberET);
        addressLine1ET = view.findViewById(R.id.addressLine1ET);
        addressLine2ET = view.findViewById(R.id.addressLine2ET);
        emailTV = view.findViewById(R.id.emailTV);
        schoolIDTV = view.findViewById(R.id.schoolIDTV);
        classGradeTV = view.findViewById(R.id.classGradeTV);
        classIDTV = view.findViewById(R.id.classIDTV);






        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("TeacherDetails").child(currentUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelTeacher teacher = snapshot.getValue(ModelTeacher.class);

                firstNameET.setText(teacher.getFirstName());
                lastNameET.setText(teacher.getLastName());
                teacherNameET.setText(teacher.getTeacherName());
                dobET.setText(teacher.getDateOfBirth());
                numberET.setText(teacher.getNumber());
                addressLine1ET.setText(teacher.getAddressLine1());
                addressLine2ET.setText(teacher.getAddressLine2());
                emailTV.setText(teacher.getEmail());
                schoolIDTV.setText(teacher.getSchoolID());
                classGradeTV.setText(teacher.getClassGrade());
                classIDTV.setText(teacher.getClassID());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update = view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameET.getText().toString();
                String lastName = lastNameET.getText().toString();
                String teacherName = teacherNameET.getText().toString();
                String dateOfBirth = dobET.getText().toString();
                String number = numberET.getText().toString();
                String addressLine1 = addressLine1ET.getText().toString();
                String addressLine2 = addressLine2ET.getText().toString();
                String schoolID = schoolIDTV.getText().toString();
                String classGrade = classGradeTV.getText().toString();
                String classID = classIDTV.getText().toString();
                String email = emailTV.getText().toString();

                if(firstName.isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter A First Name", Toast.LENGTH_SHORT).show();
                    firstNameET.requestFocus();
                }else if(lastName.isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter A Last Name", Toast.LENGTH_SHORT).show();
                    lastNameET.requestFocus();
                }else if(teacherName.isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter A Teacher Name (Ms. / Mr.)", Toast.LENGTH_SHORT).show();
                    teacherNameET.requestFocus();
                }else if (number.isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter A Contact Number", Toast.LENGTH_SHORT).show();
                    numberET.requestFocus();
                }else {

                    HashMap<String, Object> edittedHashmap = new HashMap<>();
                    edittedHashmap.put("firstName", firstName);
                    edittedHashmap.put("lastName", lastName);
                    edittedHashmap.put("teacherName", teacherName);
                    edittedHashmap.put("dateOfBirth", dateOfBirth);
                    edittedHashmap.put("number", number);
                    edittedHashmap.put("addressLine1", addressLine1);
                    edittedHashmap.put("addressLine2", addressLine2);
                    edittedHashmap.put("schoolID", schoolID);
                    edittedHashmap.put("classGrade", classGrade);
                    edittedHashmap.put("classID", classID);
                    edittedHashmap.put("email", email);

                    reference.setValue(edittedHashmap);
                    Toast.makeText(getActivity(), "Changes Saved", Toast.LENGTH_SHORT).show();
                }

            }
        });





        return view;
    }
}