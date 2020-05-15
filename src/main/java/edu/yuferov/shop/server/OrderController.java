package edu.yuferov.shop.server;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private List<Order> orders;

    @PostMapping
    int createOrder(@RequestBody Order order) {
        orders.add(order);
        return orders.size();
    }

    @GetMapping
    List<Order> getOrders() {
        return orders;
    }

}
