package com.software.builtup.Client.ClientChatSystem;

import android.app.job.JobInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.software.builtup.R;
import com.software.builtup.model.ChatDetail;
import com.software.builtup.model.ChatSystem;

import java.util.ArrayList;
import java.util.List;

public class ClientMessageAdapter extends RecyclerView.Adapter<ClientMessageAdapter.CustomViewHolder> {

    private List<ChatDetail> chatDetailList;
    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public CustomViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.text_message);
        }
    }

    List<ChatDetail> chatDetails = new ArrayList<>();
    public ClientMessageAdapter(List<ChatDetail> chatDetails){
        this.chatDetails = chatDetails;
    }

    @Override
    public int getItemViewType(int position) {
        if(chatDetails.get(position).getCurrentHolder().contains("Client")){
            return R.layout.right_chat_side;
        } else if(chatDetails.get(position).getCurrentHolder().contains("Architect"))
            return R.layout.left_chat_side;
        else return R.layout.left_chat_side;
    }

    @NonNull
    @Override
    public ClientMessageAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(viewType, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ClientMessageAdapter.CustomViewHolder holder, int position) {
        holder.textView.setText(chatDetails.get(position).getMessageText());
    }

    @Override
    public int getItemCount() {
        return chatDetails.size();
    }

}
