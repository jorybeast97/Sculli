package com.magnoliaory.scullicommunicationserver.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue directQueue() {
        return new Queue("hyrule-modbus-message-queue", true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("hyruleExchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("SEND_MODBUS_MESSAGE");
    }

}
