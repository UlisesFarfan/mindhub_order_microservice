package com.mindhub.order_microservice.services.impl;

import com.mindhub.order_microservice.dtos.get.GetOrderDTO;
import com.mindhub.order_microservice.dtos.post.PostOrderDTO;
import com.mindhub.order_microservice.dtos.update.UpdateOrderDTO;
import com.mindhub.order_microservice.exceptions.GenericException;
import com.mindhub.order_microservice.models.OrderModel;
import com.mindhub.order_microservice.repositories.OrderRepository;
import com.mindhub.order_microservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public GetOrderDTO create(PostOrderDTO order) throws GenericException {
        try {
            OrderModel orderModel = new OrderModel(order.userId());
            OrderModel savedOrder = orderRepository.save(orderModel);
            return new GetOrderDTO(savedOrder);
        } catch (Exception e) {
            throw new GenericException("something went wrong");
        }
    }

    @Override
    public OrderModel save(OrderModel order) throws GenericException {
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new GenericException("something went wrong");
        }
    }

    @Override
    public List<OrderModel> getAll() throws GenericException {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new GenericException("something went wrong");
        }
    }

    @Override
    public OrderModel getById(Long id) throws GenericException {
        return orderRepository.findById(id).orElseThrow(() -> new GenericException("order not found"));
    }

    @Override
    public GetOrderDTO getDTOById(Long id) throws GenericException {
        try {
            return new GetOrderDTO(getById(id));
        } catch (Exception e) {
            throw new GenericException("something went wrong");
        }
    }

    @Override
    public GetOrderDTO update(Long id, UpdateOrderDTO order) throws GenericException {
        try {
            OrderModel orderModel = orderRepository.findById(id)
                    .orElseThrow(() -> new GenericException("order not found"));
            orderModel.setStatus(order.status());
            orderModel.setUserId(order.userId());
            orderModel = orderRepository.save(orderModel);
            return new GetOrderDTO(orderModel);
        } catch (Exception e) {
            throw new GenericException("something went wrong");
        }
    }

    @Override
    public void delete(Long id) throws GenericException {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new GenericException("something went wrong");
        }
    }
}
