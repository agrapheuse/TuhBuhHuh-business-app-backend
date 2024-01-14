package be.kdg.integration5.business_app.business_app_backend.ports.in.projection;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewSensorDataEvent;
import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;

public interface NewSensorDataCreatedProjector {
    Measurement project(NewSensorDataEvent event);
}
