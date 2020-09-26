package com.software.builtup.Client.History;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Client.ClientMainActivity;
import com.software.builtup.MainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Client;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

public class ClientHistoryDetail extends AppCompatActivity {

    TextView clientHistoryName, clientHistoryOrganization, clientHistoryBudget, clientHistoryStatus, clientHistoryConstructionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_history_detail);
        clientHistoryName = findViewById(R.id.text_view_name_history_architect_c);
        clientHistoryOrganization = findViewById(R.id.text_view_organization_history_architect_c);
        clientHistoryBudget = findViewById(R.id.text_view_info_architect_budget_history_c);
        clientHistoryStatus = findViewById(R.id.text_view_info_architect_status_history_c);
        clientHistoryConstructionType = findViewById(R.id.text_view_info_architect_construction_type_history_c);
        
        final SharedPref sharedPref = new SharedPref(ClientHistoryDetail.this);
        Client loggedClient = sharedPref.clientLogger();
        Bundle bundle = new Bundle();
        final TransactionModel transactionModel = (TransactionModel) getIntent().getSerializableExtra("TrTransaction");
        if(transactionModel == null){
            Toast.makeText(this, "onload error", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ClientHistoryDetail.this, ClientMainActivity.class);
            startActivity(intent);
        }
        clientHistoryName.setText(transactionModel.getArchitectName());
        clientHistoryConstructionType.setText(transactionModel.getConstructionType());
        clientHistoryStatus.setText(transactionModel.getTransactionStatus());
        clientHistoryBudget.setText("Rp. " + transactionModel.getBudget()+" .00");
        clientHistoryOrganization.setText(transactionModel.getArchitectOrganization());
    }
}
