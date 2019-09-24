package com.example.springboot.gcp.services;

import com.example.springboot.gcp.model.Order;
import com.example.springboot.gcp.web.controllers.OrdersPaymentsController;
import com.example.springboot.gcp.web.controllers.OrdersPubSubController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class HashMapInMemoryOrdersDbService {

    private final OrdersPaymentsController ordersPaymentsController;
    private final OrdersPubSubController ordersPubSubController;
    private Map<String, Order> ordersMap = new HashMap();


    public HashMapInMemoryOrdersDbService(OrdersPaymentsController ordersPaymentsController, OrdersPubSubController ordersPubSubController) {
        this.ordersPaymentsController = ordersPaymentsController;
        this.ordersPubSubController = ordersPubSubController;
    }

    public boolean deleteAllOrders() {
        log.info("Deleting all orders");
        ordersMap.clear();
        return true;
    }

    public Iterable<Order> getAllOrders() {
        log.info("Listing all orders from the DB");
        return ordersMap.values();
    }

    public Order findOrder(String orderId) {
        log.info("Getting Order ID: {}", orderId);
        return ordersMap.get(orderId);
    }

    public void deleteOrder(String orderId) {
        log.info("Deleting Order ID: {}", orderId);
        ordersMap.remove(orderId);
    }


    public String createOrder(Order order) {
        String orderId = UUID.randomUUID().toString();
        order.setId(UUID.randomUUID().toString());
        order.setFirstName(order.getFirstName());
        order.setLastName(order.getLastName());
        order.setCustomerId(order.getCustomerId());
        order.setOrderDate(order.getOrderDate());
        log.info("Adding Order ID: {}", orderId);
        ordersMap.put(orderId, order);
        return orderId;
    }


}
