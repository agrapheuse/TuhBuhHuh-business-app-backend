package be.kdg.integration5.business_app.business_app_backend.adapters.out.db;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.MeasurementJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.MeasurementJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;
import be.kdg.integration5.business_app.business_app_backend.ports.out.sensors.NewSensorDataCreatePort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MeasurementDBAdapter implements NewSensorDataCreatePort {
    private final static Logger log = LoggerFactory.getLogger(MeasurementDBAdapter.class);
    private final MeasurementJpaEntityRepository measurementJpaEntityRepository;

    @Override
    public void newMeasurement(Measurement measurement) {
        measurementJpaEntityRepository.save(new MeasurementJpaEntity(measurement));
        log.info("Received response data: {}", measurement);
    }
}
