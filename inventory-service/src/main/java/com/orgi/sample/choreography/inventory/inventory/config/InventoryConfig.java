package com.orgi.sample.choreography.inventory.inventory.config;

import com.orgi.sample.choreography.choreography.events.inventory.InventoryEvent;
import com.orgi.sample.choreography.choreography.events.order.OrderEvent;
import com.orgi.sample.choreography.choreography.events.order.OrderStatus;
import com.orgi.sample.choreography.inventory.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
@Configuration
public class InventoryConfig {

    private final InventoryService service;

    @Bean
    public Function<Flux<OrderEvent>, Flux<InventoryEvent>> inventoryProcessor() {
        return flux -> flux.flatMap(this::processInventory);
    }

    private Mono<InventoryEvent> processInventory(OrderEvent event){
        if(event.getOrderStatus().equals(OrderStatus.ORDER_CREATED)){
            return Mono.fromSupplier(() -> this.service.newOrderInventory(event));
        }
        return Mono.fromRunnable(() -> this.service.cancelOrderInventory(event));
    }

}

