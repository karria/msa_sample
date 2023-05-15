package com.orgi.sample.choreography.order.order.service;

import com.orgi.sample.choreography.order.order.entity.PurchaseOrder;
import com.orgi.sample.choreography.order.order.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> getAll() {
        return this.purchaseOrderRepository.findAll();
    }

}
