package be.kdg.integration5.business_app.business_app_backend.core.ETL;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewPredictionEvent;
import be.kdg.integration5.business_app.business_app_backend.ports.in.projection.NewPredictionProjector;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.NewPredictionUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.out.prediction.NewPredictionCreatePort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultNewPredictionUseCase implements NewPredictionUseCase {
    private final static Logger log = LoggerFactory.getLogger(DefaultNewSensorDataCreatedUseCase.class);
    private final NewPredictionProjector newPredictionProjector;
    private final List<NewPredictionCreatePort> newPredictionCreatePorts;

    @Override
    public void newPrediction(NewPredictionEvent event) {
        log.info("Received new predictions: {}", event);
        var data = newPredictionProjector.project(event);
        newPredictionCreatePorts.forEach(p -> p.newPrediction(data));
    }
}
