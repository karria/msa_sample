package com.orgi.sample.choreography.order.order.service;

import com.orgi.sample.choreography.choreography.dto.OrderRequestDto;
import com.orgi.sample.choreography.choreography.events.order.OrderStatus;
import com.orgi.sample.choreography.order.order.entity.PurchaseOrder;
import com.orgi.sample.choreography.order.order.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OrderCommandService {

    private final Map<Integer, Integer> productPriceMap;

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final OrderStatusPublisher publisher;

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDTO){
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.save(this.dtoToEntity(orderRequestDTO));
        this.publisher.raiseOrderEvent(purchaseOrder, OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }

    private PurchaseOrder dtoToEntity(final OrderRequestDto dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(dto.getOrderId());
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(productPriceMap.get(purchaseOrder.getProductId()));
        return purchaseOrder;
    }

}
