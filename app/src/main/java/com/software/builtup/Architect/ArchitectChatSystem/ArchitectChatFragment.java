package com.software.builtup.Architect.ArchitectChatSystem;

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
import com.software.builtup.model.Architect;
import com.software.builtup.model.ChatSystem;
import com.software.builtup.repository.ChatSystemRepository;

import java.util.ArrayList;
import java.util.List;

public class ArchitectChatFragment extends Fragment {

    private ArchitectChatListAdapter architectChatListAdapter;
    private RecyclerView recyclerArchitectChatList;
    private List<ChatSystem> chatSystemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewChatArchitect = inflater.inflate(R.layout.fragment_chat_architect, container, false);
        recyclerArchitectChatList = viewChatArchitect.findViewById(R.id.contact_chat_list_a);
        setChatListAdapter();
        return viewChatArchitect;
    }

    private void setChatListAdapter(){
        chatSystemList = new ArrayList<>();
        architectChatListAdapter = new ArchitectChatListAdapter(chatSystemList, new ArchitectChatListAdapter.ArchitectChatListClick() {
            @Override
            public void onClick(ChatSystem chatSystem) {
                Intent intent = new Intent(ArchitectChatFragment.this.getContext(), ArchitectChatRecyclerActivity.class);
                intent.putExtra("TrChat", chatSystem);
                startActivity(intent);
            }
        });
        recyclerArchitectChatList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerArchitectChatList.setAdapter(architectChatListAdapter);
    }

    private void getData(){
        SharedPref sharedPref = new SharedPref(ArchitectChatFragment.this.requireContext());
        Architect loggedArchitect = sharedPref.architectLogger();
        ChatSystemRepository chatSystemRepository = new ChatSystemRepository(this.getContext());
        chatSystemList.clear();
        chatSystemList.addAll(chatSystemRepository.getAllChatSystemByArchitect(loggedArchitect));
        architectChatListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
