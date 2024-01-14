package be.kdg.integration5.business_app.business_app_backend.adapters.out.db;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.PredictionJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.PredictionJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;
import be.kdg.integration5.business_app.business_app_backend.ports.out.prediction.NewPredictionCreatePort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PredictionDBAdapter implements NewPredictionCreatePort {
    private final static Logger log = LoggerFactory.getLogger(PredictionDBAdapter.class);
    private final PredictionJpaEntityRepository predictionJpaEntityRepository;

    @Override
    public void newPrediction(Prediction prediction) {
        predictionJpaEntityRepository.save(new PredictionJpaEntity(prediction));
    }
}
