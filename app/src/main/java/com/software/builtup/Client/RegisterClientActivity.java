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
import com.software.builtup.MainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Client;
import com.software.builtup.repository.ClientRepository;

public class RegisterClientActivity extends AppCompatActivity {

    EditText regClientUsername, regClientPassword, regClientFullname, regClientPhoneNumber, regClientAddress;
    Button regClientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        regClientUsername = findViewById(R.id.edit_text_username_register_c);
        regClientPassword = findViewById(R.id.edit_text_password_register_c);
        regClientFullname = findViewById(R.id.edit_text_fullname_register_c);
        regClientPhoneNumber = findViewById(R.id.edit_text_phone_number_register_c);
        regClientAddress = findViewById(R.id.edit_text_address_register_c);
        regClientBtn = findViewById(R.id.btn_client_registration);

        regClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = regClientUsername.getText().toString().trim();
                String password = regClientPassword.getText().toString().trim();
                String fullname = regClientFullname.getText().toString().trim();
                String phone = regClientPhoneNumber.getText().toString().trim();
                String address = regClientAddress.getText().toString().trim();

                if(username.isEmpty()){
                    Toast.makeText(RegisterClientActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(ValidateUsername(username) == false) {
                    Toast.makeText(RegisterClientActivity.this, "Email must in the right format", Toast.LENGTH_SHORT).show();
                }else if(password.length()< 9){
                    Toast.makeText(RegisterClientActivity.this, "Password must atleast be 9 characters", Toast.LENGTH_SHORT).show();
                }else if(fullname.isEmpty()){
                    Toast.makeText(RegisterClientActivity.this, "Please fill your name", Toast.LENGTH_SHORT).show();
                }else if(phone.isEmpty()){
                    Toast.makeText(RegisterClientActivity.this, "Please fill your phone number", Toast.LENGTH_SHORT).show();
                }else if(address.isEmpty()){
                    Toast.makeText(RegisterClientActivity.this, "Please fill your Address", Toast.LENGTH_SHORT).show();
                }else{

                    ClientRepository cRepository = new ClientRepository(RegisterClientActivity.this);
                    Client client = new Client();
                    if(cRepository.ValidClientUser(username) == true){
                        client.setClientID(cRepository.IDEncoder(username,password,phone));
                        client.setClientUsername(username);
                        client.setClientPassword(password);
                        client.setClientName(fullname);
                        client.setClientPhone(phone);
                        client.setClientAddress(address);
                        Bundle bundle = new Bundle();

                        Client registeredClient = cRepository.insertRegisterClient(client);
                        SharedPref sharedPref = new SharedPref(RegisterClientActivity.this);
                        sharedPref.enLoggedClient(client);
                        Toast.makeText(RegisterClientActivity.this, "Welcome, " + registeredClient.getClientName() + ".", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterClientActivity.this, ClientMainActivity.class);
                        bundle.putSerializable("MsClient", registeredClient);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finishAffinity();
                    }else{
                        Toast.makeText(RegisterClientActivity.this, "username already existed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    public boolean ValidateUsername(String username){
        if(username.contains("@") && username.endsWith(".com") && !username.contains(" "))
            return true;
        else return false;
    }
}
