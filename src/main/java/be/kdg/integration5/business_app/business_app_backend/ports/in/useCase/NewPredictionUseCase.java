package be.kdg.integration5.business_app.business_app_backend.ports.in.useCase;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewPredictionEvent;

public interface NewPredictionUseCase {
    void newPrediction(NewPredictionEvent event);
}
