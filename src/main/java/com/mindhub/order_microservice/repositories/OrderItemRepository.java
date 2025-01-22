package com.mindhub.order_microservice.repositories;

import com.mindhub.order_microservice.models.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemModel, Long> {
}
