package com.example.attex.adminmain;

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

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAdmin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class AdminProfileFragment extends Fragment {

    TextView schoolIDTV, schoolNameTV, adminEmailTV;
    EditText addressLine1ET, addressLine2ET, countyET;
    Button update;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(getActivity(), InitialLoginActivity.class);
            startActivity(intent);
        }

        schoolIDTV = view.findViewById(R.id.schoolIDTV);
        schoolNameTV = view.findViewById(R.id.schoolNameTV);
        adminEmailTV = view.findViewById(R.id.adminEmailTV);

        addressLine1ET = view.findViewById(R.id.addressLine1ET);
        addressLine2ET = view.findViewById(R.id.addressLine2ET);
        countyET = view.findViewById(R.id.countyET);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admin").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelAdmin admin = snapshot.getValue(ModelAdmin.class);
                schoolIDTV.setText(admin.getSchoolID());
                schoolNameTV.setText(admin.getSchoolName());
                adminEmailTV.setText(admin.getAdminEmail());

                addressLine1ET.setText(admin.getAddressLine1());
                addressLine2ET.setText(admin.getAddressLine2());
                countyET.setText(admin.getCounty());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update = view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String schoolID = schoolIDTV.getText().toString();
                String schoolName = schoolNameTV.getText().toString();
                String adminEmail = adminEmailTV.getText().toString();
                String addressLine1 = addressLine1ET.getText().toString();
                String addressLine2 = addressLine2ET.getText().toString();
                String county = countyET.getText().toString();

                HashMap<String, Object> edittedHashmap = new HashMap<>();
                edittedHashmap.put("schoolID", schoolID);
                edittedHashmap.put("schoolName", schoolName);
                edittedHashmap.put("adminEmail", adminEmail);
                edittedHashmap.put("addressLine1", addressLine1);
                edittedHashmap.put("addressLine2", addressLine2);
                edittedHashmap.put("county", county);

                reference.setValue(edittedHashmap);

            }
        });


        return view;
    }
}