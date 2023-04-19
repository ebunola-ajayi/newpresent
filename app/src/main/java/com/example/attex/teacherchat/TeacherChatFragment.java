package com.example.attex.teacherchat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.attex.R;
import com.example.attex.teachermain.SearchParentAdapter;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.models.ModelParent;
import com.example.attex.models.ModelTeacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class TeacherChatFragment extends Fragment {

    private RecyclerView recyclerView;

    private SearchParentAdapter parentAdapter;
    private List<ModelParent> mParents;
    EditText search_users;

    ImageView chatParents;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_chat, container, false);

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
                ModelTeacher teacher = snapshot.getValue(ModelTeacher.class);

                String classID = teacher.getTeacherID();
                String schoolID = teacher.getSchoolID();
                String classGrade = teacher.getClassGrade();

                chatParents = view.findViewById(R.id.chatParents);
                chatParents.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ListParentsActivity.class);
                        intent.putExtra("schoolID", schoolID);
                        intent.putExtra("classGrade", classGrade);
                        intent.putExtra("teacherID", classID);
                        startActivity(intent);

                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });










        /*
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mParents = new ArrayList<>();
        readParents();

        //search for parent
        search_users = view.findViewById(R.id.search_users);
        search_users.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchParents(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });*/

        // Inflate the layout for this fragment
        return view;
    }

    /*private void searchParents(String s) {
        FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();

        Query query = FirebaseDatabase.getInstance().getReference("Parents").orderByChild("parentUsername")
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mParents.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelParent parent = dataSnapshot.getValue(ModelParent.class);

                    assert parent !=null;
                    assert fuser != null;

                    //*************************** might be important - commenting out for now so app can run
                    //if(!parent.getpID().equals(fuser.getUid())){
                        mParents.add(parent);
                   // }
                }
                parentAdapter = new SearchParentAdapter(getContext(), mParents);
                recyclerView.setAdapter(parentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }*/

 /*   private void readParents(){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Parents");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!search_users.getText().toString().equals("")){
                    mParents.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ModelParent parent = dataSnapshot.getValue(ModelParent.class);

                        assert parent != null;
                        assert firebaseUser != null;

                        if(!parent.getParentID().equals(firebaseUser.getUid())){
                            mParents.add(parent);
                        }
                    }
                    parentAdapter = new SearchParentAdapter(getContext(), mParents);
                    recyclerView.setAdapter(parentAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }*/


}