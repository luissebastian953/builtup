package com.software.builtup.Client.ClientChatSystem;

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
import com.software.builtup.R;
import com.software.builtup.model.ChatSystem;
import com.software.builtup.model.Client;
import com.software.builtup.repository.ChatSystemRepository;
import com.software.builtup.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientChatFragment extends Fragment {

    private ChatListAdapter chatListAdapter;
    private RecyclerView recyclerClientChatList;
    private List<ChatSystem> chatSystemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewChatClient = inflater.inflate(R.layout.fragment_chat_client, container, false);
        recyclerClientChatList = viewChatClient.findViewById(R.id.contact_chat_list_c);
        setChatListAdapter();
        return viewChatClient;
    }

    private void setChatListAdapter(){
        chatSystemList = new ArrayList<>();
        chatListAdapter = new ChatListAdapter(chatSystemList, new ChatListAdapter.ChatListClick() {
            @Override
            public void onClick(ChatSystem chatSystem) {
                Intent intent = new Intent(ClientChatFragment.this.getContext(), ClientChatRecyclerActivity.class);
                intent.putExtra("TrChat", chatSystem);
                startActivity(intent);
            }
        });
        recyclerClientChatList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerClientChatList.setAdapter(chatListAdapter);
    }

    private void getData(){
        SharedPref sharedPref = new SharedPref(ClientChatFragment.this.requireContext());
        Client loggedClient = sharedPref.clientLogger();
        ChatSystemRepository chatSystemRepository = new ChatSystemRepository(this.getContext());
        chatSystemList.clear();
        chatSystemList.addAll(chatSystemRepository.getAllChatSystemByClient(loggedClient));
        chatListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
