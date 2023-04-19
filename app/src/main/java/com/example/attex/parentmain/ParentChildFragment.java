/*
package com.example.attex;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ParentChildFragment extends Fragment {

    ImageView imageView;
    TextView childName;
    TextView childNumber;
    ImageView viewAttendance;
    ImageView academicPro;
    ImageView behaviour;
    ImageView details;
    Button chat;

    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser currentUser=auth.getCurrentUser();
    FirebaseDatabase database;

    private List<ModelStudent> mStudents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_parent_child, container, false);

        View view = inflater.inflate(R.layout.fragment_parent_child, container, false);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(getActivity(),TeacherLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }


       // Intent i = getIntent();
       // String child_ID = i.getStringExtra(EnterChildIDActivity.CHILD_ID);

        childName = view.findViewById(R.id.childName);
        //childName.setText(this.getArguments().getString("childID"));
        //String child_ID = this.getArguments().getString("childID");
        Bundle bundle = this.getArguments();
        String child_ID = bundle.getString("childID");


        childNumber = view.findViewById(R.id.childNumber);
        //String teacher_ID = this.getArguments().getString("teacherID");
        Bundle b2 = this.getArguments();
        String teacher_ID = b2.getString("teacherID");
        //Intent intent2 = getIntent();
       // String teacher_ID = intent2.getStringExtra(EnterChildIDActivity.TEACHER_ID);


        chat = view.findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), ParentMessageActivity.class);
                startActivity(intent);
            }
        });


        Query query = FirebaseDatabase.getInstance().getReference("StudentDetails").child(teacher_ID)
                .orderByChild("studentID")
                .equalTo(child_ID);

        query.addListenerForSingleValueEvent(valueEventListener);

        return view;

    }

   ValueEventListener valueEventListener = new ValueEventListener() {
       @Override
       public void onDataChange(@NonNull DataSnapshot snapshot) {
           if(snapshot.exists()){
               for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   ModelStudent student= dataSnapshot.getValue(ModelStudent.class);

                   childName.setText(student.firstName);
                   childNumber.setText(student.studentID);
               }
           } else {
               Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
           }
       }

       @Override
       public void onCancelled(@NonNull DatabaseError error) {

       }
   };

}*/
