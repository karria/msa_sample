package com.orgi.sample.choreography.order.order.entity;

import com.orgi.sample.choreography.choreography.events.inventory.InventoryStatus;
import com.orgi.sample.choreography.choreography.events.order.OrderStatus;
import com.orgi.sample.choreography.choreography.events.payment.PaymentStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.UUID;

@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    private UUID id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private InventoryStatus inventoryStatus;

    @Version
    private int version;

}