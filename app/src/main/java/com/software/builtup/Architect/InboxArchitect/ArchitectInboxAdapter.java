package com.software.builtup.Architect.InboxArchitect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.software.builtup.Client.ClientList.ClientListAdapter;
import com.software.builtup.R;
import com.software.builtup.model.TransactionModel;

import java.util.List;

public class ArchitectInboxAdapter extends RecyclerView.Adapter<ArchitectInboxAdapter.ArchitectInboxViewHolder>{

    private List<TransactionModel> transactionModelList;
    private ArchitectInboxAdapter.ArchitectInboxClick listenerR;

    public ArchitectInboxAdapter(List<TransactionModel> transactionModelList, ArchitectInboxAdapter.ArchitectInboxClick listenerR){
        this.transactionModelList = transactionModelList;
        this.listenerR = listenerR;
    }

    class ArchitectInboxViewHolder extends RecyclerView.ViewHolder{

        TextView messageArchitectInbox, clientNameArchitectInboxArchitect;
        LinearLayout listDataFromClient;

        public ArchitectInboxViewHolder(@NonNull View dataView) {
            super(dataView);
            messageArchitectInbox = dataView.findViewById(R.id.text_view_title_inbox_message_client_a);
            clientNameArchitectInboxArchitect = dataView.findViewById(R.id.text_view_organization_inbox_a);
            listDataFromClient = dataView.findViewById(R.id.architect_inbox_adapter);
        }

        public void bind(final TransactionModel transactionModel){
            messageArchitectInbox.setText("Income Request");
            clientNameArchitectInboxArchitect.setText("Client : "+transactionModel.getClientName());

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
    public ArchitectInboxAdapter.ArchitectInboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewArchitectInbox = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_inbox_architect_adapter, parent, false);
        return new ArchitectInboxAdapter.ArchitectInboxViewHolder(viewArchitectInbox);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchitectInboxAdapter.ArchitectInboxViewHolder holder, int position) {
        holder.bind(transactionModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionModelList.size();
    }

    interface ArchitectInboxClick{
        void onClick(TransactionModel transactionModel);
    }
}
