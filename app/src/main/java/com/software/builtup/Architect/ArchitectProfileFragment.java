package com.software.builtup.Architect;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.InboxArchitect.UpdateProfileArchitectActivity;
import com.software.builtup.Client.LoginClientActivity;
import com.software.builtup.Client.UpdateProfileClientActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;

public class ArchitectProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.fragment_profile_architect, container, false);

        TextView profArchitectName, profArchitectUsername, profArchitectPhone, profArchitectAddress, profArchitectOrganization, profArchitectFocusField;
        Button btnProfileArchitectLogout,btnProfileArchitectUpdate;

        profArchitectName = inflaterView.findViewById(R.id.text_view_fullname_profile_a);
        profArchitectUsername = inflaterView.findViewById(R.id.text_view_username_profile_a);
        profArchitectPhone = inflaterView.findViewById(R.id.text_view_phone_profile_a);
        profArchitectAddress = inflaterView.findViewById(R.id.text_view_address_profile_a);
        profArchitectOrganization = inflaterView.findViewById(R.id.text_view_organization_profile_a);
        profArchitectFocusField = inflaterView.findViewById(R.id.text_view_construction_type_profile_a);
        btnProfileArchitectLogout = inflaterView.findViewById(R.id.logout_architect);
        btnProfileArchitectUpdate = inflaterView.findViewById(R.id.update_architect);
        final SharedPref sharedPref = new SharedPref(ArchitectProfileFragment.this.getContext());
        final Architect loggedArchitect = sharedPref.architectLogger();
        final Bundle bundle = new Bundle();

        if(loggedArchitect != null){
            profArchitectName.setText(loggedArchitect.getArchitectName());
            profArchitectUsername.setText(loggedArchitect.getArchitectUsername());
            profArchitectPhone.setText(loggedArchitect.getArchitectPhone());
            profArchitectAddress.setText(loggedArchitect.getArchitectAddress());
            profArchitectOrganization.setText(loggedArchitect.getArchitectOrganization());
            profArchitectFocusField.setText(loggedArchitect.getArchitectFieldFocus());
        }

        btnProfileArchitectLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.LogOut();
                Intent intent = new Intent(getActivity(), LoginArchitectActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finishAffinity();
            }
        });

        btnProfileArchitectUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateProfileArchitectActivity.class);
                bundle.putSerializable("MsArchitect", loggedArchitect);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return inflaterView;
    }
}
