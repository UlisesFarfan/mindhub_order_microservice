package com.mindhub.order_microservice.dtos.update;

import com.mindhub.order_microservice.models.OrderModel;

public record UpdateOrderItemDTO(Long productId, Integer quantity) {
}
