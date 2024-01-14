package be.kdg.integration5.business_app.business_app_backend.seeding;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.LocationJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.MeasurementJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.PredictionJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.UserJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.LocationJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.MeasurementJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.PredictionJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.UserJPAEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import com.google.maps.model.LatLng;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class DatabaseSeeder {
    private final LocationJpaEntityRepository locationJpaEntityRepository;
    private final UserJPAEntityRepository userJPAEntityRepository;
    private final MeasurementJpaEntityRepository measurementJpaEntityRepository;
    private final PredictionJpaEntityRepository predictionJpaEntityRepository;

    void createData() {
        Map thresholds = new HashMap<String, Double>();
        thresholds.put("PM10", 25.);
        thresholds.put("PM2.5", 15.);
        thresholds.put("OZONE", 35.);
//        User user = new User(
//                new User.UserUUID("da3cc321-9b1e-48c5-92fa-56896a70fa5e"),
//                "Henry",
//                "Bley",
//                new ArrayList<>(),
//                thresholds
//        );

//        userJPAEntityRepository.save(new UserJpaEntity(user));

        LocationJpaEntity locationJpaEntity = locationJpaEntityRepository.findById(new Location.LocationUUID("001dd687-24cd-479d-b0ab-5a9ad5a8df1f").uuid()).orElse(null);
        if(locationJpaEntity != null) {
            measurementJpaEntityRepository.save(new MeasurementJpaEntity(
                    UUID.randomUUID(),
                    locationJpaEntity,
                    ValueType.PM10,
                    LocalDateTime.now(),
                    (float) 12.5
            ));
            measurementJpaEntityRepository.save(new MeasurementJpaEntity(
                    UUID.randomUUID(),
                    locationJpaEntity,
                    ValueType.PM25,
                    LocalDateTime.now(),
                    (float) 15.5
            ));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusHours(3),
                    LocalDateTime.now(),
                    ValueType.PM25,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now(),
                    ValueType.PM25,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusDays(3),
                    LocalDateTime.now(),
                    ValueType.PM25,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusHours(3),
                    LocalDateTime.now(),
                    ValueType.PM10,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now(),
                    ValueType.PM10,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusDays(3),
                    LocalDateTime.now(),
                    ValueType.PM10,
                    (float) 10.5
            )));
        }
        locationJpaEntity = locationJpaEntityRepository.findById(new Location.LocationUUID("00e11cfc-64d0-4e24-a8cb-ddf860e575b2").uuid()).orElse(null);
        if(locationJpaEntity != null) {
            measurementJpaEntityRepository.save(new MeasurementJpaEntity(
                    UUID.randomUUID(),
                    locationJpaEntity,
                    ValueType.PM10,
                    LocalDateTime.now(),
                    (float) 10.5
            ));
            measurementJpaEntityRepository.save(new MeasurementJpaEntity(
                    UUID.randomUUID(),
                    locationJpaEntity,
                    ValueType.PM25,
                    LocalDateTime.now(),
                    (float) 13.5
            ));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusHours(3),
                    LocalDateTime.now(),
                    ValueType.PM25,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now(),
                    ValueType.PM25,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusDays(3),
                    LocalDateTime.now(),
                    ValueType.PM25,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusHours(3),
                    LocalDateTime.now(),
                    ValueType.PM10,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now(),
                    ValueType.PM10,
                    (float) 10.5
            )));
            predictionJpaEntityRepository.save(new PredictionJpaEntity( new Prediction(
                    UUID.randomUUID(),
                    new Location(locationJpaEntity),
                    LocalDateTime.now().plusDays(3),
                    LocalDateTime.now(),
                    ValueType.PM10,
                    (float) 10.5
            )));
        }


    }
}
