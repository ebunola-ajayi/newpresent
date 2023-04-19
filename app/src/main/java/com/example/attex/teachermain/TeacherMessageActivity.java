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
import com.example.attex.models.TeacherMessageAdapter;
import com.example.attex.models.ModelChat;
import com.example.attex.models.ModelParent;
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

public class TeacherMessageActivity extends AppCompatActivity {

    ImageView imageView;
    TextView parentUsername;

    FirebaseUser fUser;
    DatabaseReference reference, ref;
    Intent intent;

    ImageButton sendBtn;
    EditText sendTxt;

    TeacherMessageAdapter messageAdapter;
    List<ModelChat> mchat;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Message Parent");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherMessageActivity.this, TeacherMainActivity.class));
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        imageView = findViewById(R.id.imageView);
        parentUsername = findViewById(R.id.parentUsername);

        sendBtn = findViewById(R.id.sendBTN);
        sendTxt = findViewById(R.id.send_text);
        imageView.setImageResource(R.drawable.profitem);

        intent = getIntent();
        String parentID = intent.getStringExtra("parentID");

        Intent i = getIntent();
        String parentEmail = i.getStringExtra("parentEmail");



        //String pID = intent.getStringExtra(TeacherMainActivity.)

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        String myEmail = fUser.getEmail();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = sendTxt.getText().toString();
                if(!msg.equals("")){
                    sendMessage(fUser.getEmail(), parentEmail, msg);
                } else {
                    Toast.makeText(TeacherMessageActivity.this, "Please Enter a Message", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Parents").child(parentID); //maybe change to pusername

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelParent parent = snapshot.getValue(ModelParent.class);
                parentUsername.setText(parent.getParentUsername());
                String parentID = parent.getParentID();
                System.out.println(parentID + " " + parentEmail);
               // readMessage(fUser.getUid(), parentID, parent.getParentID());
                readMessage(myEmail, parentEmail, parent.getParentID());
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

    private void readMessage(final String myEmail, final String parentEmail, final String other){
        mchat = new ArrayList<>();
        System.out.println(myEmail+ " " + parentEmail + " " + other);

        ref= FirebaseDatabase.getInstance().getReference("Chats");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mchat.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelChat chat = dataSnapshot.getValue(ModelChat.class);

                    assert chat != null;
                    String sender = chat.getSender();
                    System.out.println("sender " + sender);

                    String receiver = chat.getReceiver();
                    System.out.println("receiver " + receiver);

                   if(chat.getReceiver().equals(myEmail) && chat.getSender().equals(parentEmail) || chat.getReceiver().equals(parentEmail) && chat.getSender().equals(myEmail)) {
                        //if(chat.getSender().equalsIgnoreCase(myEmail) && chat.getReceiver().equalsIgnoreCase(parentEmail)){
                            mchat.add(chat);

                       // }

                   }

                    messageAdapter = new TeacherMessageAdapter(TeacherMessageActivity.this, mchat);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }



    //SEND A NEW MESSAGE THEN DO IF STATEMENT











}