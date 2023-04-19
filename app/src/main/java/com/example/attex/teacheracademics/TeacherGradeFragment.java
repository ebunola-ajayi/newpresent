package com.example.attex.teacheracademics;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    ImageView maths;
    ImageView science;
    ImageView english;
    ImageView irish;
    ImageView geography;
    ImageView history;
    ImageView viewExam;
    public static final String SUBJECT = "subject";

    public static final String TEACHER_ID = "teacherID";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_teacher_grade, container, false);
        View view = inflater.inflate(R.layout.fragment_teacher_grade, container, false);





        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();


        if(currentUser==null){
            Intent intent=new Intent(getActivity(), TeacherLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("TeacherDetails").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelTeacher teacher=snapshot.getValue(ModelTeacher.class);
                if(teacher!=null)/*&&ifstudent.className=user.className*/{
                    //mainName.setText("Welcome "+teacher.firstName + " " + teacher.lastName);

                    String teacher_ID = teacher.getTeacherID();
                    String schoolID = teacher.getSchoolID();
                    String classGrade = teacher.getClassGrade();


                    //i puth this here cus i need the string id as an intent
                    maths = view.findViewById(R.id.maths);
                    maths.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String subject = "Maths";

                            Intent intent = new Intent(getActivity(), SubjectOptionActivity.class);
                            intent.putExtra("subjectName", subject);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("teacherID", teacher_ID);
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
                            intent.putExtra("teacherID", teacher_ID);
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
                            intent.putExtra("teacherID", teacher_ID);
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
                            intent.putExtra("teacherID", teacher_ID);
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
                            intent.putExtra("teacherID", teacher_ID);
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
                            intent.putExtra("teacherID", teacher_ID);
                            startActivity(intent);
                        }
                    });

                    viewExam = view.findViewById(R.id.viewExam);
                    viewExam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), TeacherStandardOptionsActivity.class);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("teacherID", teacher_ID);
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