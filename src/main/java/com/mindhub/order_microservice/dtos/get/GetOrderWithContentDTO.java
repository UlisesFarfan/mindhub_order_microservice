package com.mindhub.order_microservice.dtos.get;

import com.mindhub.order_microservice.models.OrderModel;

import java.util.List;

public class GetOrderWithContentDTO extends GetOrderDTO {

    private final List<GetOrderItemDTO> products;

    public GetOrderWithContentDTO(OrderModel order) {
        super(order);
        this.products = order.getProducts()
                .stream()
                .map(GetOrderItemDTO::new)
                .toList();
    }

    public List<GetOrderItemDTO> getProducts() {
        return products;
    }
}
