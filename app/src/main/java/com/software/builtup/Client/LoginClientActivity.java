package com.software.builtup.Client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.ArchitectMainActivity;
import com.software.builtup.Architect.LoginArchitectActivity;
import com.software.builtup.MainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.repository.ClientRepository;

public class LoginClientActivity extends AppCompatActivity {

    TextView changeUserA, registClient;
    EditText logUsername, logPassword;
    Button  logClientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);

        logUsername = findViewById(R.id.edit_text_login_user_username);
        logPassword = findViewById(R.id.edit_text_login_user_Password);
        logClientBtn = findViewById(R.id.btn_client_login);
        registClient = findViewById(R.id.text_view_link_register_client);

        changeUserA = findViewById(R.id.user_change_architect);

        SharedPref sharedPref = new SharedPref(LoginClientActivity.this);
        Client loggedClient = sharedPref.clientLogger();
        Architect loggedArchitect = sharedPref.architectLogger();
        if(!loggedClient.getClientUsername().equals("") && !loggedClient.getClientPassword().equals("")){
            Intent intent = new Intent(LoginClientActivity.this, ClientMainActivity.class);
            startActivity(intent);
            finish();
        }else if(!loggedArchitect.getArchitectUsername().equals("") && !loggedArchitect.getArchitectPassword().equals("")){
            Intent intent = new Intent(LoginClientActivity.this, ArchitectMainActivity.class);
            startActivity(intent);
            finish();
        }

        logClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = logUsername.getText().toString().trim();
                String password = logPassword.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginClientActivity.this, "Email and password must not be empty.", Toast.LENGTH_SHORT).show();
                }else if(ValidateUsername(username) == false) {
                    Toast.makeText(LoginClientActivity.this, "Invalid email format.", Toast.LENGTH_SHORT).show();
                }else if(ValidatePassword(password) == false) {
                    Toast.makeText(LoginClientActivity.this, "Password must be at least 9 characters long.", Toast.LENGTH_SHORT).show();
                }else{
                    ClientRepository cRepository = new ClientRepository(LoginClientActivity.this);
                    Bundle bundle = new Bundle();

                    Client client = cRepository.ValidClientUser(username, password);
                    if(client != null){

                        SharedPref sharedPref = new SharedPref(LoginClientActivity.this);
                        sharedPref.enLoggedClient(client);

                        Intent intent = new Intent(LoginClientActivity.this, ClientMainActivity.class);
                        Toast.makeText(LoginClientActivity.this, "Welcome, " + client.getClientName() + ".", Toast.LENGTH_SHORT).show();
                        bundle.putSerializable("MsClient", client);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finishAffinity();
                    }else{
                        Toast.makeText(LoginClientActivity.this, "There is no such user registered.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginClientActivity.this, RegisterClientActivity.class);
                startActivity(intent);
            }
        });

        changeUserA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginClientActivity.this, LoginArchitectActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean ValidateUsername(String username){
        if(username.contains("@") && username.endsWith(".com") && !username.contains(" "))
            return true;
        else return false;
    }

    public boolean ValidatePassword(String password){
        if(password.length()>=9)
            return true;
        else return false;
    }

}
