package com.orgi.sample.choreography.order.order.config;

import com.orgi.sample.choreography.choreography.events.inventory.InventoryStatus;
import com.orgi.sample.choreography.choreography.events.order.OrderStatus;
import com.orgi.sample.choreography.choreography.events.payment.PaymentStatus;
import com.orgi.sample.choreography.order.order.entity.PurchaseOrder;
import com.orgi.sample.choreography.order.order.service.OrderStatusPublisher;
import com.orgi.sample.choreography.order.order.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class OrderStatusUpdateEventHandler {

    private final PurchaseOrderRepository repository;

    private final OrderStatusPublisher publisher;

    @Transactional
    public void updateOrder(final UUID id, Consumer<PurchaseOrder> consumer){
        this.repository
                .findById(id)
                .ifPresent(consumer.andThen(this::updateOrder));

    }

    private void updateOrder(PurchaseOrder purchaseOrder){
        if(Objects.isNull(purchaseOrder.getInventoryStatus()) || Objects.isNull(purchaseOrder.getPaymentStatus()))
            return;
        var isComplete = PaymentStatus.RESERVED.equals(purchaseOrder.getPaymentStatus()) && InventoryStatus.RESERVED.equals(purchaseOrder.getInventoryStatus());
        var orderStatus = isComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isComplete){
            this.publisher.raiseOrderEvent(purchaseOrder, orderStatus);
        }
    }

}
