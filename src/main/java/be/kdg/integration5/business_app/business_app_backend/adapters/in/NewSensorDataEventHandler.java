package be.kdg.integration5.business_app.business_app_backend.adapters.in;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.Event;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.EventCatalog;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.EventMessage;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewSensorDataEvent;

import java.util.List;

public interface NewSensorDataEventHandler<T extends Event> {
    boolean appliesTo(EventCatalog eventCatalog);
    default NewSensorDataEventHandler<T> receive(EventMessage eventMessage) {
        return this;
    }
    List<NewSensorDataEvent> map(String eventBody);
    void handle(List<NewSensorDataEvent> responseEventBody);
}
