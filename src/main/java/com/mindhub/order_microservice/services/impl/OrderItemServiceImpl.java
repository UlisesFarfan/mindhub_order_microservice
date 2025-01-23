package com.mindhub.order_microservice.services.impl;

import com.mindhub.order_microservice.dtos.get.GetOrderItemDTO;
import com.mindhub.order_microservice.dtos.post.PostOrderItemDTO;
import com.mindhub.order_microservice.dtos.update.UpdateOrderItemDTO;
import com.mindhub.order_microservice.exceptions.GenericException;
import com.mindhub.order_microservice.models.OrderItemModel;
import com.mindhub.order_microservice.repositories.OrderItemRepository;
import com.mindhub.order_microservice.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public GetOrderItemDTO create(PostOrderItemDTO orderItem) throws GenericException {
        try {
            String url = "http://localhost:8082/api/products/" + orderItem.productId();
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
            Map responseBody = responseEntity.getBody();
            assert responseBody != null;
            Integer stock = (Integer) responseBody.get("stock");
            if(stock < orderItem.quantity()) {
                throw new GenericException("insufficient stock");
            } else {
                String purl = "http://localhost:8082/api/products/stock/" + orderItem.productId() + "?quantity=" + orderItem.quantity();
                restTemplate.exchange(purl, HttpMethod.PUT, null, Map.class);
            }
            OrderItemModel productModel = new OrderItemModel(orderItem.productId(), orderItem.quantity(), orderItem.orderModel());
            OrderItemModel savedProduct = orderItemRepository.save(productModel);
            return new GetOrderItemDTO(savedProduct);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public OrderItemModel save(OrderItemModel orderItem) throws GenericException {
        try {
            return orderItemRepository.save(orderItem);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public List<OrderItemModel> getAll() throws GenericException {
        try {
            return orderItemRepository.findAll();
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public OrderItemModel getById(Long id) throws GenericException {
        return orderItemRepository.findById(id).orElseThrow(() -> new GenericException("order item not found"));
    }

    @Override
    public GetOrderItemDTO getDTOById(Long id) throws GenericException {
        try {
            return new GetOrderItemDTO(getById(id));
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public GetOrderItemDTO update(Long id, UpdateOrderItemDTO orderItem) throws GenericException {
        try {
            OrderItemModel orderItemModel = orderItemRepository.findById(id)
                    .orElseThrow(() -> new GenericException("order item not found"));
            orderItemModel.setQuantity(orderItem.quantity());
            orderItemModel.setProductId(orderItem.productId());
            orderItemModel = orderItemRepository.save(orderItemModel);
            return new GetOrderItemDTO(orderItemModel);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws GenericException {
        try {
            orderItemRepository.deleteById(id);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }
}
