package com.mindhub.order_microservice.controller;

import com.mindhub.order_microservice.dtos.get.GetOrderDTO;
import com.mindhub.order_microservice.dtos.post.PostOrderDTO;
import com.mindhub.order_microservice.dtos.update.UpdateOrderDTO;
import com.mindhub.order_microservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<GetOrderDTO>> getAll() {
        List<GetOrderDTO> orderDTOS = orderService.getAll()
                .stream()
                .map(GetOrderDTO::new)
                .toList();
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<GetOrderDTO> create(@RequestBody PostOrderDTO order){
        if (order.userId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.create(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetOrderDTO> update(@RequestBody UpdateOrderDTO order, @PathVariable long id){
        if (order.userId() == null || order.status() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.update(id, order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
