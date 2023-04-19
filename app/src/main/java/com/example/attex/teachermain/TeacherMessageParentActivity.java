package com.example.attex.teachermain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.R;
import com.example.attex.models.ModelChat;
import com.example.attex.models.ModelParent;
import com.example.attex.models.TeacherMessageAdapter;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.teachermain.TeacherMainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherMessageParentActivity extends AppCompatActivity {

    ImageView imageView;
    TextView parentEmailET;

    FirebaseUser fUser;
    DatabaseReference reference;
    Intent intent;

    ImageButton sendBtn;
    EditText sendTxt;

    TeacherMessageAdapter messageAdapter;
    List<ModelChat> mchat;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_message_parent);

        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Message Parent");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherMessageParentActivity.this, TeacherMainActivity.class));
                finish();
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();
        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

      /*  messageAdapter = new TeacherMessageAdapter(TeacherMessageParentActivity.this, mchat);
        recyclerView.setAdapter(messageAdapter);*/


        Intent i2 = getIntent();
        String classID = i2.getStringExtra("teacherID");

        Intent i = getIntent();
        String parentEmail = i.getStringExtra("parentEmail");
        String myEmail = currentUser.getEmail();
        System.out.println(myEmail);

        imageView = findViewById(R.id.imageView);
        parentEmailET = findViewById(R.id.parentEmail);
        parentEmailET.setText(parentEmail);

        sendBtn = findViewById(R.id.sendBTN);
        sendTxt = findViewById(R.id.send_text);
        imageView.setImageResource(R.drawable.profitem);

        fUser = FirebaseAuth.getInstance().getCurrentUser();


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = sendTxt.getText().toString();

                if(!msg.equals("")){
                    sendMessage(myEmail, parentEmail, msg);
                } else {
                    Toast.makeText(TeacherMessageParentActivity.this, "Please Enter a Message", Toast.LENGTH_SHORT).show();
                }
            }
        });


        reference = FirebaseDatabase.getInstance().getReference("Parents"); //maybe change to pusername
        readMessage(classID, parentEmail);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ModelParent parent = snapshot.getValue(ModelParent.class);
                    //parentUsername.setText(parent.getParentUsername());
                    // readMessage(fUser.getUid(), parentID, parent.getParentID());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void sendMessage(String sender, String receiver, String message){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> messageHashMap = new HashMap<>();
        messageHashMap.put("sender", sender);
        messageHashMap.put("receiver", receiver);
        messageHashMap.put("message", message);

        ref.child("Chats").push().setValue(messageHashMap);
    }


    public void readMessage(final String classID, String parentEmail){
        mchat = new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mchat.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelChat chat = dataSnapshot.getValue(ModelChat.class);

                    if(chat.getReceiver().equals(classID) && chat.getSender().equals(parentEmail) || chat.getReceiver().equals(parentEmail) && chat.getSender().equals(classID)) {
                        mchat.add(chat);
                    }



                    messageAdapter = new TeacherMessageAdapter(TeacherMessageParentActivity.this, mchat);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}