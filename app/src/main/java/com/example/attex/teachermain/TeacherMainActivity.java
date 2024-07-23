package com.example.attex.teachermain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.databinding.ActivityMainBinding;
import com.example.attex.teacheracademics.TeacherGradeFragment;
import com.example.attex.teacherchat.TeacherChatFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TeacherMainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //when app opens first
        replaceFragment(new TeacherHomeFragment());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeItem:
                    replaceFragment(new TeacherHomeFragment());
                    break;
                case R.id.chatItem:
                    replaceFragment(new TeacherChatFragment());
                    break;
                case R.id.gradeItem:
                    replaceFragment(new TeacherGradeFragment());
                    break;
                case R.id.profileItem:
                    replaceFragment(new TeacherProfileFragment());
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

        //login page
        if (id == R.id.logoutItem) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}