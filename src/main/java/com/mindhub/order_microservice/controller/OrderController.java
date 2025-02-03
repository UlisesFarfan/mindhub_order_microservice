package com.mindhub.order_microservice.controller;

import com.mindhub.order_microservice.dtos.get.GetOrderDTO;
import com.mindhub.order_microservice.dtos.get.GetOrderWithContentDTO;
import com.mindhub.order_microservice.dtos.post.PostOrderDTO;
import com.mindhub.order_microservice.dtos.update.UpdateOrderDTO;
import com.mindhub.order_microservice.services.AppService;
import com.mindhub.order_microservice.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AppService appService;

    @GetMapping("/")
    public ResponseEntity<List<GetOrderDTO>> getAll() {
        List<GetOrderDTO> orderDTOS = orderService.getAll()
                .stream()
                .map(GetOrderDTO::new)
                .toList();
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<GetOrderDTO> create(HttpServletRequest request, @RequestBody PostOrderDTO order){
        if (order.userId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = appService.extraerToken(request);
        return new ResponseEntity<>(orderService.create(order, token), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetOrderDTO> update(@RequestBody UpdateOrderDTO order, @PathVariable long id){
        if (order.userId() == null || order.status() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.update(id, order), HttpStatus.OK);
    }

    @PutMapping("/complete_order/{id}")
    public ResponseEntity<GetOrderDTO> completeOrder(HttpServletRequest request, @PathVariable long id) {
        String token = appService.extraerToken(request);
        GetOrderWithContentDTO orderWithContentDTO = orderService.completeOrder(id, token);
        return new ResponseEntity<>(orderWithContentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
