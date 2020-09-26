package com.software.builtup.Architect.ArchitectChatSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.builtup.R;
import com.software.builtup.model.ChatDetail;

import java.util.ArrayList;
import java.util.List;

public class ArchitectMessageAdapter extends RecyclerView.Adapter<ArchitectMessageAdapter.CustomViewHolder> {

    private List<ChatDetail> chatDetailList;
    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public CustomViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.text_message);
        }
    }
    List<ChatDetail> chatDetails = new ArrayList<>();
    public ArchitectMessageAdapter(List<ChatDetail> chatDetails){
        this.chatDetails = chatDetails;
    }

    @Override
    public int getItemViewType(int position) {
        if(chatDetails.get(position).getCurrentHolder().contains("Architect") ){
            return R.layout.right_chat_side;
        } else if(chatDetails.get(position).getCurrentHolder().contains("Client"))
            return R.layout.left_chat_side;
        else return R.layout.left_chat_side;
    }

    @NonNull
    @Override
    public ArchitectMessageAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(viewType, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ArchitectMessageAdapter.CustomViewHolder holder, int position) {
        holder.textView.setText(chatDetails.get(position).getMessageText());
    }

    @Override
    public int getItemCount() {
        return chatDetails.size();
    }


}
