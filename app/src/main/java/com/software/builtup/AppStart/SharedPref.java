package com.software.builtup.AppStart;

import android.content.Context;
import android.content.SharedPreferences;

import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;

public class SharedPref {

    public static final String PREFERENCES_NAME = "sharedPreference";
    private SharedPreferences sharedPreferences;
    public SharedPref(Context context){
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public SharedPref(){
        super();
    }

    public void enLoggedClient(Client client){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ClientID", client.getClientID());
        editor.putString("ClientUsername", client.getClientUsername());
        editor.putString("ClientPassword", client.getClientPassword());
        editor.putString("ClientName", client.getClientName());
        editor.putString("ClientAddress", client.getClientAddress());
        editor.putString("ClientPhone", client.getClientPhone());
        editor.apply();
    }

    public Client clientLogger(){
        Client client = new Client();
        client.setClientID(sharedPreferences.getString("ClientID", ""));
        client.setClientUsername(sharedPreferences.getString("ClientUsername", ""));
        client.setClientPassword(sharedPreferences.getString("ClientPassword", ""));
        client.setClientName(sharedPreferences.getString("ClientName", ""));
        client.setClientAddress(sharedPreferences.getString("ClientAddress", ""));
        client.setClientPhone(sharedPreferences.getString("ClientPhone", ""));
        return client;
    }

    public void enLoggedArchitect(Architect architect){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ArchitectID", architect.getArchitectID());
        editor.putString("ArchitectUsername", architect.getArchitectUsername());
        editor.putString("ArchitectPassword", architect.getArchitectPassword());
        editor.putString("ArchitectName", architect.getArchitectName());
        editor.putString("ArchitectPhone", architect.getArchitectPhone());
        editor.putString("ArchitectOrganization", architect.getArchitectOrganization());
        editor.putString("ArchitectFieldFocus", architect.getArchitectFieldFocus());
        editor.putString("ArchitectAddress", architect.getArchitectAddress());
        editor.apply();
    }

    public Architect architectLogger(){
        Architect architect = new Architect();
        architect.setArchitectID(sharedPreferences.getString("ArchitectID", ""));
        architect.setArchitectUsername(sharedPreferences.getString("ArchitectUsername", ""));
        architect.setArchitectPassword(sharedPreferences.getString("ArchitectPassword", ""));
        architect.setArchitectName(sharedPreferences.getString("ArchitectName", ""));
        architect.setArchitectOrganization(sharedPreferences.getString("ArchitectOrganization", ""));
        architect.setArchitectFieldFocus(sharedPreferences.getString("ArchitectFieldFocus", ""));
        architect.setArchitectAddress(sharedPreferences.getString("ArchitectAddress", ""));
        architect.setArchitectPhone(sharedPreferences.getString("ArchitectPhone", ""));
        return architect;
    }

    public void LogOut(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("sharedPreference");
        editor.clear();
        editor.apply();
    }

}
