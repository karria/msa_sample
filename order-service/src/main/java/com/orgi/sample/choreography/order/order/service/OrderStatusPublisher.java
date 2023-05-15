package com.orgi.sample.choreography.order.order.service;

import com.orgi.sample.choreography.choreography.dto.PurchaseOrderDto;
import com.orgi.sample.choreography.choreography.events.order.OrderEvent;
import com.orgi.sample.choreography.choreography.events.order.OrderStatus;
import com.orgi.sample.choreography.order.order.entity.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@RequiredArgsConstructor
@Service
public class OrderStatusPublisher {

    private final Sinks.Many<OrderEvent> orderSink;

    public void raiseOrderEvent(final PurchaseOrder purchaseOrder, OrderStatus orderStatus){
        var dto = PurchaseOrderDto.of(
                purchaseOrder.getId(),
                purchaseOrder.getProductId(),
                purchaseOrder.getPrice(),
                purchaseOrder.getUserId()
        );
        var orderEvent = new OrderEvent(dto, orderStatus);
        this.orderSink.tryEmitNext(orderEvent);
    }

}
