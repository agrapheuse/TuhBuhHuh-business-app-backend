package be.kdg.integration5.business_app.business_app_backend.core.ETL;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewSensorDataEvent;
import be.kdg.integration5.business_app.business_app_backend.ports.in.projection.NewSensorDataCreatedProjector;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.NewSensorDataCreatedUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.out.sensors.NewSensorDataCreatePort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultNewSensorDataCreatedUseCase implements NewSensorDataCreatedUseCase {
    private final static Logger log = LoggerFactory.getLogger(DefaultNewSensorDataCreatedUseCase.class);
    private final NewSensorDataCreatedProjector newSensorDataCreatedProjector;
    private final List<NewSensorDataCreatePort> newSensorDataCreatePorts;
    @Override
    public void createNewData(NewSensorDataEvent event) {
        log.info("Received new sensor data: {}", event);
        var data = newSensorDataCreatedProjector.project(event);
        newSensorDataCreatePorts.forEach(p -> p.newMeasurement(data));
    }
}
