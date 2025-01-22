package com.mindhub.order_microservice.repositories;

import com.mindhub.order_microservice.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
