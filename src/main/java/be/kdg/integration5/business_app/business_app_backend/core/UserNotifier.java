package be.kdg.integration5.business_app.business_app_backend.core;

import be.kdg.integration5.business_app.business_app_backend.core.usecases.DefaultUserNotifyUseCase;
import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;
import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserNotifyUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.out.prediction.NewPredictionCreatePort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.sensors.NewSensorDataCreatePort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.user.UserLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserNotifier implements NewSensorDataCreatePort, NewPredictionCreatePort {

    private final UserNotifyUseCase userNotifyUseCase;
    private final UserLoadPort userLoadPort;
    @Override
    public void newMeasurement(Measurement measurement) {

        userNotifyUseCase.notifyUsers(userLoadPort.findAll(), measurement);
    }

    @Override
    public void newPrediction(Prediction prediction) {
        userNotifyUseCase.notifyUsers(userLoadPort.findAll(), prediction);
    }
}
