// RabbitMqConfig.java
// Spring AMQP configuration for the AI Service's RabbitMQ messaging.
// Defines the queue, exchange, binding, and message converter for receiving activity events from the Activity Service.
package com.fitness.aiservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // = "Marks this class as a source of bean definitions — Spring processes it during startup to create the declared beans"
public class RabbitMqConfig {

    // Queue name, exchange name, and routing key all injected from application configuration (properties/yml).
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean // = "Declares a durable queue (true = durable) with the configured name — survives broker restarts"
    public Queue activityQueue() {
        return new Queue(queue, true);
    }

    @Bean // = "Declares a direct exchange — messages are routed to queues whose binding key exactly matches the routing key"
    public DirectExchange activityExchange() {
        return new DirectExchange(exchange);
    }

    @Bean // = "Binds the queue to the exchange with the configured routing key — connects the producer (Activity Service) to the consumer (AI Service)"
    public Binding activityBinding(Queue activityQueue, DirectExchange activityExchange) {
        return BindingBuilder.bind(activityQueue).to(activityExchange).with(routingKey);
    }


    @Bean // = "Configures JSON serialization/deserialization for messages so Activity objects are sent/received as JSON over RabbitMQ"
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}