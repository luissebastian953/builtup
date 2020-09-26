package com.software.builtup.Architect.ArchitectHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class ArchitectHistoryActivityRecycler extends AppCompatActivity {

    private ArchitectHistoryAdapter architectHistoryAdapter;
    private RecyclerView recyclerHistoryArchitect;
    private List<TransactionModel> transactionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_history_recycler);
        recyclerHistoryArchitect = findViewById(R.id.history_recycler_a);
        setArchitectHistoryAdapter();
    }

    public void setArchitectHistoryAdapter(){
        transactionModelList = new ArrayList<>();
        architectHistoryAdapter = new ArchitectHistoryAdapter(transactionModelList, new ArchitectHistoryAdapter.HistoryClick(){
            @Override
            public void onClick(TransactionModel transactionModel) {
                Intent intent = new Intent(ArchitectHistoryActivityRecycler.this, ArchitectHistoryDetail.class);
                intent.putExtra("TrTransaction", transactionModel);
                startActivity(intent);
            }
        });
        recyclerHistoryArchitect.setLayoutManager(new LinearLayoutManager(this));
        recyclerHistoryArchitect.setAdapter(architectHistoryAdapter);
    }

    private void getData(){
        SharedPref sharedPref = new SharedPref(ArchitectHistoryActivityRecycler.this);
        Architect loggedArchitect = sharedPref.architectLogger();
        TransactionRepository transactionRepository = new TransactionRepository(ArchitectHistoryActivityRecycler.this);
        transactionModelList.clear();
        transactionModelList.addAll(transactionRepository.getAllArchitectListHistory(loggedArchitect));
        architectHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
