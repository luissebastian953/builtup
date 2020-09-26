package com.software.builtup.Client.ClientList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Client.ArchitectListDetailActivity;
import com.software.builtup.Client.ClientProfileFragment;
import com.software.builtup.Client.buildHouse.BuildHouseAdapter;
import com.software.builtup.Client.buildHouse.BuildHouseRecycler;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.ArchitectRepository;
import com.software.builtup.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientListFragment extends Fragment {

    private ClientListAdapter clientListAdapter;
    private RecyclerView recyclerClientList;
    private TextView emptyView;
    private List<TransactionModel> transactionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewList = inflater.inflate(R.layout.fragment_list_client, container, false);

        recyclerClientList = viewList.findViewById(R.id.client_list_recycler_c);
        emptyView = (TextView) viewList.findViewById(R.id.empty_view);
        setClientListAdapter();

        return viewList;
    }

    private void setClientListAdapter(){

        transactionList = new ArrayList<>();
        clientListAdapter = new ClientListAdapter(transactionList, new ClientListAdapter.ClientListClick(){
            @Override
            public void onClick(TransactionModel transactionModel) {
                if (transactionModel.getTransactionStatus().contains("Accepted")){
                    Intent intent = new Intent(ClientListFragment.this.getContext(), ClientListDetailAccepted.class);
                    intent.putExtra("TrTransaction", transactionModel);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ClientListFragment.this.getContext(), ClientListDetail.class);
                    intent.putExtra("TrTransaction", transactionModel);
                    startActivity(intent);
                }
            }
        });
        recyclerClientList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerClientList.setAdapter(clientListAdapter);
    }

    private void getData(){
        SharedPref sharedPref = new SharedPref(ClientListFragment.this.requireContext());
        Client loggedClient = sharedPref.clientLogger();
        TransactionRepository transactionRepository = new TransactionRepository(this.getContext());
        transactionList.clear();
        transactionList.addAll(transactionRepository.getAllClientListAccepted(loggedClient));
        transactionList.addAll(transactionRepository.getAllClientListPending(loggedClient));
        clientListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
