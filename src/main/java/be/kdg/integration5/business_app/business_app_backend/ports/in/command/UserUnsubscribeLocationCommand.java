package be.kdg.integration5.business_app.business_app_backend.ports.in.command;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.User;

public record UserUnsubscribeLocationCommand(
        User user,
        Location location
){}
