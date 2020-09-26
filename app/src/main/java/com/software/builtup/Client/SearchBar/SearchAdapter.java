package com.software.builtup.Client.SearchBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.builtup.R;
import com.software.builtup.model.Architect;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {

    private List<Architect> architectList;
    private List<Architect> architectList2;
    private SearchAdapter.SearchAdapterClick listenerS;

    public SearchAdapter(List<Architect> architectList, SearchAdapterClick listenerS) {
        this.architectList = architectList;
        architectList2 = new ArrayList<>(architectList);
        this.listenerS = listenerS;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView architectSearchName, architectSearchOrganization, architectSearchConstruction;
        LinearLayout listSearchAdapter;

        public SearchViewHolder(@NonNull View dataView) {
            super(dataView);
            listSearchAdapter = dataView.findViewById(R.id.search_adapter_linear_layout);
            architectSearchName = dataView.findViewById(R.id.text_view_architect_search_name_c);
            architectSearchConstruction = dataView.findViewById(R.id.text_view_title_search_construction_type_c);
            architectSearchOrganization = dataView.findViewById(R.id.text_view_title_search_organization_type_c);
        }

        public void bind(final Architect architect) {
            architectSearchName.setText(architect.getArchitectName());
            architectSearchConstruction.setText(architect.getArchitectFieldFocus() + " - ");
            if (architect.getArchitectOrganization().length() >= 13) {
                architectSearchOrganization.setText(architect.getArchitectOrganization().substring(0, 12) + "..");
            } else {
                architectSearchOrganization.setText(architect.getArchitectOrganization());
            }
            listSearchAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerS.onClick(architect);
                }
            });
        }
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewSearchList = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_search_adapter, parent, false);
        return new SearchAdapter.SearchViewHolder(viewSearchList);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bind(architectList.get(position));
    }

    @Override
    public int getItemCount() {
        return architectList.size();
    }

    interface SearchAdapterClick {
        void onClick(Architect architect);
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Architect> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(architectList2);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Architect architect : architectList2) {
                    if (architect.getArchitectName().toLowerCase().contains(pattern)) {
                        filteredList.add(architect);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            architectList.clear();
            architectList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
