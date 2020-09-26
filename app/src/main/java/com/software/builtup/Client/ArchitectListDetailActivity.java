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
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

public class ArchitectListDetailActivity extends AppCompatActivity {

    EditText messageToBH, budgetToBH;
    Button btnSubmitBHArchitect;
    TextView detailArchitectName, detailArchitectOrganization, detailArchitectPhone, detailArchitectAddresss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_house_detail);

        detailArchitectName = findViewById(R.id.text_view_name_build_house_architect_c);
        detailArchitectOrganization = findViewById(R.id.text_view_organization_build_house_architect_c);
        detailArchitectPhone = findViewById(R.id.text_view_info_architect_phone_bh_c);
        detailArchitectAddresss = findViewById(R.id.text_view_info_architect_address_bh_c);

        messageToBH = findViewById(R.id.edit_text_message_to_bh_c);
        budgetToBH = findViewById(R.id.edit_text_budget_c);
        btnSubmitBHArchitect = findViewById(R.id.button_submit_request_send_bh_c);

        final SharedPref sharedPref = new SharedPref(ArchitectListDetailActivity.this);
        final Client loggedClient = sharedPref.clientLogger();
        final Bundle bundle = new Bundle();

        final Architect architectEnList = (Architect) getIntent().getSerializableExtra("MsArchitect");
        if(architectEnList != null){
            detailArchitectName.setText(architectEnList.getArchitectName());
            detailArchitectOrganization.setText(architectEnList.getArchitectOrganization());
            detailArchitectPhone.setText(architectEnList.getArchitectPhone());
            detailArchitectAddresss.setText(architectEnList.getArchitectAddress());
        }

        btnSubmitBHArchitect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestMessage = messageToBH.getText().toString().trim();
                String requestBudget = budgetToBH.getText().toString().trim();

                if(requestMessage == "" || requestMessage.isEmpty()){
                    Toast.makeText(ArchitectListDetailActivity.this, "Please fill your message before requesting", Toast.LENGTH_SHORT).show();
                }else{

                    TransactionRepository transactionRepository = new TransactionRepository(ArchitectListDetailActivity.this);
                    TransactionModel transactionModel = new TransactionModel();

                    if(transactionRepository.ValidTransaction(architectEnList.getArchitectID() ,loggedClient.getClientID()) == true){

                        transactionModel.setTransactionID(
                                transactionRepository.IDEncoder(loggedClient.getClientName(),
                                        architectEnList.getArchitectName(),
                                        architectEnList.getArchitectFieldFocus())
                        );
                        transactionModel.setArchitectID(architectEnList.getArchitectID());
                        transactionModel.setArchitectName(architectEnList.getArchitectName());
                        transactionModel.setArchitectName(architectEnList.getArchitectOrganization());
                        transactionModel.setClientID(loggedClient.getClientID());
                        transactionModel.setConstructionType(architectEnList.getArchitectFieldFocus());
                        transactionModel.setTransactionStatus("Pending");
                        transactionModel.setRequestMessage(requestMessage);
                        transactionModel.setBudget(requestBudget);
                        String completedTransaction = transactionRepository.insertTransaction(transactionModel);

                        if(completedTransaction != ""){
                            Toast.makeText(ArchitectListDetailActivity.this, "Request confirmation has been sent", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ArchitectListDetailActivity.this, "Anomalies Invalidated", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(ArchitectListDetailActivity.this, ClientMainActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }else{
                        Toast.makeText(ArchitectListDetailActivity.this, "Your already made this transaction", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
