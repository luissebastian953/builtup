package com.software.builtup.Client.Renovation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.software.builtup.R;
import com.software.builtup.model.Architect;

import java.util.List;

public class RenovationAdapter extends RecyclerView.Adapter<RenovationAdapter.RenovationViewHolder> {

    private List<Architect> architectList;
    private RenovationAdapter.RenovationClick listenerR;

    public RenovationAdapter(List<Architect> architectList, RenovationAdapter.RenovationClick listenerR){
        this.architectList = architectList;
        this.listenerR = listenerR;
    }

    class RenovationViewHolder extends RecyclerView.ViewHolder{

        TextView nameRenovationArchitect, organizationRenovationArchitect;
        LinearLayout listDataRenovation;

        public RenovationViewHolder(@NonNull View dataView) {
            super(dataView);
            nameRenovationArchitect = dataView.findViewById(R.id.text_view_title_build_house_architect_c);
            organizationRenovationArchitect = dataView.findViewById(R.id.text_view_organization_build_house_architect_c);
            listDataRenovation = dataView.findViewById(R.id.build_house_list_data_c);
        }

        public void bind(final Architect architect){
            nameRenovationArchitect.setText(architect.getArchitectName());
            organizationRenovationArchitect.setText(architect.getArchitectOrganization());

            listDataRenovation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerR.onClick(architect);
                }
            });
        }
    }

    @NonNull
    @Override
    public RenovationAdapter.RenovationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewBuildHouse = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_build_house_adapter, parent, false);
        return new RenovationAdapter.RenovationViewHolder(viewBuildHouse);
    }

    @Override
    public void onBindViewHolder(@NonNull RenovationAdapter.RenovationViewHolder holder, int position) {
        holder.bind(architectList.get(position));
    }

    @Override
    public int getItemCount() {
        return architectList.size();
    }

    interface RenovationClick{
        void onClick(Architect architect);
    }
}
