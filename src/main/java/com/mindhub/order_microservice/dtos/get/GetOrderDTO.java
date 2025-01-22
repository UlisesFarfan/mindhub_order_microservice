package com.mindhub.order_microservice.dtos.get;

import com.mindhub.order_microservice.models.OrderModel;
import com.mindhub.order_microservice.models.StatusType;

public class GetOrderDTO {

    private final Long id;

    private final Long userId;

    private final StatusType status;

    public GetOrderDTO(OrderModel order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.status = order.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public StatusType getStatus() {
        return status;
    }
}
