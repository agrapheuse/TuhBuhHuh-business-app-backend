package be.kdg.integration5.business_app.business_app_backend.adapters.in;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.*;

import java.util.List;

public interface NewPredictionEventHandler <T extends Event> {
    boolean appliesTo(EventCatalog eventCatalog);
    default NewPredictionEventHandler<T> receive(EventMessage eventMessage) {
        return this;
    }
    List<NewPredictionEvent> map(String eventBody);
    void handle(List<NewPredictionEvent> predictionEventBodies);
}
