package com.software.builtup.Client.ClientList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.software.builtup.R;
import com.software.builtup.model.TransactionModel;

public class ClientListDetailAccepted extends AppCompatActivity {

    TextView architectNameClientList, architectOrganizationClientList, transactionStatus, transactionConstructionType, transactionBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list_detail_accepted);

        architectNameClientList = findViewById(R.id.text_view_name_client_list_architect_accepted_c);
        architectOrganizationClientList = findViewById(R.id.text_view_organization_client_list_architect_accepted_c);
        transactionStatus = findViewById(R.id.text_view_info_architect_status_client_list_accepted_c);
        transactionConstructionType = findViewById(R.id.text_view_info_architect_construction_type_client_list_accepted_c);
        transactionBudget = findViewById(R.id.text_view_info_architect_budget_client_list_accepted_c);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        final TransactionModel transactionModel = (TransactionModel) bundle.getSerializable("TrTransaction");

        architectNameClientList.setText(transactionModel.getArchitectName());
        architectOrganizationClientList.setText(transactionModel.getArchitectOrganization());
        transactionStatus.setText(transactionModel.getTransactionStatus());
        transactionConstructionType.setText(transactionModel.getConstructionType());
        transactionBudget.setText("Rp. "+transactionModel.getBudget()+".00");
    }
}
