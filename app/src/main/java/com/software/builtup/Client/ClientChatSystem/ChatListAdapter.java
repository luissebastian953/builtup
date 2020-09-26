package com.software.builtup.Client.ClientChatSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.builtup.Client.InboxClient.ClientInboxAdapter;
import com.software.builtup.R;
import com.software.builtup.model.ChatSystem;
import com.software.builtup.model.TransactionModel;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder> {

    private List<ChatSystem> chatSystemList;
    private ChatListAdapter.ChatListClick listenerR;

    public ChatListAdapter(List<ChatSystem> chatSystemList, ChatListAdapter.ChatListClick listenerR){
        this.chatSystemList = chatSystemList;
        this.listenerR = listenerR;
    }

    class ChatListViewHolder extends RecyclerView.ViewHolder{

        TextView messageChatClient, architectNameContactClient;
        LinearLayout listChatFromClient;

        public ChatListViewHolder(@NonNull View dataView) {
            super(dataView);
            messageChatClient = dataView.findViewById(R.id.chat_list_organization_c);
            architectNameContactClient = dataView.findViewById(R.id.chat_list_name_c);
            listChatFromClient = dataView.findViewById(R.id.linear_layout_chat_list_adapter_c);
        }

        public void bind(final ChatSystem chatSystem){
            architectNameContactClient.setText(chatSystem.getArchitectName());
            messageChatClient.setText(chatSystem.getArchitectFieldFocus());
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
    public ChatListAdapter.ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewChatList = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_chat_list_adapter, parent, false);
        return new ChatListAdapter.ChatListViewHolder(viewChatList);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.ChatListViewHolder holder, int position) {
        holder.bind(chatSystemList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatSystemList.size();
    }

    interface ChatListClick{
        void onClick(ChatSystem chatSystem);
    }

}
