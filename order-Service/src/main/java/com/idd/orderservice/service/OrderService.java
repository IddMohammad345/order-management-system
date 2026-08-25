package com.idd.orderservice.service;

import com.idd.orderservice.dto.OrderCreateRequest;
import com.idd.orderservice.dto.OrderResponse;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderCreateRequest order);
    OrderResponse getOrder(Long orderId);
    List<OrderResponse>getOrderByUser(Long userId);
}
