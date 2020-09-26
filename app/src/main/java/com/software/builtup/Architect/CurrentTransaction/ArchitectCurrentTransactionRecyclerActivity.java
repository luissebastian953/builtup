package com.software.builtup.Architect.CurrentTransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.ArchitectHistory.ArchitectHistoryActivityRecycler;
import com.software.builtup.Client.ArchitectListDetailActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class ArchitectCurrentTransactionRecyclerActivity extends AppCompatActivity {

    private ArchitectCurrentTransactionAdapter architectCurrentTransactionAdapter;
    private RecyclerView recyclerCurrentTransaction;
    private List<TransactionModel> transactionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_current_transaction_recycler);
        recyclerCurrentTransaction = findViewById(R.id.current_transaction_recycler_a);
        setCurrentTransactionAdapter();
    }


    public void setCurrentTransactionAdapter(){
        transactionModelList = new ArrayList<>();
        architectCurrentTransactionAdapter = new ArchitectCurrentTransactionAdapter(transactionModelList, new ArchitectCurrentTransactionAdapter.ArchitectCurrentTransactionClick() {
            @Override
            public void onClick(TransactionModel transactionModel) {
                Intent intent = new Intent(ArchitectCurrentTransactionRecyclerActivity.this, ArchitectCurrentTransactionDetail.class);
                intent.putExtra("TrTransaction", transactionModel);
                startActivity(intent);
            }
        });
        recyclerCurrentTransaction.setLayoutManager(new LinearLayoutManager(ArchitectCurrentTransactionRecyclerActivity.this));
        recyclerCurrentTransaction.setAdapter(architectCurrentTransactionAdapter);
    }

    private void getData(){
        SharedPref sharedPref = new SharedPref(ArchitectCurrentTransactionRecyclerActivity.this);
        Architect loggedArchitect = sharedPref.architectLogger();
        TransactionRepository transactionRepository = new TransactionRepository(ArchitectCurrentTransactionRecyclerActivity.this);
        transactionModelList.clear();
        transactionModelList.addAll(transactionRepository.getAllCurrentTransactionArchitect(loggedArchitect));
        architectCurrentTransactionAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
