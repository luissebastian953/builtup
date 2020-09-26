package com.software.builtup.Client.buildHouse;

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
import com.software.builtup.model.Architect;

import java.util.List;

public class BuildHouseAdapter extends RecyclerView.Adapter<BuildHouseAdapter.BuildHouseViewHolder> {

    private List<Architect> architectList;
    private BuildHouseClick listenerBHC;

    public BuildHouseAdapter(List<Architect> architectList, BuildHouseClick listenerBHC){
        this.architectList = architectList;
        this.listenerBHC = listenerBHC;
    }

    class BuildHouseViewHolder extends RecyclerView.ViewHolder{

        TextView nameBuildHouseArchitect, organizationBuildHouseArchitect;
        LinearLayout listDataBuildHouse;

        public BuildHouseViewHolder(@NonNull View dataView) {
            super(dataView);
            nameBuildHouseArchitect = dataView.findViewById(R.id.text_view_title_build_house_architect_c);
            organizationBuildHouseArchitect = dataView.findViewById(R.id.text_view_organization_build_house_architect_c);
            listDataBuildHouse = dataView.findViewById(R.id.build_house_list_data_c);
        }

        public void bind(final Architect architect){
            nameBuildHouseArchitect.setText(architect.getArchitectName());
            organizationBuildHouseArchitect.setText(architect.getArchitectOrganization());

            listDataBuildHouse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerBHC.onClick(architect);
                }
            });
        }
    }

    @NonNull
    @Override
    public BuildHouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewBuildHouse = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_build_house_adapter, parent, false);
        return new BuildHouseViewHolder(viewBuildHouse);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildHouseViewHolder holder, int position) {
        holder.bind(architectList.get(position));
    }

    @Override
    public int getItemCount() {
        return architectList.size();
    }

    interface BuildHouseClick{
        void onClick(Architect architect);
    }

}
