package com.software.builtup.Architect.InboxArchitect;

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
import com.software.builtup.Client.ClientList.ClientListAdapter;
import com.software.builtup.Client.ClientList.ClientListDetail;
import com.software.builtup.Client.ClientList.ClientListFragment;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class ArchitectInboxFragment extends Fragment {

    private ArchitectInboxAdapter architectInboxAdapter;
    private RecyclerView recyclerArchitectInbox;
    private List<TransactionModel> transactionList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewInboxArchitect = inflater.inflate(R.layout.fragment_inbox_architect, container, false);

        recyclerArchitectInbox = viewInboxArchitect.findViewById(R.id.architect_inbox_recycler_c);
        setArchitectInboxAdapter();
        return viewInboxArchitect;
    }

    private void setArchitectInboxAdapter(){

        transactionList = new ArrayList<>();
        architectInboxAdapter = new ArchitectInboxAdapter(transactionList, new ArchitectInboxAdapter.ArchitectInboxClick(){
            @Override
            public void onClick(TransactionModel transactionModel) {
                Intent intent = new Intent(getActivity(), ArchitectInboxDetail.class);
                intent.putExtra("TrTransaction", transactionModel);
                startActivity(intent);
            }
        });
        recyclerArchitectInbox.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerArchitectInbox.setAdapter(architectInboxAdapter);
    }

    private void getData(){
        SharedPref sharedPref = new SharedPref(ArchitectInboxFragment.this.requireContext());
        Architect loggedArchitect = sharedPref.architectLogger();
        TransactionRepository transactionRepository = new TransactionRepository(this.getContext());
        transactionList.clear();
        transactionList.addAll(transactionRepository.getAllClientListPendingArchitectInbox(loggedArchitect));
        architectInboxAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

}
