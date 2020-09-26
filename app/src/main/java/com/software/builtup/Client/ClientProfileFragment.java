package com.software.builtup.Client;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.software.builtup.R;
import com.software.builtup.model.Client;

import static androidx.core.app.ActivityCompat.finishAffinity;

public class ClientProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.fragment_profile_client, container, false);

        TextView profClientName, profClientUsername, profClientPhone, profClientAddress;
        Button btnProfileClientLogout, btnProfileClientUpdate;

        profClientName = inflaterView.findViewById(R.id.text_view_fullname_profile_c);
        profClientUsername = inflaterView.findViewById(R.id.text_view_username_profile_c);
        profClientPhone = inflaterView.findViewById(R.id.text_view_phone_profile_c);
        profClientAddress = inflaterView.findViewById(R.id.text_view_address_profile_c);
        btnProfileClientLogout = inflaterView.findViewById(R.id.logout_client);
        btnProfileClientUpdate = inflaterView.findViewById(R.id.update_client);
        final SharedPref sharedPref = new SharedPref(ClientProfileFragment.this.getContext());
        final Client loggedClient = sharedPref.clientLogger();
        final Bundle bundle = new Bundle();

        if(loggedClient != null){
            profClientName.setText(loggedClient.getClientName());
            profClientUsername.setText(loggedClient.getClientUsername());
            profClientPhone.setText(loggedClient.getClientPhone());
            profClientAddress.setText(loggedClient.getClientAddress());
        }

        btnProfileClientLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.LogOut();
                Intent intent = new Intent(getActivity(), LoginClientActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finishAffinity();
            }
        });

        btnProfileClientUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateProfileClientActivity.class);
                bundle.putSerializable("MsClient", loggedClient);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return inflaterView;
    }
}
