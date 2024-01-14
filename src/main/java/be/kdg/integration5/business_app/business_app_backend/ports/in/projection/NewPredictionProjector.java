package be.kdg.integration5.business_app.business_app_backend.ports.in.projection;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewPredictionEvent;
import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;

public interface NewPredictionProjector {
    Prediction project(NewPredictionEvent event);
}
