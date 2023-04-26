package com.example.attex.teacheracademics;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.models.ModelTeacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherGradeFragment extends Fragment {

    ImageView maths, science, english, irish, geography, history, viewExam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_grade, container, false);

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
                ModelTeacher teacher=snapshot.getValue(ModelTeacher.class);
                if(teacher!=null){
                    String classID = teacher.getClassID();
                    String schoolID = teacher.getSchoolID();
                    String classGrade = teacher.getClassGrade();


                    //onClickListeners are here as classID is needed as an intent value
                    maths = view.findViewById(R.id.maths);
                    maths.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String subject = "Maths";
                            Intent intent = new Intent(getActivity(), SubjectOptionActivity.class);
                            intent.putExtra("subjectName", subject);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("classID", classID);
                            startActivity(intent);
                        }
                    });



                    science = view.findViewById(R.id.science);
                    science.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String subject = "Science";
                            Intent intent = new Intent(getActivity(), SubjectOptionActivity.class);
                            intent.putExtra("subjectName", subject);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("classID", classID);
                            startActivity(intent);
                        }
                    });


                    english = view.findViewById(R.id.english);
                    english.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String subject = "English";
                            Intent intent = new Intent(getActivity(), SubjectOptionActivity.class);
                            intent.putExtra("subjectName", subject);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("classID", classID);
                            startActivity(intent);
                        }
                    });

                    irish = view.findViewById(R.id.irish);
                    irish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String subject = "Irish";
                            Intent intent = new Intent(getActivity(), SubjectOptionActivity.class);
                            intent.putExtra("subjectName", subject);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("classID", classID);
                            startActivity(intent);
                        }
                    });



                    geography = view.findViewById(R.id.geography);
                    geography.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String subject = "Geography";
                            Intent intent = new Intent(getActivity(), SubjectOptionActivity.class);
                            intent.putExtra("subjectName", subject);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("classID", classID);
                            startActivity(intent);
                        }
                    });

                    history = view.findViewById(R.id.history);
                    history.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String subject = "Geography";
                            Intent intent = new Intent(getActivity(), SubjectOptionActivity.class);
                            intent.putExtra("subjectName", subject);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("classID", classID);
                            startActivity(intent);
                        }
                    });

                    viewExam = view.findViewById(R.id.viewExam);
                    viewExam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), TeacherViewStandardSubjectActivity.class);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("classID", classID);
                            startActivity(intent);
                        }
                    });

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

























        return view;
    }
}