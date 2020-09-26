package com.software.builtup.Architect.CurrentTransaction;

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

public class ArchitectCurrentTransactionAdapter extends RecyclerView.Adapter<ArchitectCurrentTransactionAdapter.ArchitectCurrentTransactionViewHolder> {

    private List<TransactionModel> transactionModelList;
    private ArchitectCurrentTransactionClick listenerH;

    public ArchitectCurrentTransactionAdapter(List<TransactionModel> transactionModelList, ArchitectCurrentTransactionClick listenerH){
        this.transactionModelList = transactionModelList;
        this.listenerH = listenerH;
    }

    class ArchitectCurrentTransactionViewHolder extends RecyclerView.ViewHolder{

        TextView architectCurrentTransactionClientConstructionType, architectCurrentTransactionClientNameArchitect, architectCurrentTransactionClientStatus;
        LinearLayout architectCurrentTransactionClientListAdapter;

        public ArchitectCurrentTransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            architectCurrentTransactionClientListAdapter = itemView.findViewById(R.id.current_transaction_list_data_c);
            architectCurrentTransactionClientConstructionType = itemView.findViewById(R.id.text_view_title_current_transaction_construction_type_c);
            architectCurrentTransactionClientNameArchitect = itemView.findViewById(R.id.text_view_client_current_transaction_name_c);
            architectCurrentTransactionClientStatus = itemView.findViewById(R.id.text_view_status_current_transaction_c);
        }
        public void bind(final TransactionModel transactionModel){
            architectCurrentTransactionClientConstructionType.setText(transactionModel.getConstructionType());
            architectCurrentTransactionClientNameArchitect.setText(transactionModel.getClientName());
            architectCurrentTransactionClientStatus.setText(transactionModel.getTransactionStatus());
            architectCurrentTransactionClientListAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerH.onClick(transactionModel);
                }
            });
        }
    }

    @NonNull
    @Override
    public ArchitectCurrentTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewClientHistory = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_architect_current_transaction_adapter, parent, false);
        return new ArchitectCurrentTransactionViewHolder(viewClientHistory);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchitectCurrentTransactionViewHolder holder, int position) {
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

    interface ArchitectCurrentTransactionClick{
        void onClick(TransactionModel transactionModel);
    }
}
