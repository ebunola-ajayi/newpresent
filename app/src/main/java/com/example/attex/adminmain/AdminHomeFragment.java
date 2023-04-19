package com.example.attex.adminmain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.attex.adminacademics.AdminSelectClass2Activity;
import com.example.attex.models.ModelAdmin;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdminHomeFragment extends Fragment {

    TextView schoolIDTV;
    TextView schoolNameTV;
    TextView schoolEmailTV;

    ImageView viewGrades;
    ImageView viewClasses;
    ImageView viewTeachers;
    ImageView viewStudents;

    public static String ADMINID = "admin ID";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_admin_home, container, false);
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        ADMINID = currentUser.getUid();


        if(currentUser==null){
            Intent intent=new Intent(getActivity(), AdminLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }

        schoolIDTV = view.findViewById(R.id.schoolIDTV);
        schoolNameTV = view.findViewById(R.id.schoolNameTV);
        schoolEmailTV = view.findViewById(R.id.schoolEmailTV);




        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("Admin").child(currentUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelAdmin admin = snapshot.getValue(ModelAdmin.class);
                String schoolID = admin.getSchoolID();

                if(admin != null){
                    schoolIDTV.setText(admin.getSchoolID());
                    schoolNameTV.setText(admin.getSchoolName());
                    schoolEmailTV.setText(admin.getAdminEmail());
                }


                viewGrades = view.findViewById(R.id.viewGrades);
                viewGrades.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), AdminSelectClass2Activity.class);
                        intent.putExtra("schoolID", admin.getSchoolID());
                        startActivity(intent);
                    }
                });


                viewClasses = view.findViewById(R.id.viewClasses);
                viewClasses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), AdminSelectClass3Activity.class);
                        intent.putExtra("schoolID", admin.getSchoolID());
                        startActivity(intent);
                    }
                });

                viewTeachers = view.findViewById(R.id.viewTeachers);
                viewTeachers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), AdminSelectClass4Activity.class);
                        intent.putExtra("schoolID", schoolID);
                        startActivity(intent);
                    }
                });

                viewStudents = view.findViewById(R.id.viewStudents);
                viewStudents.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), AdminSelectClass5Activity.class);
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