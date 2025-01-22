package com.mindhub.order_microservice.dtos.post;

import com.mindhub.order_microservice.models.OrderModel;

public record PostOrderItemDTO(Long productId, Integer quantity, OrderModel orderModel) {
}
