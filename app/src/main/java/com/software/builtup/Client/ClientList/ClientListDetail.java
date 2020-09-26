package com.software.builtup.Client.ClientList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.software.builtup.Client.ClientMainActivity;
import com.software.builtup.R;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

public class ClientListDetail extends AppCompatActivity {

    TextView architectNameClientList, architectOrganizationClientList, transactionStatus, transactionConstructionType, transactionBudget;
    ImageButton btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list_detail);

        architectNameClientList = findViewById(R.id.text_view_name_client_list_architect_c);
        architectOrganizationClientList = findViewById(R.id.text_view_organization_client_list_architect_c);
        transactionStatus = findViewById(R.id.text_view_info_architect_status_client_list_c);
        transactionConstructionType = findViewById(R.id.text_view_info_architect_construction_type_client_list_c);
        transactionBudget = findViewById(R.id.text_view_info_architect_budget_client_list_c);

        btnCancel = findViewById(R.id.button_cancel_client_list_c);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        final TransactionModel transactionModel = (TransactionModel) bundle.getSerializable("TrTransaction");

        architectNameClientList.setText(transactionModel.getArchitectName());
        architectOrganizationClientList.setText(transactionModel.getArchitectOrganization());
        transactionStatus.setText(transactionModel.getTransactionStatus());
        transactionConstructionType.setText(transactionModel.getConstructionType());
        transactionBudget.setText("Rp. "+transactionModel.getBudget()+".00");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionRepository transactionRepository = new TransactionRepository(ClientListDetail.this);
                transactionRepository.CancelTransaction(transactionModel.getTransactionID());
                Intent intent = new Intent(ClientListDetail.this, ClientMainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}
