package be.kdg.integration5.business_app.business_app_backend.adapters.in;

import be.kdg.integration5.business_app.business_app_backend.adapters.config.RabbitMQTopology;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.Event;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.EventMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class RabbitEventHandler {
    private final static Logger log = LoggerFactory.getLogger(RabbitEventHandler.class);
    private final List<NewSensorDataEventHandler<? extends Event>> newSensorDataEventHandlers;
    private final List<NewPredictionEventHandler<? extends Event>> newPredictionEventHandlers;

    @RabbitListener(queues = RabbitMQTopology.NEW_DATA_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveSensorDataEventMessage(EventMessage eventMessage) {
        newSensorDataEventHandlers.stream()
                .filter(newSensorDataEventHandler ->
                        newSensorDataEventHandler.appliesTo(eventMessage.getEventHeader().getEventCatalog())
                )
                .forEach(newSensorDataEventHandler ->
                        newSensorDataEventHandler.receive(eventMessage).handle(
                                newSensorDataEventHandler.map(eventMessage.getEventBody())
                        )
                );
    }

    @RabbitListener(queues = RabbitMQTopology.NEW_PREDICTION_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receivePredictionEventMessage(EventMessage eventMessage) {
        newPredictionEventHandlers.stream()
                .filter(newPredictionEventHandler ->
                        newPredictionEventHandler.appliesTo(eventMessage.getEventHeader().getEventCatalog())
                )
                .forEach(newPredictionEventHandler ->
                        newPredictionEventHandler.receive(eventMessage).handle(
                                newPredictionEventHandler.map(eventMessage.getEventBody())
                        )
                );
    }

}

