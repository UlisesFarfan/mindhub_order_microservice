package com.mindhub.order_microservice.dtos.update;

import com.mindhub.order_microservice.models.StatusType;

public record UpdateOrderDTO(Long userId,StatusType status) {
}
