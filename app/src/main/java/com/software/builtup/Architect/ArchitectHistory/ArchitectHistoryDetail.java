package com.software.builtup.Architect.ArchitectHistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.ArchitectMainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.TransactionModel;

public class ArchitectHistoryDetail extends AppCompatActivity {

    TextView architectHistoryName, architectHistoryBudget, architectHistoryStatus, architectHistoryConstructionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_history_detail);
        architectHistoryName = findViewById(R.id.text_view_name_history_architect_a);
        architectHistoryBudget = findViewById(R.id.text_view_info_architect_budget_history_a);
        architectHistoryStatus = findViewById(R.id.text_view_status_trans_architect_history_a);
        architectHistoryConstructionType = findViewById(R.id.text_view_info_architect_construction_type_history_a);

        final SharedPref sharedPref = new SharedPref(ArchitectHistoryDetail.this);
        Architect loggedArchitect = sharedPref.architectLogger();
        Bundle bundle = new Bundle();
        final TransactionModel transactionModel = (TransactionModel) getIntent().getSerializableExtra("TrTransaction");
        if(transactionModel == null){
            Toast.makeText(this, "onload error", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ArchitectHistoryDetail.this, ArchitectMainActivity.class);
            startActivity(intent);
        }
        architectHistoryName.setText(transactionModel.getClientName());
        architectHistoryConstructionType.setText(transactionModel.getConstructionType());
        architectHistoryStatus.setText(transactionModel.getTransactionStatus());
        architectHistoryBudget.setText("Rp. " + transactionModel.getBudget()+" .00");
    }
}
