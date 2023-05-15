package com.orgi.sample.choreography.payment.payment.repository;

import com.orgi.sample.choreography.payment.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}
