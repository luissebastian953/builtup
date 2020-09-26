package com.software.builtup.Client;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Client.History.ClientHistoryActivityRecycler;
import com.software.builtup.Client.Renovation.RenovationRecycler;
import com.software.builtup.Client.SearchBar.SearchBarActivity;
import com.software.builtup.Client.buildHouse.BuildHouseRecycler;
import com.software.builtup.Client.feedback.FeedBackActivity;
import com.software.builtup.R;
import com.software.builtup.model.Client;

public class ClientHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.fragment_home_client, container, false);

        LinearLayout cardBuildHouse, cardRenovation, cardHistory, cardFeedback, searchClickOnDirect;

        cardBuildHouse = inflaterView.findViewById(R.id.linear_layout_card_build_house_c);
        cardRenovation = inflaterView.findViewById(R.id.linear_layout_card_renovation_c);
        cardHistory = inflaterView.findViewById(R.id.linear_layout_card_history_c);
        cardFeedback = inflaterView.findViewById(R.id.linear_layout_card_feedback_c);
        searchClickOnDirect = inflaterView.findViewById(R.id.search_bar_home_client);

        final SharedPref sharedPref = new SharedPref(ClientHomeFragment.this.getContext());
        final Client loggedClient = sharedPref.clientLogger();
        final Bundle bundle = new Bundle();

        cardBuildHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BuildHouseRecycler.class);
                bundle.putSerializable("MsClient", loggedClient);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cardRenovation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RenovationRecycler.class);
                bundle.putSerializable("MsClient", loggedClient);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClientHistoryActivityRecycler.class);
                bundle.putSerializable("MsClient", loggedClient);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cardFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedBackActivity.class);
                bundle.putSerializable("MsClient", loggedClient);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        searchClickOnDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchBarActivity.class);
                bundle.putSerializable("MsClient", loggedClient);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return inflaterView;
    }
}
