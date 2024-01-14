package be.kdg.integration5.business_app.business_app_backend.adapters.in.amqp;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.NewSensorDataEventHandler;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.EventCatalog;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewSensorDataEvent;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.NewSensorDataCreatedUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class NewSensorDataAMQPAdapter implements NewSensorDataEventHandler<NewSensorDataEvent> {
    private final static Logger log = LoggerFactory.getLogger(NewSensorDataAMQPAdapter.class);
    private final NewSensorDataCreatedUseCase newSensorDataCreatedUseCase;
    private final ObjectMapper objectMapper;
    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.NEW_SENSOR_DATA == eventCatalog;
    }

    @Override
    public List<NewSensorDataEvent> map(String eventBody) {
        try {
            return objectMapper.readValue(eventBody, new TypeReference<List<NewSensorDataEvent>>() {});
        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
            return new ArrayList<>();
        }
    }


    @Override
    public void handle(List<NewSensorDataEvent> responseEventBodies) {
        responseEventBodies.forEach(newSensorDataCreatedUseCase::createNewData);
    }
}
