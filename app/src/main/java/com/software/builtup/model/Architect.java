package com.software.builtup.model;

import com.software.builtup.repository.ArchitectRepository;

import java.io.Serializable;

public class Architect implements Serializable {

    private String ArchitectID;
    private String ArchitectUsername;
    private String ArchitectPassword;
    private String ArchitectName;
    private String ArchitectPhone;
    private String ArchitectOrganization;
    private String ArchitectFieldFocus;
    private String ArchitectAddress;

    public Architect(String architectID, String architectUsername, String architectPassword, String architectName, String architectPhone, String architectOrganization, String architectFieldFocus, String architectAddress) {
        ArchitectID = architectID;
        ArchitectUsername = architectUsername;
        ArchitectPassword = architectPassword;
        ArchitectName = architectName;
        ArchitectPhone = architectPhone;
        ArchitectOrganization = architectOrganization;
        ArchitectFieldFocus = architectFieldFocus;
        ArchitectAddress = architectAddress;
    }

    public Architect() {

    }

    public String getArchitectID() {
        return ArchitectID;
    }

    public void setArchitectID(String architectID) {
        ArchitectID = architectID;
    }

    public String getArchitectUsername() {
        return ArchitectUsername;
    }

    public void setArchitectUsername(String architectUsername) {
        ArchitectUsername = architectUsername;
    }

    public String getArchitectPassword() {
        return ArchitectPassword;
    }

    public void setArchitectPassword(String architectPassword) {
        ArchitectPassword = architectPassword;
    }

    public String getArchitectName() {
        return ArchitectName;
    }

    public void setArchitectName(String architectName) {
        ArchitectName = architectName;
    }

    public String getArchitectPhone() {
        return ArchitectPhone;
    }

    public void setArchitectPhone(String architectPhone) {
        ArchitectPhone = architectPhone;
    }

    public String getArchitectOrganization() {
        return ArchitectOrganization;
    }

    public void setArchitectOrganization(String architectOrganization) {
        ArchitectOrganization = architectOrganization;
    }

    public String getArchitectFieldFocus() {
        return ArchitectFieldFocus;
    }

    public void setArchitectFieldFocus(String architectFieldFocus) {
        ArchitectFieldFocus = architectFieldFocus;
    }

    public String getArchitectAddress() {
        return ArchitectAddress;
    }

    public void setArchitectAddress(String architectAddress) {
        ArchitectAddress = architectAddress;
    }

}
