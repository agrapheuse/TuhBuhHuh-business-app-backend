package be.kdg.integration5.business_app.business_app_backend.adapters.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitMQConfig {
    @Bean
    @Profile({"remote-dev", "prod"})
    CachingConnectionFactory createConnectionFactory() {
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory(System.getenv("RABBIT_MQ_HOST"));
        connectionFactory.setUsername(System.getenv("RABBIT_MQ_DEFAULT_USER"));
        connectionFactory.setPassword(System.getenv("RABBIT_MQ_DEFAULT_PASS"));
        connectionFactory.setVirtualHost(System.getenv("RABBIT_MQ_VIRTUAL_HOST"));
        //Recommended settings
        connectionFactory.setRequestedHeartBeat(30);
        connectionFactory.setConnectionTimeout(30000);
        return connectionFactory;
    }
    @Bean
    @Profile({"remote-dev", "prod"})
    RabbitTemplate remoteRabbitTemplate(CachingConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter(objectMapper));
        return rabbitTemplate;
    }
    @Bean
    @Profile("local-dev")
    RabbitTemplate localRabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter(objectMapper));
        return rabbitTemplate;
    }
    @Bean
    Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper mapper) {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }
}
