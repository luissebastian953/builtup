package com.software.builtup.Client.History;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.builtup.R;
import com.software.builtup.model.TransactionModel;

import java.util.List;

import static com.software.builtup.R.layout.activity_client_history_adapter;

public class ClientHistoryAdapter extends RecyclerView.Adapter<ClientHistoryAdapter.ClientHistoryViewHolder> {

    private List<TransactionModel> transactionModelList;
    private HistoryClick listenerH;

    public ClientHistoryAdapter(List<TransactionModel> transactionModelList, HistoryClick listenerH){
        this.transactionModelList = transactionModelList;
        this.listenerH = listenerH;
    }

    class ClientHistoryViewHolder extends RecyclerView.ViewHolder{

        TextView historyClientConstructionType, historyClientNameArchitect, historyClientStatus;
        LinearLayout historyClientListAdapter;

        public ClientHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            historyClientListAdapter = itemView.findViewById(R.id.history_list_data_c);
            historyClientConstructionType = itemView.findViewById(R.id.text_view_title_history_construction_type_c);
            historyClientNameArchitect = itemView.findViewById(R.id.text_view_architect_history_name_c);
            historyClientStatus = itemView.findViewById(R.id.text_view_status_history_c);
        }
        public void bind(final TransactionModel transactionModel){
            historyClientConstructionType.setText(transactionModel.getConstructionType());
            historyClientNameArchitect.setText(transactionModel.getArchitectName());
            historyClientStatus.setText(transactionModel.getTransactionStatus());
            historyClientListAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerH.onClick(transactionModel);
                }
            });
        }
    }

    @NonNull
    @Override
    public ClientHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewClientHistory = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_client_history_adapter, parent, false);
        return new ClientHistoryViewHolder(viewClientHistory);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHistoryViewHolder holder, int position) {
        holder.bind(transactionModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    interface HistoryClick{
        void onClick(TransactionModel transactionModel);
    }
}
