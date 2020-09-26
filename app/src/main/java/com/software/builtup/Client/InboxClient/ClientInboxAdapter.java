package com.software.builtup.Client.InboxClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.builtup.Architect.InboxArchitect.ArchitectInboxAdapter;
import com.software.builtup.R;
import com.software.builtup.model.TransactionModel;

import java.util.List;

public class ClientInboxAdapter extends RecyclerView.Adapter<ClientInboxAdapter.ClientInboxViewHolder> {


    private List<TransactionModel> transactionModelList;
    private ClientInboxAdapter.ClientInboxClick listenerR;

    public ClientInboxAdapter(List<TransactionModel> transactionModelList, ClientInboxAdapter.ClientInboxClick listenerR){
        this.transactionModelList = transactionModelList;
        this.listenerR = listenerR;
    }

    class ClientInboxViewHolder extends RecyclerView.ViewHolder{

        TextView messageClientInbox, clientNameClientInboxArchitect;
        LinearLayout listDataFromClient, foregroundInboxclient;

        public ClientInboxViewHolder(@NonNull View dataView) {
            super(dataView);
            messageClientInbox = dataView.findViewById(R.id.text_view_title_inbox_status_inbox_c);
            clientNameClientInboxArchitect = dataView.findViewById(R.id.text_view_architect_name_inbox_c);
            listDataFromClient = dataView.findViewById(R.id.client_inbox_adapter);
            foregroundInboxclient = (LinearLayout) dataView.findViewById(R.id.client_adapter_foreground);
        }

        public void bind(final TransactionModel transactionModel){
            messageClientInbox.setText("Your request have been "+ transactionModel.getTransactionStatus());
            clientNameClientInboxArchitect.setText(transactionModel.getArchitectName());
            listDataFromClient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerR.onClick(transactionModel);
                }
            });
        }
    }

    @NonNull
    @Override
    public ClientInboxAdapter.ClientInboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View viewClientInbox = LayoutInflater
//                .from(parent.getContext())
//                .inflate(R.layout.activity_client_inbox_adapter, parent, false);
//        return new ClientInboxAdapter.ClientInboxViewHolder(viewClientInbox);
        return new ClientInboxViewHolder (
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClientInboxAdapter.ClientInboxViewHolder holder, int position) {
        holder.bind(transactionModelList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if(transactionModelList.get(position).getTransactionStatus().contains("Declined")){
            return R.layout.activity_client_inbox_rejected_adapter;
        }else return R.layout.activity_client_inbox_adapter;
    }

    @Override
    public int getItemCount() {
        return transactionModelList.size();
    }

    interface ClientInboxClick{
        void onClick(TransactionModel transactionModel);
    }

}
