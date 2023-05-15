package com.orgi.sample.choreography.order.order.controller;

import com.orgi.sample.choreography.choreography.dto.OrderRequestDto;
import com.orgi.sample.choreography.order.order.entity.PurchaseOrder;
import com.orgi.sample.choreography.order.order.service.OrderCommandService;
import com.orgi.sample.choreography.order.order.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderCommandService commandService;

    private final OrderQueryService queryService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto requestDTO){
        requestDTO.setOrderId(UUID.randomUUID());
        return this.commandService.createOrder(requestDTO);
    }

    @GetMapping("/all")
    public List<PurchaseOrder> getOrders(){
        return this.queryService.getAll();
    }

}
