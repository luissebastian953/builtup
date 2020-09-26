package com.software.builtup.Architect.InboxArchitect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.ArchitectMainActivity;
import com.software.builtup.Client.ClientMainActivity;
import com.software.builtup.Client.UpdateProfileClientActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.repository.ArchitectRepository;
import com.software.builtup.repository.ClientRepository;

public class UpdateProfileArchitectActivity extends AppCompatActivity {

    TextView updateUsernameArchitect;
    EditText updateNameArchitect, updatePhoneArchitect, updateAddressArchitect;
    Button btnCancelUpdate, btnChangeUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_architect);


        updateUsernameArchitect = findViewById(R.id.text_view_fullname_profile_update_a);

        updateNameArchitect = findViewById(R.id.edit_text_fullname_update_a);
        updatePhoneArchitect = findViewById(R.id.edit_text_phone_number_update_a);
        updateAddressArchitect = findViewById(R.id.edit_text_address_update_a);

        btnCancelUpdate = findViewById(R.id.btn_architect_update_cancel);
        btnChangeUpdate = findViewById(R.id.btn_architect_update_submit);

        final SharedPref sharedPref = new SharedPref(UpdateProfileArchitectActivity.this);
        final Architect loggedArchitect = sharedPref.architectLogger();
        final Bundle bundle = new Bundle();


        updateUsernameArchitect.setText(loggedArchitect.getArchitectUsername());
        updateNameArchitect.setText(loggedArchitect.getArchitectName());
        updatePhoneArchitect.setText(loggedArchitect.getArchitectPhone());
        updateAddressArchitect.setText(loggedArchitect.getArchitectAddress());

        btnCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProfileArchitectActivity.this, ArchitectMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnChangeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UpdatedName = updateNameArchitect.getText().toString().trim();
                String UpdatedPhone = updatePhoneArchitect.getText().toString().trim();
                String UpdatedAddress = updateAddressArchitect.getText().toString().trim();

                loggedArchitect.setArchitectName(UpdatedName);
                loggedArchitect.setArchitectPhone(UpdatedPhone);
                loggedArchitect.setArchitectAddress(UpdatedAddress);

                ArchitectRepository architectRepository = new ArchitectRepository(UpdateProfileArchitectActivity.this);
                architectRepository.UpdateArchitect(loggedArchitect);
                Intent intent = new Intent(UpdateProfileArchitectActivity.this, ArchitectMainActivity.class);
                sharedPref.enLoggedArchitect(loggedArchitect);
                bundle.putSerializable("MsArchitect", loggedArchitect);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}
