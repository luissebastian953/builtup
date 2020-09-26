package com.software.builtup.Client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.software.builtup.Client.ClientChatSystem.ClientChatFragment;
import com.software.builtup.Client.ClientList.ClientListFragment;
import com.software.builtup.Client.InboxClient.ClientInboxFragment;
import com.software.builtup.R;

public class ClientMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClientHomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = new ClientHomeFragment();
                    switch (menuItem.getItemId()){
                        case R.id.nav_home :
                            selectedFragment = new ClientHomeFragment();
                            break;
                        case R.id.nav_list :
                            selectedFragment = new ClientListFragment();
                            break;
                        case R.id.nav_chat :
                            selectedFragment = new ClientChatFragment();
                            break;
                        case R.id.nav_inbox :
                            selectedFragment = new ClientInboxFragment();
                            break;
                        case R.id.nav_profile :
                            selectedFragment = new ClientProfileFragment();
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
