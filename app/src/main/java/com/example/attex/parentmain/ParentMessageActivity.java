package com.example.attex.parentmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.teacherchat.TeacherMessageAdapter;
import com.example.attex.models.ModelChat;
import com.example.attex.models.ModelTeacher;
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

public class ParentMessageActivity extends AppCompatActivity {

    TextView teacherUsername;
    ImageView sendBTN, imageView;
    EditText send_text;
    DatabaseReference ref;

    RecyclerView recyclerView;
    TeacherMessageAdapter adapter;
    List<ModelChat> mchat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_message);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Message Parent");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentMessageActivity.this, ParentMainActivity.class));
                finish();
            }
        });


        String myEmail =  auth.getCurrentUser().getEmail();


        Intent i2 = getIntent();
        String teacherID = i2.getStringExtra("classID");
        String teacherEmail = i2.getStringExtra("teacherEmail");



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        imageView = findViewById(R.id.imageView);
        teacherUsername = findViewById(R.id.teacherUsername);
        teacherUsername.setText(teacherEmail);
        imageView.setImageResource(R.drawable.profitem);
        send_text = findViewById(R.id.send_text);


        sendBTN = findViewById(R.id.sendBTN);
        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = send_text.getText().toString();
                if(!msg.equals("")){
                    sendMessage(myEmail, teacherEmail, msg);
                    send_text.setText("");
                } else {
                    Toast.makeText(ParentMessageActivity.this, "Please Enter a Message", Toast.LENGTH_SHORT).show();
                }
            }
        });



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TeacherDetails"); //maybe change to pusername
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelTeacher teacher = snapshot.getValue(ModelTeacher.class);
                readMessage(myEmail, teacherEmail);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> messageHashMap = new HashMap<>();
        messageHashMap.put("sender", sender);
        messageHashMap.put("receiver", receiver);
        messageHashMap.put("message", message);

        ref.child("Chats").push().setValue(messageHashMap);
    }

    private void readMessage(final String myEmail, final String teacherEmail) {
        mchat = new ArrayList<>();
        System.out.println(myEmail+ " " + teacherEmail);

        ref= FirebaseDatabase.getInstance().getReference("Chats");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mchat.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelChat chat = dataSnapshot.getValue(ModelChat.class);

                    assert chat != null;
                    String sender = chat.getSender();
                    System.out.println("sender " + sender + myEmail);

                    String receiver = chat.getReceiver();
                    System.out.println("receiver " + receiver +teacherEmail);

                    if(chat.getReceiver().equals(myEmail) && chat.getSender().equals(teacherEmail) || chat.getReceiver().equals(teacherEmail) && chat.getSender().equals(myEmail)) {
                        mchat.add(chat);
                    }

                    adapter = new TeacherMessageAdapter(ParentMessageActivity.this, mchat);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}