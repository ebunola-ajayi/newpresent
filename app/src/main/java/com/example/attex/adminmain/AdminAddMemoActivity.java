package com.example.attex.adminmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AdminAddMemoActivity extends AppCompatActivity {

    EditText memoTitleET, memoET;
    Button save;

    //if editting
    TextView title;
    ImageButton delete;
    boolean isEditting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_memo);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");

        memoTitleET = findViewById(R.id.memoTitleET);
        memoET = findViewById(R.id.memoET);

        //if is editting
        title = findViewById(R.id.title);
        delete = findViewById(R.id.delete);

        //if editting memo
        Intent i2 = getIntent();
        String memoTitle = i2.getStringExtra("memoTitle");
        String memo = i2.getStringExtra("memo");
        String memoID = i2.getStringExtra("memoID");
        String memoDate = i2.getStringExtra("memoDate");

        if(memoID!=null && !memoID.isEmpty()){
            isEditting = true;
        }

        if(isEditting){
            title.setText("Edit Memo");
            memoTitleET.setText(memoTitle);
            memoET.setText(memo);
            delete.setVisibility(View.VISIBLE);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Memos").child(schoolID).child(memoID);
                reference1.removeValue();
                memoTitleET.setText("");
                memoET.setText("");
                Toast.makeText(AdminAddMemoActivity.this, "Memo Deleted", Toast.LENGTH_SHORT).show();
            }
        });



        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Memos").child(schoolID);

                if(isEditting){
                    String editMemoTitle = memoTitleET.getText().toString();
                    String editMemo = memoET.getText().toString();

                    HashMap<String, Object> editHashmap = new HashMap<>();
                    editHashmap.put("memoTitle", editMemoTitle);
                    editHashmap.put("memo", editMemo);
                    editHashmap.put("date", memoDate);
                    editHashmap.put("memoID", memoID);
                    editHashmap.put("schoolID", schoolID);

                    reference.child(memoID).setValue(editHashmap);
                    Toast.makeText(AdminAddMemoActivity.this, "Editted Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                    Date todayDate = new Date();
                    String date = currentDate.format(todayDate);

                    String memoTitle = memoTitleET.getText().toString();
                    String memo = memoET.getText().toString();
                    String theID = reference.push().getKey();

                    HashMap<String, Object> memoHashMap = new HashMap<>();
                    memoHashMap.put("memoTitle", memoTitle);
                    memoHashMap.put("memo", memo);
                    memoHashMap.put("memoID", theID);
                    memoHashMap.put("schoolID", schoolID);
                    memoHashMap.put("date", date);

                    if(memoTitle.isEmpty() || memo.isEmpty()){
                        Toast.makeText(AdminAddMemoActivity.this, "Please Fill All Values", Toast.LENGTH_SHORT).show();
                    } else {
                        reference.child(theID).setValue(memoHashMap);
                        Toast.makeText(AdminAddMemoActivity.this, "Memo Created", Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });


    }

}