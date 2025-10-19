package com.insulina.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "projects.topic";
    public static final String QUEUE_EMAIL = "notifications.email.queue";
    public static final String ROUTING_PATTERN = "project.#";

    @Bean
    public TopicExchange projectsExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue emailQueue() {
        return QueueBuilder.durable(QUEUE_EMAIL).build();
    }

    @Bean
    public Binding bindEmailQueue(Queue emailQueue, TopicExchange projectsExchange) {
        return BindingBuilder.bind(emailQueue).to(projectsExchange).with(ROUTING_PATTERN);
    }
}