package com.orgi.sample.choreography.order.order.config;

import com.orgi.sample.choreography.choreography.events.order.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderConfig {

    @Bean
        public Sinks.Many<OrderEvent> reu(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sink){
        return sink::asFlux;
    }

}
