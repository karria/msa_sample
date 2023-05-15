package com.orgi.sample.choreography.choreography.events.order;

import com.orgi.sample.choreography.choreography.dto.PurchaseOrderDto;
import com.orgi.sample.choreography.choreography.events.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private PurchaseOrderDto purchaseOrder;
    private OrderStatus orderStatus;

    public OrderEvent(PurchaseOrderDto purchaseOrder, OrderStatus orderStatus) {
        this.purchaseOrder = purchaseOrder;
        this.orderStatus = orderStatus;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public PurchaseOrderDto getPurchaseOrder() {
        return purchaseOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
