package com.software.builtup.Architect.ArchitectHistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.builtup.Client.History.ClientHistoryAdapter;
import com.software.builtup.R;
import com.software.builtup.model.TransactionModel;

import java.util.List;

public class ArchitectHistoryAdapter extends RecyclerView.Adapter<ArchitectHistoryAdapter.ArchitectHistoryViewHolder> {

    private List<TransactionModel> transactionModelList;
    private HistoryClick listenerH;

    public ArchitectHistoryAdapter(List<TransactionModel> transactionModelList, HistoryClick listenerH){
        this.transactionModelList = transactionModelList;
        this.listenerH = listenerH;
    }

    class ArchitectHistoryViewHolder extends RecyclerView.ViewHolder{

        TextView historyArchitectConstructionType, historyArchitectNameArchitect, historyArchitectStatus;
        LinearLayout historyArchitectListAdapter;

        public ArchitectHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            historyArchitectListAdapter = itemView.findViewById(R.id.history_list_data_a);
            historyArchitectConstructionType = itemView.findViewById(R.id.text_view_title_history_construction_type_a);
            historyArchitectNameArchitect = itemView.findViewById(R.id.text_view_organization_history_name_a);
            historyArchitectStatus = itemView.findViewById(R.id.text_view_status_history_a);
        }
        public void bind(final TransactionModel transactionModel){
            historyArchitectConstructionType.setText(transactionModel.getConstructionType());
            historyArchitectNameArchitect.setText(transactionModel.getClientName());
            historyArchitectStatus.setText(transactionModel.getTransactionStatus());
            historyArchitectListAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerH.onClick(transactionModel);
                }
            });
        }
    }

    @NonNull
    @Override
    public ArchitectHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewArchitectHistory = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_architect_history_adapter, parent,false);
        return new ArchitectHistoryViewHolder(viewArchitectHistory);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchitectHistoryViewHolder holder, int position) {
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
