package com.software.builtup.Architect.ArchitectChatSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.builtup.Client.ClientChatSystem.ChatListAdapter;
import com.software.builtup.R;
import com.software.builtup.model.ChatSystem;

import java.util.List;

public class ArchitectChatListAdapter extends RecyclerView.Adapter<ArchitectChatListAdapter.ArchitectChatListViewHolder>  {

    private List<ChatSystem> chatSystemList;
    private ArchitectChatListAdapter.ArchitectChatListClick listenerR;

    public ArchitectChatListAdapter(List<ChatSystem> chatSystemList, ArchitectChatListAdapter.ArchitectChatListClick listenerR){
        this.chatSystemList = chatSystemList;
        this.listenerR = listenerR;
    }

    class ArchitectChatListViewHolder extends RecyclerView.ViewHolder{

        TextView architectNameContactClient;
        LinearLayout listChatFromClient;

        public ArchitectChatListViewHolder(@NonNull View dataView) {
            super(dataView);
            architectNameContactClient = dataView.findViewById(R.id.chat_list_name_a);
            listChatFromClient = dataView.findViewById(R.id.linear_layout_chat_list_adapter_a);
        }

        public void bind(final ChatSystem chatSystem){
            architectNameContactClient.setText(chatSystem.getClientName());
            listChatFromClient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerR.onClick(chatSystem);
                }
            });
        }
    }
    @NonNull
    @Override
    public ArchitectChatListAdapter.ArchitectChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewChatList = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_architect_chat_list_adapter, parent, false);
        return new ArchitectChatListAdapter.ArchitectChatListViewHolder(viewChatList);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchitectChatListAdapter.ArchitectChatListViewHolder holder, int position) {
        holder.bind(chatSystemList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatSystemList.size();
    }

    interface ArchitectChatListClick{
        void onClick(ChatSystem chatSystem);
    }
}
