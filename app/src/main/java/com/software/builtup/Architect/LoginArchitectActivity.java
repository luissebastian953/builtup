package com.software.builtup.Architect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Client.LoginClientActivity;
import com.software.builtup.MainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.repository.ArchitectRepository;
import com.software.builtup.repository.ClientRepository;

public class LoginArchitectActivity extends AppCompatActivity {

    TextView changeUserC, registArchitect;
    EditText logUsername, logPassword;
    Button architectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_architect);

        logUsername = findViewById(R.id.edit_text_login_user_username_a);
        logPassword = findViewById(R.id.edit_text_login_user_password_a);
        architectBtn = findViewById(R.id.btn_architect_login);
        registArchitect = findViewById(R.id.text_view_link_register_architect);
        changeUserC = findViewById(R.id.user_change_client);

        architectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = logUsername.getText().toString().trim();
                String password = logPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginArchitectActivity.this, "Email and password must not be empty.", Toast.LENGTH_SHORT).show();
                } else if (ValidateUsername(username) == false) {
                    Toast.makeText(LoginArchitectActivity.this, "Invalid email format.", Toast.LENGTH_SHORT).show();
                } else if (ValidatePassword(password) == false) {
                    Toast.makeText(LoginArchitectActivity.this, "Password must be at least 9 characters long.", Toast.LENGTH_SHORT).show();
                } else {
                    ArchitectRepository aRepository = new ArchitectRepository(LoginArchitectActivity.this);
                    Bundle bundle = new Bundle();

                    Architect architect = aRepository.ValidArchitectUser(username, password);
                    if (architect != null) {

                        SharedPref sharedPref = new SharedPref(LoginArchitectActivity.this);
                        sharedPref.enLoggedArchitect(architect);

                        Intent intent = new Intent(LoginArchitectActivity.this, ArchitectMainActivity.class);
                        Toast.makeText(LoginArchitectActivity.this, "Welcome, " + architect.getArchitectName() + ".", Toast.LENGTH_SHORT).show();
                        bundle.putSerializable("MsArchitect", architect);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        Toast.makeText(LoginArchitectActivity.this, "There is no such user registered.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        changeUserC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginArchitectActivity.this, LoginClientActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        registArchitect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginArchitectActivity.this, RegisterArchitectActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean ValidateUsername(String username) {
        if (username.contains("@") && username.endsWith(".com") && !username.contains(" "))
            return true;
        else return false;
    }

    public boolean ValidatePassword(String password) {
        if (password.length() >= 9)
            return true;
        else return false;
    }
}