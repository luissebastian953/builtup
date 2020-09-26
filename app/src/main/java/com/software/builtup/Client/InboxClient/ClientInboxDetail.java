package com.software.builtup.Client.InboxClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.software.builtup.Architect.ArchitectMainActivity;
import com.software.builtup.Architect.InboxArchitect.ArchitectInboxDetail;
import com.software.builtup.Client.ClientMainActivity;
import com.software.builtup.R;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

public class ClientInboxDetail extends AppCompatActivity {

    TextView architectNameClientInboxDetail, architectOrganizationClientInbox, architectStatusClientInboxDetail, architectConstructionTypeInboxDetail, architectBudgetInboxDetail;
    ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_inbox_detail);

        architectNameClientInboxDetail = findViewById(R.id.text_view_name_architect_inbox_detail_a);
        architectOrganizationClientInbox = findViewById(R.id.text_view_organization_architect_inbox_detail_a);
        architectConstructionTypeInboxDetail = findViewById(R.id.text_view_info_architect_inbox_construction_type_detail_a);
        architectStatusClientInboxDetail = findViewById(R.id.text_view_info_architect_inbox_request_status_inbox_detail_c);
        architectBudgetInboxDetail = findViewById(R.id.text_view_info_inbox_client_budget_c);
        btnClose = findViewById(R.id.inbox_button_close_c);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        final TransactionModel transactionModel = (TransactionModel) bundle.getSerializable("TrTransaction");

        architectNameClientInboxDetail.setText(transactionModel.getArchitectName());
        architectOrganizationClientInbox.setText(transactionModel.getArchitectOrganization());
        architectConstructionTypeInboxDetail.setText(transactionModel.getConstructionType());
        architectStatusClientInboxDetail.setText(transactionModel.getTransactionStatus());
        architectBudgetInboxDetail.setText("Rp. "+transactionModel.getBudget()+".00");

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionRepository transactionRepository = new TransactionRepository(ClientInboxDetail.this);
                if(transactionModel.getTransactionStatus().contains("Accepted")) transactionModel.setTransactionStatus("Accepted-Current");
                else if(transactionModel.getTransactionStatus().contains("Completed")) transactionModel.setTransactionStatus("Task-Completed");
                transactionRepository.UpdateTransaction(transactionModel);
                Intent intent = new Intent(ClientInboxDetail.this, ClientMainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}
