package com.software.builtup.Architect;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.software.builtup.Architect.ArchitectChatSystem.ArchitectChatFragment;
import com.software.builtup.Architect.InboxArchitect.ArchitectInboxFragment;
import com.software.builtup.R;

public class ArchitectMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ArchitectHomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = new ArchitectHomeFragment();
                    switch (menuItem.getItemId()){
                        case R.id.nav_home :
                            selectedFragment = new ArchitectHomeFragment();
                            break;
                        case R.id.nav_chat :
                            selectedFragment = new ArchitectChatFragment();
                            break;
                        case R.id.nav_inbox :
                            selectedFragment = new ArchitectInboxFragment();
                            break;
                        case R.id.nav_profile :
                            selectedFragment = new ArchitectProfileFragment();
                            break;
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    return true;
                }
            };
}
