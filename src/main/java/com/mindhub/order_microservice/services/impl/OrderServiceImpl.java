package com.mindhub.order_microservice.services.impl;

import com.mindhub.order_microservice.dtos.get.GetOrderDTO;
import com.mindhub.order_microservice.dtos.post.PostOrderDTO;
import com.mindhub.order_microservice.dtos.update.UpdateOrderDTO;
import com.mindhub.order_microservice.exceptions.GenericException;
import com.mindhub.order_microservice.models.OrderModel;
import com.mindhub.order_microservice.repositories.OrderRepository;
import com.mindhub.order_microservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public GetOrderDTO create(PostOrderDTO order) throws GenericException {
        try {
            String url = "http://localhost:8081/api/users/" + order.userId();
            restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
            OrderModel orderModel = new OrderModel(order.userId());
            OrderModel savedOrder = orderRepository.save(orderModel);
            return new GetOrderDTO(savedOrder);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public OrderModel save(OrderModel order) throws GenericException {
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public List<OrderModel> getAll() throws GenericException {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
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
            throw new GenericException(e.getMessage());
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
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws GenericException {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }
}
