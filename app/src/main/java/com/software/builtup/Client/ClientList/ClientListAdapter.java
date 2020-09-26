package com.software.builtup.Client.ClientList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.builtup.Client.Renovation.RenovationAdapter;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.TransactionModel;

import java.util.List;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ClientListViewHolder>  {

    private List<TransactionModel> transactionModelList;
    private ClientListAdapter.ClientListClick listenerR;

    public ClientListAdapter(List<TransactionModel> transactionModelList, ClientListAdapter.ClientListClick listenerR){
        this.transactionModelList = transactionModelList;
        this.listenerR = listenerR;
    }

    class ClientListViewHolder extends RecyclerView.ViewHolder{

        TextView nameClientListArchitect, organizationClientListArchitect, statusClientList;
        LinearLayout listDataFromClient;

        public ClientListViewHolder(@NonNull View dataView) {
            super(dataView);
            nameClientListArchitect = dataView.findViewById(R.id.text_view_title_build_house_architect_c);
            organizationClientListArchitect = dataView.findViewById(R.id.text_view_organization_build_house_architect_c);
            listDataFromClient = dataView.findViewById(R.id.linear_layout_client_list_adapter_c);
            statusClientList = dataView.findViewById(R.id.text_view_status_report_list_c);
        }

        public void bind(final TransactionModel transactionModel){
            nameClientListArchitect.setText(transactionModel.getArchitectName());
            organizationClientListArchitect.setText(transactionModel.getArchitectOrganization());
            statusClientList.setText(transactionModel.getTransactionStatus());

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
    public ClientListAdapter.ClientListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View viewBuildHouse = LayoutInflater
//                .from(parent.getContext())
//                .inflate(R.layout.activity_client_list_adapter, parent, false);
//        return new ClientListAdapter.ClientListViewHolder(viewBuildHouse);
        return new ClientListViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(viewType, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if(transactionModelList.get(position).getTransactionStatus().contains("Accepted")) return R.layout.activity_client_list_adapter_accepted;
        else return R.layout.activity_client_list_adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientListAdapter.ClientListViewHolder holder, int position) {
        holder.bind(transactionModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionModelList.size();
    }

    interface ClientListClick{
        void onClick(TransactionModel transactionModel);
    }

}
