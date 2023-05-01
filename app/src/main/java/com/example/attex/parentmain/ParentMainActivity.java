package com.example.attex.parentmain;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.databinding.ActivityParentMainBinding;
import com.example.attex.parentacademics.ParentAcademicsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ParentMainActivity extends AppCompatActivity{

        ActivityParentMainBinding binding2;

     @Override
     protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_main);

        binding2 = ActivityParentMainBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());

        //when app opens first
        replaceFragment(new ParentMainFragment());

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
                Intent intent=new Intent(this, InitialLoginActivity.class);
                startActivity(intent);
        }


        binding2.bottomNavParent.setOnItemSelectedListener(item -> {
                switch (item.getItemId()){
                        case R.id.childItem:
                                replaceFragment(new ParentMainFragment());
                                break;
                        case R.id.academicsItem:
                                replaceFragment(new ParentAcademicsFragment());
                                break;
                        case R.id.profileItem:
                                replaceFragment(new ParentProfileFragment());
                                break;
                }
                return true;
        });
       }

        private void replaceFragment(Fragment fragment){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.commit();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.teacher_main_menu, menu);
                return true;
        }


        public boolean onOptionsItemSelected(MenuItem item) {
             int id = item.getItemId();
             if (id == R.id.logoutItem) {
                     FirebaseAuth.getInstance().signOut();
                     Intent intent = new Intent(this, InitialLoginActivity.class);
                     startActivity(intent);
                     finish();
                }
                return true;
        }


}



