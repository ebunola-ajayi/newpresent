package com.example.attex.adminacademics;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.models.ModelAdmin;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminAcademicsFragment extends Fragment {
    ImageView createtest;
    ImageView viewTest;
    ImageView testResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_academics, container, false);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(getActivity(), InitialLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }



        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("Admin").child(currentUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelAdmin admin = snapshot.getValue(ModelAdmin.class);

                String schoolID = admin.getSchoolID();

                createtest = view.findViewById(R.id.createTest);
                createtest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), AdminTestDetailsActivity.class);
                        intent.putExtra("schoolID", schoolID);
                        startActivity(intent);
                    }
                });


                viewTest = view.findViewById(R.id.viewTest);
                viewTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), AdminViewTestOptionsActivity.class);
                        intent.putExtra("schoolID", schoolID);
                        startActivity(intent);
                    }
                });

                testResult = view.findViewById(R.id.testResult);
                testResult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), AdminViewResultOptionActivity.class);
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