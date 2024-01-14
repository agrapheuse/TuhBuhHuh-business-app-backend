package be.kdg.integration5.business_app.business_app_backend.ports.out.sensors;

import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;

public interface NewSensorDataCreatePort {
    void newMeasurement(Measurement measurement);
}
