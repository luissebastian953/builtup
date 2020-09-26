package com.software.builtup.Client.InboxClient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.InboxArchitect.ArchitectInboxAdapter;
import com.software.builtup.Architect.InboxArchitect.ArchitectInboxDetail;
import com.software.builtup.Architect.InboxArchitect.ArchitectInboxFragment;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientInboxFragment extends Fragment {

    private ClientInboxAdapter clientInboxAdapter;
    private RecyclerView recyclerClientInbox;
    private List<TransactionModel> transactionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View viewInboxClient = inflater.inflate(R.layout.fragment_inbox_client, container, false);
        recyclerClientInbox = viewInboxClient.findViewById(R.id.client_inbox_recycler_c);
        setClientInboxAdapter();
        return viewInboxClient;
    }

    private void setClientInboxAdapter(){

        transactionList = new ArrayList<>();
        clientInboxAdapter = new ClientInboxAdapter(transactionList, new ClientInboxAdapter.ClientInboxClick(){
            @Override
            public void onClick(TransactionModel transactionModel) {
                Intent intent = new Intent(ClientInboxFragment.this.getContext(), ClientInboxDetail.class);
                intent.putExtra("TrTransaction", transactionModel);
                startActivity(intent);
            }
        });
        recyclerClientInbox.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerClientInbox.setAdapter(clientInboxAdapter);
    }

    private void getData(){
        SharedPref sharedPref = new SharedPref(ClientInboxFragment.this.requireContext());
        Client loggedClient = sharedPref.clientLogger();
        TransactionRepository transactionRepository = new TransactionRepository(this.getContext());
        transactionList.clear();
        transactionList.addAll(transactionRepository.getInboxNotified(loggedClient));
        clientInboxAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
