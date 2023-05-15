package com.orgi.sample.choreography.inventory.inventory.service;

import com.orgi.sample.choreography.choreography.dto.InventoryDto;
import com.orgi.sample.choreography.choreography.events.inventory.InventoryEvent;
import com.orgi.sample.choreography.choreography.events.inventory.InventoryStatus;
import com.orgi.sample.choreography.choreography.events.order.OrderEvent;
import com.orgi.sample.choreography.inventory.inventory.entity.OrderInventoryConsumption;
import com.orgi.sample.choreography.inventory.inventory.repository.OrderInventoryConsumptionRepository;
import com.orgi.sample.choreography.inventory.inventory.repository.OrderInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final OrderInventoryRepository inventoryRepository;

    private final OrderInventoryConsumptionRepository consumptionRepository;

    @Transactional
    public InventoryEvent newOrderInventory(OrderEvent orderEvent){
        InventoryDto dto = InventoryDto.of(orderEvent.getPurchaseOrder().getOrderId(), orderEvent.getPurchaseOrder().getProductId());
        return inventoryRepository.findById(orderEvent.getPurchaseOrder().getProductId())
                .filter(i -> i.getAvailableInventory() > 0 )
                .map(i -> {
                    i.setAvailableInventory(i.getAvailableInventory() - 1);
                    consumptionRepository.save(OrderInventoryConsumption.of(orderEvent.getPurchaseOrder().getOrderId(), orderEvent.getPurchaseOrder().getProductId(), 1));
                    return new InventoryEvent(dto, InventoryStatus.RESERVED);
//                    return new InventoryEvent(dto, InventoryStatus.REJECTED);
                })
                .orElse(new InventoryEvent(dto, InventoryStatus.REJECTED));
    }

    @Transactional
    public void cancelOrderInventory(OrderEvent orderEvent){
        consumptionRepository.findById(orderEvent.getPurchaseOrder().getOrderId())
                .ifPresent(ci -> {
                    inventoryRepository.findById(ci.getProductId())
                            .ifPresent(i ->
                                i.setAvailableInventory(i.getAvailableInventory() + ci.getQuantityConsumed())
                            );
                    consumptionRepository.delete(ci);
                });
    }

}
