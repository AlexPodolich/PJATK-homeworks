package com.example.finalproject.model.model_interface;

import com.example.finalproject.model.ReservedRoom;

import java.util.Set;

public interface IAdministrator {
    void addAdminResponsibility(String adminResponsibility);
    Set<String> getAdminResponsibilities();
}
