package com.example.attex.teachermain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.attex.R;
import com.example.attex.adminmain.AdminMainActivity;
import com.example.attex.models.ModelTeacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherHomeFragment extends Fragment {

    ImageView viewStudents;
    ImageView addStudents;
    ImageView attendance;
    ImageView school;
   // ImageView chat;
    ImageView specialNeeds;
    ImageView attendanceRecord;
    ImageView note;
    TextView mainName;

    public static final String TEACHER_ID = "teacherID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_teacher_home, container, false);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();


        if(currentUser==null){
            Intent intent=new Intent(getActivity(), TeacherLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }


        String hello = AdminMainActivity.ADMINID;
        System.out.println(hello);







       /* attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TakeAttendanceActivity.class);
                startActivity(intent);
            }
        });*/



        mainName = view.findViewById(R.id.mainName);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("TeacherDetails").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelTeacher teacher=snapshot.getValue(ModelTeacher.class);
                if(teacher!=null)/*&&ifstudent.className=user.className*/{
                   // mainName.setText("Welcome "+teacher.firstName + " " + teacher.lastName);
                    mainName.setText("Welcome "+teacher.getFirstName() + " " + teacher.getLastName());
                    String teacherID = teacher.getTeacherID();
                    String schoolID = teacher.getSchoolID();
                    String classGrade = teacher.getClassGrade();

                    //i puth this here cus i need the string id as an intent
                    attendance = view.findViewById(R.id.attendance);
                    attendance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), TakeAttendance2Activity.class);
                            intent.putExtra("classID", teacherID);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });

                    viewStudents = view.findViewById(R.id.viewStudents);
                    viewStudents.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ViewStudentActivity.class);
                            intent.putExtra("classID", teacherID);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });

                    attendanceRecord = view.findViewById(R.id.attendanceRecord);
                    attendanceRecord.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), AttendanceHistoryActivity.class);
                            intent.putExtra("classID", teacherID);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });

                    addStudents = view.findViewById(R.id.add);
                    addStudents.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), AddStudentActivity.class);
                            intent.putExtra("classID", teacherID);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });

                    note = view.findViewById(R.id.note);
                    note.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), TeacherNotesActivity.class);
                            intent.putExtra("classID", teacherID);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });

                   /* test = view.findViewById(R.id.test);
                    test.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //StandardTest
                            Intent intent = new Intent(getActivity(), TestActivity.class);
                            intent.putExtra("classID", teacherID);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });*/

                    specialNeeds = view.findViewById(R.id.specialNeeds);
                    specialNeeds.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), LearningDisabilityListActivity.class);
                            intent.putExtra("classID", teacherID);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });

                    school = view.findViewById(R.id.school);
                    school.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });


                    /*chat = view.findViewById(R.id.chat);
                    chat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), TeacherChatOptionsActivity.class);
                            intent.putExtra("classID", teacherID);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });*/



                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        return view;
    }
}