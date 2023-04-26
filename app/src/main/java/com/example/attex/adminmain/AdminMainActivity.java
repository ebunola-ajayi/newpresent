package com.example.attex.adminmain;

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
import com.example.attex.adminacademics.AdminAcademicsFragment;
import com.example.attex.adminmain.AdminHomeFragment;
import com.example.attex.databinding.ActivityAdminMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminMainActivity extends AppCompatActivity {

    //ActivityMainBinding ;
    ActivityAdminMainBinding binding1;
    public static String ADMINID = "admin ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding1 = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding1.getRoot());

        replaceFragment(new AdminHomeFragment());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        ADMINID = currentUser.getUid();

        binding1.bottomNavAdmin.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeItem:
                    replaceFragment(new AdminHomeFragment());
                    break;
                case R.id.academicItem:
                    replaceFragment(new AdminAcademicsFragment());
                    break;
                case R.id.profileItem:
                    replaceFragment(new AdminProfileFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout1, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_main_menu, menu);
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