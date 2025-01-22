package com.mindhub.order_microservice.dtos.get;

import com.mindhub.order_microservice.models.OrderItemModel;

public class GetOrderItemDTO {
    private final Long id;

    private final Long productId;

    private final Integer quantity;

    public GetOrderItemDTO(OrderItemModel orderItem) {
        this.id = orderItem.getId();
        this.productId = orderItem.getProductId();
        this.quantity = orderItem.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
