package be.kdg.integration5.business_app.business_app_backend.ports.out.prediction;

import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;

public interface NewPredictionCreatePort {
    void newPrediction(Prediction prediction);
}
