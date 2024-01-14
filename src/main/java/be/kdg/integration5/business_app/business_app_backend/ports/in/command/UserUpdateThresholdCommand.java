package be.kdg.integration5.business_app.business_app_backend.ports.in.command;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;

public record UserUpdateThresholdCommand(
        User user,
        ValueType valueType,
        int value
        ){}
