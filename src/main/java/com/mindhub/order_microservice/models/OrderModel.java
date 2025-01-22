package com.mindhub.order_microservice.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "orderModel")
    private Set<OrderItemModel> products = new HashSet<>();

    private StatusType status = StatusType.PENDING;

    public OrderModel() {
    }

    public OrderModel(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<OrderItemModel> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderItemModel> products) {
        this.products = products;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }
}
