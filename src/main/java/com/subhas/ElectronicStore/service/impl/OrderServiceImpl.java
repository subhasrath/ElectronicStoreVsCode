package com.subhas.ElectronicStore.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.subhas.ElectronicStore.dto.OrderDto;
import com.subhas.ElectronicStore.entity.Cart;
import com.subhas.ElectronicStore.entity.User;
import com.subhas.ElectronicStore.exception.ResourceNotFoundException;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.repository.CartRepository;
import com.subhas.ElectronicStore.repository.OrderRepository;
import com.subhas.ElectronicStore.repository.UserRepository;
import com.subhas.ElectronicStore.service.OrderService;

public class OrderServiceImpl implements OrderService{
    private final UserRepository userRepository;

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ModelMapper mapper;

    public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository, CartRepository cartRepository, ModelMapper mapper){
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.mapper = mapper;
        
    }
    @Override
    public OrderDto createOrder(OrderDto orderDto, String userId, String cartId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found with Given ID"));
        // fetch cart
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not Found with Given ID"));

        return null;
    }

    @Override
    public void removeOrder(String orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOrder'");
    }

    @Override
    public List<OrderDto> getOrdersOfUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrdersOfUser'");
    }

    @Override
    public PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrders'");
    }

}
