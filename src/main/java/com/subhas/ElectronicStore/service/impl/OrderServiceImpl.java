package com.subhas.ElectronicStore.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.subhas.ElectronicStore.dto.CreateOrderRequest;
import com.subhas.ElectronicStore.dto.OrderDto;
import com.subhas.ElectronicStore.entity.Cart;
import com.subhas.ElectronicStore.entity.CartItem;
import com.subhas.ElectronicStore.entity.Order;
import com.subhas.ElectronicStore.entity.OrderItem;
import com.subhas.ElectronicStore.entity.User;
import com.subhas.ElectronicStore.exception.BadApiRequest;
import com.subhas.ElectronicStore.exception.ResourceNotFoundException;
import com.subhas.ElectronicStore.helper.Helper;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.repository.CartRepository;
import com.subhas.ElectronicStore.repository.OrderRepository;
import com.subhas.ElectronicStore.repository.UserRepository;
import com.subhas.ElectronicStore.service.OrderService;
@Service 
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
    public OrderDto createOrder(CreateOrderRequest orderDto) {
        String userId = orderDto.getUserId();
        String cartId = orderDto.getCartId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found with Given ID"));
        // fetch cart
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not Found with Given ID"));

       List<CartItem> cartItems = cart.getItems();

       if(cartItems.size() <= 0){
        throw new BadApiRequest("Invalid no of Items in Cart !!!");
       }

       Order order = Order.builder()
                    .billingName(orderDto.getBillingName())
                    .billingAddress(orderDto.getBillingAddress())
                    .billingPhoneNo(orderDto.getBillingPhoneNo())
                    .orderedDate(new Date())
                    .deliveryDate(null)
                    .paymentStatus(orderDto.getPaymentStatus())
                    .orderStatus(orderDto.getOrderStatus())
                    .orderId(UUID.randomUUID().toString())
                    .user(user)
                    .build();
                    // orderItems, Amount
        AtomicReference<Integer> orderAmount = new AtomicReference<>(0);
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            // CartItem -> orderItem
            OrderItem orderItem = OrderItem.builder()
                        .quantinty(cartItem.getQuantity())
                        .product(cartItem.getProduct())
                        .totalPrice(cartItem.getQuantity() * cartItem.getProduct().getDiscountedPrice())
                        // .totalPrice(cartItem.getTotalPrice())
                        .order(order)
                        .build();
                orderAmount.set(orderAmount.get() + orderItem.getTotalPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setOrderAmount(orderAmount.get());

        cart.getItems().clear();

        cartRepository.save(cart);
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public void removeOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found by given Id"));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getOrdersOfUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found with Given ID"));
        List<Order> orders = orderRepository.findByUser(user);
       List<OrderDto> orderDto = orders.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return orderDto;
    }

    @Override
    public PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Order> page = orderRepository.findAll(pageable);
        return Helper.getPageableResponse(page, OrderDto.class);

    }

}
