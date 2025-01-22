package com.mindhub.order_microservice.models;

import jakarta.persistence.*;

@Entity
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderModel orderModel;

    private Long productId;

    private Integer quantity;

    public OrderItemModel() {
    }

    public OrderItemModel(Long productId, Integer quantity, OrderModel orderModel) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderModel = orderModel;
    }

    public Long getId() {
        return id;
    }

    public OrderModel getOrder() {
        return orderModel;
    }

    public void setOrder(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
