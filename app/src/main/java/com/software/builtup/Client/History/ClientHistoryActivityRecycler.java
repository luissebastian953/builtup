package com.software.builtup.Client.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Client.ClientChatSystem.ClientChatRecyclerActivity;
import com.software.builtup.R;
import com.software.builtup.model.Client;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientHistoryActivityRecycler extends AppCompatActivity {

    private ClientHistoryAdapter clientHistoryAdapter;
    private RecyclerView recyclerHistoryClient;
    private List<TransactionModel> transactionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_history_recycler);
        recyclerHistoryClient = findViewById(R.id.history_recycler_c);
        setClientHistoryAdapter();
    }

    public void setClientHistoryAdapter(){
        transactionModelList = new ArrayList<>();
        clientHistoryAdapter = new ClientHistoryAdapter(transactionModelList, new ClientHistoryAdapter.HistoryClick(){
            @Override
            public void onClick(TransactionModel transactionModel) {
                Intent intent = new Intent(ClientHistoryActivityRecycler.this, ClientHistoryDetail.class);
                intent.putExtra("TrTransaction", transactionModel);
                startActivity(intent);
            }
        });
        recyclerHistoryClient.setLayoutManager(new LinearLayoutManager(this));
        recyclerHistoryClient.setAdapter(clientHistoryAdapter);
    }

    private void getData(){
        SharedPref sharedPref = new SharedPref(ClientHistoryActivityRecycler.this);
        Client loggedClient = sharedPref.clientLogger();
        TransactionRepository transactionRepository = new TransactionRepository(ClientHistoryActivityRecycler.this);
        transactionModelList.clear();
        transactionModelList.addAll(transactionRepository.getAllClientListHistory(loggedClient));
        clientHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
