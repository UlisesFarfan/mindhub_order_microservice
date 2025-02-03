package com.mindhub.order_microservice.controller;

import com.mindhub.order_microservice.dtos.get.GetOrderItemDTO;
import com.mindhub.order_microservice.dtos.post.PostOrderItemDTO;
import com.mindhub.order_microservice.dtos.update.UpdateOrderItemDTO;
import com.mindhub.order_microservice.services.AppService;
import com.mindhub.order_microservice.services.OrderItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order_item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private AppService appService;

    @GetMapping("/")
    public ResponseEntity<List<GetOrderItemDTO>> getAll() {
        List<GetOrderItemDTO> productDTO = orderItemService.getAll()
                .stream()
                .map(GetOrderItemDTO::new)
                .toList();
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<GetOrderItemDTO> create(HttpServletRequest request,@RequestBody PostOrderItemDTO orderItem){
        if (orderItem.productId() == null || orderItem.quantity() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = appService.extraerToken(request);
        return new ResponseEntity<>(orderItemService.create(orderItem, token), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetOrderItemDTO> update(@RequestBody UpdateOrderItemDTO orderItem, @PathVariable long id){
        if (orderItem.productId() == null || orderItem.quantity() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderItemService.update(id, orderItem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
