package com.orgi.sample.choreography.inventory.inventory.repository;

import com.orgi.sample.choreography.inventory.inventory.entity.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {
}
