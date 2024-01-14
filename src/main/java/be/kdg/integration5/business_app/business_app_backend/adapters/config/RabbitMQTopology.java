package be.kdg.integration5.business_app.business_app_backend.adapters.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {
    public static final String NEW_DATA_QUEUE = "new_data_queue";
    public static final String NEW_PREDICTION_QUEUE = "new_prediction_queue";

    @Bean
    Queue sensorDataEventsQueue() {
        return new Queue(NEW_DATA_QUEUE, true);
    }

    @Bean
    Queue predictionEventsQueue() {
        return new Queue(NEW_PREDICTION_QUEUE, true);
    }

}
