package com.example.attex.teacheracademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.teachermain.SubjectSelectStudentActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SubjectOptionActivity extends AppCompatActivity{

   /* ImageView add;
    SubjectTopicAdapter adapter;
    List<ModelSubjectTopics> mData;*/

    Button addAcademic;
    Button viewAcademic;
    EditText topicName;
    TextView subName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_option);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();


        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        Intent i = getIntent();
        String subject = i.getStringExtra("subjectName");
        System.out.println(subject);

        Intent i2 = getIntent();
        String classID = i2.getStringExtra("teacherID");
        System.out.println(classID);

        Intent i3 = getIntent();
        String schoolID = i3.getStringExtra("schoolID");

        Intent i4 = getIntent();
        String classGrade = i4.getStringExtra("classGrade");


/*

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("TeacherDetails").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelTeacher teacher=snapshot.getValue(ModelTeacher.class);
                if(teacher!=null)*//*&&ifstudent.className=user.className*//*{
                    //mainName.setText("Welcome "+teacher.firstName + " " + teacher.lastName);

                    String teacherID = teacher.getTeacherID();
                    System.out.println(teacherID);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/



















        subName = findViewById(R.id.subName);
        subName.setText(subject);






        topicName = findViewById(R.id.newTopic);



        viewAcademic = findViewById(R.id.viewAcademic);
        viewAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectOptionActivity.this, SubjectViewAcademicActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                startActivity(intent);
            }
        });




        addAcademic = findViewById(R.id.addAcademic);
        addAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = topicName.getText().toString();

                Intent intent = new Intent(SubjectOptionActivity.this, SubjectSelectStudentActivity.class);
                intent.putExtra("topic", topic);
                intent.putExtra("classID", classID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("subject", subject);
                intent.putExtra("classGrade", classGrade);
                startActivity(intent);
            }
        });

        /*FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();
        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubjectTopicAdapter(this, mData);
        recyclerView.setAdapter(adapter);

       // mData.add("Test", "Test", "Test");



        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(this, AddMathsTopic.class);
                *//*final DialogPlus dialogPlus = DialogPlus.newDialog(SubjectMathsActivity.this).setContentHolder(new ViewHolder(R.layout.popup_add_topic))
                        .setExpanded(true, 1200).create();

                dialogPlus.show();*//*
                //Toast.makeText(SubjectMathsActivity.this, "Test Working", Toast.LENGTH_SHORT).show();
                insert_Dialog dialog = new insert_Dialog();
                dialog.show(getSupportFragmentManager(), "Insert Item");
            }
        });*/




    }

   /* @Override
    public void applyTexts(String lineOne, String lineTwo, String lineThree) {
        mData.add(new ModelSubjectTopics(lineOne, lineTwo, lineThree));
        int position = 0;
        adapter.add(0, (ModelSubjectTopics) mData);
    }*/
}