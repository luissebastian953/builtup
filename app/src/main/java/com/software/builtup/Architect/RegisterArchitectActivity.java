package com.software.builtup.Architect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Client.RegisterClientActivity;
import com.software.builtup.MainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.repository.ArchitectRepository;
import com.software.builtup.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

public class RegisterArchitectActivity extends AppCompatActivity {

    EditText regArchitectUsername, regArchitectPassword, regArchitectFullname, regArchitectPhoneNumber, regArchitectAddress, regOrganization;
    Button regArchitectBtn;
    Spinner reqFocusField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_architect);

        List<String> focusfield = new ArrayList<>();
        focusfield.add(0,"Choose Focus Field");
        focusfield.add(1,"Renovation");
        focusfield.add(2,"Build House");

        regArchitectUsername = findViewById(R.id.edit_text_username_register_a);
        regArchitectPassword = findViewById(R.id.edit_text_password_register_a);
        regArchitectFullname = findViewById(R.id.edit_text_fullname_register_a);
        regArchitectPhoneNumber = findViewById(R.id.edit_text_phone_number_register_a);
        regOrganization = findViewById(R.id.edit_text_organization_register_a);
        regArchitectAddress = findViewById(R.id.edit_text_address_register_a);
        regArchitectBtn = findViewById(R.id.btn_architect_registration);
        reqFocusField = findViewById(R.id.edit_text_focus_field_register_a);

        ArrayAdapter<String> focusFieldAdapter = new ArrayAdapter<String>(RegisterArchitectActivity.this,
                android.R.layout.simple_list_item_1, focusfield);
        //getResources().getStringArray(R.array.FocusField)
        focusFieldAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        reqFocusField.setAdapter(focusFieldAdapter);

//        reqFocusField.setOnItemClickListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(!parent.getItemAtPosition(position).equals("Choose Focus Field")){
//                    String item = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(RegisterArchitectActivity.this, "You chose "+ item, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                //
//            }
//        });

        regArchitectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = regArchitectUsername.getText().toString().trim();
                String password = regArchitectPassword.getText().toString().trim();
                String fullname = regArchitectFullname.getText().toString().trim();
                String organization = regOrganization.getText().toString().trim();
                String phone = regArchitectPhoneNumber.getText().toString().trim();
                String address = regArchitectAddress.getText().toString().trim();
                String focusField = reqFocusField.getSelectedItem().toString().trim();

                if(username.isEmpty()){
                    Toast.makeText(RegisterArchitectActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(ValidateUsername(username) == false) {
                    Toast.makeText(RegisterArchitectActivity.this, "Email must in the right format", Toast.LENGTH_SHORT).show();
                }else if(password.length()< 9){
                    Toast.makeText(RegisterArchitectActivity.this, "Password must atleast be 9 characters", Toast.LENGTH_SHORT).show();
                }else if(fullname.isEmpty()){
                    Toast.makeText(RegisterArchitectActivity.this, "Please fill your name", Toast.LENGTH_SHORT).show();
                }else if(organization.isEmpty()){
                    Toast.makeText(RegisterArchitectActivity.this, "Please fill your organization", Toast.LENGTH_SHORT).show();
                }else if(phone.length() < 5){
                    Toast.makeText(RegisterArchitectActivity.this, "Phone Number must atleast 5 digits", Toast.LENGTH_SHORT).show();
                }else if(address.isEmpty()){
                    Toast.makeText(RegisterArchitectActivity.this, "Please fill your Address", Toast.LENGTH_SHORT).show();
                }else if(focusField.isEmpty() || focusField == "" || focusField.equals("Choose Focus Field") || focusField == "Choose Focus Field"){
                    Toast.makeText(RegisterArchitectActivity.this, "Please fill your focus field type", Toast.LENGTH_SHORT).show();
                }else{

                    ArchitectRepository aRepository = new ArchitectRepository(RegisterArchitectActivity.this);
                    Architect architect = new Architect();
                    if(aRepository.ValidArchitectUser(username) == true){
                        architect.setArchitectID(aRepository.IDEncoder(username, password, phone, organization));
                        architect.setArchitectUsername(username);
                        architect.setArchitectPassword(password);
                        architect.setArchitectName(fullname);
                        architect.setArchitectOrganization(organization);
                        architect.setArchitectFieldFocus(focusField);
                        architect.setArchitectPhone(phone);
                        architect.setArchitectAddress(address);
                        Bundle bundle = new Bundle();

                        Architect registeredArchitect = aRepository.insertRegisterArchitect(architect);
                        SharedPref sharedPref = new SharedPref(RegisterArchitectActivity.this);
                        sharedPref.enLoggedArchitect(registeredArchitect);
                        Toast.makeText(RegisterArchitectActivity.this, "Welcome, " + registeredArchitect.getArchitectName() + ".", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterArchitectActivity.this, ArchitectMainActivity.class);
                        bundle.putSerializable("MsArchitect", registeredArchitect);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finishAffinity();
                    }else{
                        Toast.makeText(RegisterArchitectActivity.this, "username already existed", Toast.LENGTH_SHORT).show();
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
