package com.mindhub.order_microservice.services;

import com.mindhub.order_microservice.dtos.get.GetOrderItemDTO;
import com.mindhub.order_microservice.dtos.post.PostOrderItemDTO;
import com.mindhub.order_microservice.dtos.update.UpdateOrderItemDTO;
import com.mindhub.order_microservice.exceptions.GenericException;
import com.mindhub.order_microservice.models.OrderItemModel;

import java.util.List;

public interface OrderItemService {
    GetOrderItemDTO create(PostOrderItemDTO orderItem) throws GenericException;

    OrderItemModel save(OrderItemModel orderItem) throws GenericException;

    List<OrderItemModel> getAll() throws GenericException;

    OrderItemModel getById(Long id) throws GenericException;

    GetOrderItemDTO getDTOById(Long id) throws GenericException;

    GetOrderItemDTO update(Long id, UpdateOrderItemDTO orderItem) throws GenericException;

    void delete(Long id) throws GenericException;
}
