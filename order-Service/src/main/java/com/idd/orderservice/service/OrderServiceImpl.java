package com.idd.orderservice.service;

import com.idd.orderservice.client.ProductClient;
import com.idd.orderservice.client.UserClient;
import com.idd.orderservice.dto.*;
import com.idd.orderservice.entity.Order;
import com.idd.orderservice.entity.OrderItem;
import com.idd.orderservice.exception.OrderNotFoundException;
import com.idd.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    @Override
    public OrderResponse createOrder(OrderCreateRequest orderRequest) {
        //Validate User
        userClient.getUser(orderRequest.userId());
        Order order = Order.builder()
                .userId(orderRequest.userId())
                .build();
        BigDecimal totalAmount = BigDecimal.ZERO;

        //getProduct Information
       for (OrderItemRequest itemRequest: orderRequest.items()) {
           ProductResponse product = productClient.getProduct(itemRequest.productId());

           //check product status
           if (!"ACTIVE".equalsIgnoreCase(product.status())){
               throw new IllegalArgumentException(
                       "Product is not active: "+product.id()
               );
           }
           //calculate total items
           BigDecimal totalItemPrice = product.price()
                   .multiply(
                           BigDecimal.valueOf(itemRequest.quantity())
                   );

           //create Order items
           OrderItem orderItem = OrderItem.builder()
                   .productId(product.id())
                   .productName(product.name())
                   .sku(product.sku())
                   .quantity(itemRequest.quantity())
                   .unitPrice(product.price())
                   .totalPrice(totalItemPrice)
                   .build();
           order.addItem(orderItem);
           totalAmount=totalAmount.add(totalItemPrice);
       }
       //set total amount
        order.setTotalAmount(totalAmount);
       //save
        Order savedOrder = orderRepository.save(order);
        return toResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new OrderNotFoundException(
                                "Order not found" + orderId
                        )
                );
        return toResponse(order);
    }

    @Override
    public List<OrderResponse> getOrderByUser(Long userId) {
        List<OrderResponse> responses = orderRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
        return responses;
    }

    private OrderResponse toResponse(Order savedOrder) {
        List<OrderItemResponse>items=
                savedOrder.getItems()
                        .stream()
                        .map(orderItem ->
                                new OrderItemResponse(
                                        orderItem.getProductId(),
                                        orderItem.getProductName(),
                                        orderItem.getSku(),
                                        orderItem.getQuantity(),
                                        orderItem.getUnitPrice(),
                                        orderItem.getTotalPrice()
                                )).toList();
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getTotalAmount(),
                savedOrder.getOrderStatus(),
                savedOrder.getPaymentStatus(),
                savedOrder.getCreatedAt(),
                items);
    }
}
