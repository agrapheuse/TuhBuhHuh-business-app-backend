package be.kdg.integration5.business_app.business_app_backend.adapters.in.amqp;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.NewPredictionEventHandler;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.EventCatalog;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewPredictionEvent;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.NewPredictionUseCase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class NewPredictionAMQPAdapter implements NewPredictionEventHandler<NewPredictionEvent> {
    private final static Logger log = LoggerFactory.getLogger(NewSensorDataAMQPAdapter.class);
    private final NewPredictionUseCase newSensorDataCreatedUseCase;
    private final ObjectMapper objectMapper;

    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.NEW_PREDICTION_DATA == eventCatalog;
    }

    @Override
    public List<NewPredictionEvent> map(String eventBody) {
        try {
            return objectMapper.readValue(eventBody, new TypeReference<List<NewPredictionEvent>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(List<NewPredictionEvent> predictionEventBodies) {
        predictionEventBodies.forEach(newSensorDataCreatedUseCase::newPrediction);
    }
}
