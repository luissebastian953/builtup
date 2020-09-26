package com.software.builtup.Client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.MainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Client;
import com.software.builtup.repository.ClientRepository;

public class UpdateProfileClientActivity extends AppCompatActivity {

    TextView updateUsernameClient;
    EditText updateNameClient, updatePhoneClient, updateAddressClient;
    Button btnCancelUpdate, btnChangeUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_client);

        updateUsernameClient = findViewById(R.id.text_view_fullname_profile_update_c);

        updateNameClient = findViewById(R.id.edit_text_fullname_update_c);
        updatePhoneClient = findViewById(R.id.edit_text_phone_number_update_c);
        updateAddressClient = findViewById(R.id.edit_text_address_update_c);

        btnCancelUpdate = findViewById(R.id.btn_client_update_cancel);
        btnChangeUpdate = findViewById(R.id.btn_client_update_submit);

        final SharedPref sharedPref = new SharedPref(UpdateProfileClientActivity.this);
        final Client loggedClient = sharedPref.clientLogger();
        final Bundle bundle = new Bundle();


        updateUsernameClient.setText(loggedClient.getClientUsername());
        updateNameClient.setText(loggedClient.getClientName());
        updatePhoneClient.setText(loggedClient.getClientPhone());
        updateAddressClient.setText(loggedClient.getClientAddress());

        btnCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProfileClientActivity.this, ClientMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnChangeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UpdatedName = updateNameClient.getText().toString().trim();
                String UpdatedPhone = updatePhoneClient.getText().toString().trim();
                String UpdatedAddress = updateAddressClient.getText().toString().trim();

                loggedClient.setClientName(UpdatedName);
                loggedClient.setClientPhone(UpdatedPhone);
                loggedClient.setClientAddress(UpdatedAddress);

                ClientRepository clientRepository = new ClientRepository(UpdateProfileClientActivity.this);
                clientRepository.UpdateClient(loggedClient);
                Intent intent = new Intent(UpdateProfileClientActivity.this, ClientMainActivity.class);
                sharedPref.enLoggedClient(loggedClient);
                bundle.putSerializable("MsClient", loggedClient);
                intent.putExtras(bundle);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}
