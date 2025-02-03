package com.mindhub.order_microservice.services;

import com.mindhub.order_microservice.dtos.get.GetOrderDTO;
import com.mindhub.order_microservice.dtos.get.GetOrderWithContentDTO;
import com.mindhub.order_microservice.dtos.post.PostOrderDTO;
import com.mindhub.order_microservice.dtos.update.UpdateOrderDTO;
import com.mindhub.order_microservice.exceptions.GenericException;
import com.mindhub.order_microservice.models.OrderModel;

import java.util.List;

public interface OrderService {
    GetOrderDTO create(PostOrderDTO order, String token) throws GenericException;

    OrderModel save(OrderModel order) throws GenericException;

    List<OrderModel> getAll() throws GenericException;

    OrderModel getById(Long id) throws GenericException;

    GetOrderDTO getDTOById(Long id) throws GenericException;

    GetOrderDTO update(Long id, UpdateOrderDTO order) throws GenericException;

    GetOrderWithContentDTO completeOrder(Long id, String token) throws GenericException;

    void delete(Long id) throws GenericException;
}
