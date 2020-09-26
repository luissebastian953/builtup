package com.software.builtup.Architect;

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
import com.software.builtup.Architect.ArchitectHistory.ArchitectHistoryActivityRecycler;
import com.software.builtup.Architect.CurrentTransaction.ArchitectCurrentTransactionRecyclerActivity;
import com.software.builtup.Architect.Feedback.ArchitectFeedbackActivity;
import com.software.builtup.Client.Renovation.RenovationRecycler;
import com.software.builtup.Client.buildHouse.BuildHouseRecycler;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;

public class ArchitectHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.fragment_home_architect, container, false);

        LinearLayout cardCurrentTransaction, cardHistory, cardFeedback;

        cardCurrentTransaction = inflaterView.findViewById(R.id.linear_layout_card_current_transaction_a);
        cardHistory = inflaterView.findViewById(R.id.linear_layout_card_history_a);
        cardFeedback = inflaterView.findViewById(R.id.linear_layout_card_feedback_a);

        final SharedPref sharedPref = new SharedPref(ArchitectHomeFragment.this.getContext());
        final Architect loggedArchitect = sharedPref.architectLogger();
        final Bundle bundle = new Bundle();

        cardCurrentTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArchitectCurrentTransactionRecyclerActivity.class);
                bundle.putSerializable("MsArchitect", loggedArchitect);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArchitectHistoryActivityRecycler.class);
                bundle.putSerializable("MsArchitect", loggedArchitect);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cardFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArchitectFeedbackActivity.class);
                bundle.putSerializable("MsArchitect", loggedArchitect);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return inflaterView;
    }
}
