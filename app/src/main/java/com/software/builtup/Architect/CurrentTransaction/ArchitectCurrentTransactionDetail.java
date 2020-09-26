package com.software.builtup.Architect.CurrentTransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.ArchitectMainActivity;
import com.software.builtup.Architect.InboxArchitect.ArchitectInboxDetail;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

public class ArchitectCurrentTransactionDetail extends AppCompatActivity {

    TextView clientNameArchitectCurrentTransactionDetail, clientStatusArchitectCurrentTransactionDetail, clientConstructionTypeCurrentTransactionDetail, clientBudgetCurrentTransactionDetail;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_current_transaction_detail);
        clientNameArchitectCurrentTransactionDetail = findViewById(R.id.text_view_name_architect_current_transaction_detail_a);
        clientStatusArchitectCurrentTransactionDetail = findViewById(R.id.text_view_info_architect_current_transaction_detail_status_a);
        clientConstructionTypeCurrentTransactionDetail = findViewById(R.id.text_view_info_architect_current_transaction_detail_construction_type_a);
        clientBudgetCurrentTransactionDetail = findViewById(R.id.text_view_info_architect_budget_current_transaction_detail_detail_a);
        btnFinish = findViewById(R.id.inbox_button_finish_transaction_a);

        Intent intent = this.getIntent();
        final SharedPref sharedPref = new SharedPref(ArchitectCurrentTransactionDetail.this);
        final Architect loggedArchitect = sharedPref.architectLogger();
        final Bundle bundle = intent.getExtras();
        final TransactionModel transactionModel = (TransactionModel) bundle.getSerializable("TrTransaction");

        clientNameArchitectCurrentTransactionDetail.setText(transactionModel.getClientName());
        clientStatusArchitectCurrentTransactionDetail.setText(transactionModel.getTransactionStatus());
        clientConstructionTypeCurrentTransactionDetail.setText(transactionModel.getConstructionType());
        clientBudgetCurrentTransactionDetail.setText("Rp. "+transactionModel.getBudget()+".00");


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String completed = "Completed";
                transactionModel.setTransactionStatus(completed);
                TransactionRepository transactionRepository = new TransactionRepository(ArchitectCurrentTransactionDetail.this);
                transactionRepository.UpdateTransaction(transactionModel);
                Intent intent = new Intent(ArchitectCurrentTransactionDetail.this, ArchitectMainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}
