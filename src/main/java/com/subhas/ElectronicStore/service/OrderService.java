package com.subhas.ElectronicStore.service;

import java.util.List;

import com.subhas.ElectronicStore.dto.CreateOrderRequest;
import com.subhas.ElectronicStore.dto.OrderDto;
import com.subhas.ElectronicStore.payload.PageableResponse;

public interface OrderService {
    // Create Order
    OrderDto createOrder(CreateOrderRequest orderDto);

    // Remove Order
    void removeOrder(String orderId);

    // Get orders of User
    List<OrderDto> getOrdersOfUser(String userId);

    // Get Orders

    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);

    // other mothods
}
