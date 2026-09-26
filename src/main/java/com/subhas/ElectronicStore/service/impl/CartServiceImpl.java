package com.subhas.ElectronicStore.service.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.subhas.ElectronicStore.dto.AddItemToCartRequest;
import com.subhas.ElectronicStore.dto.CartDto;
import com.subhas.ElectronicStore.entity.Cart;
import com.subhas.ElectronicStore.entity.CartItem;
import com.subhas.ElectronicStore.entity.Product;
import com.subhas.ElectronicStore.entity.User;
import com.subhas.ElectronicStore.exception.BadApiRequest;
import com.subhas.ElectronicStore.exception.ResourceNotFoundException;
import com.subhas.ElectronicStore.repository.CartItemRepository;
import com.subhas.ElectronicStore.repository.CartRepository;
import com.subhas.ElectronicStore.repository.ProductRepository;
import com.subhas.ElectronicStore.repository.UserRepository;
import com.subhas.ElectronicStore.service.CartService;

public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private  final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    public CartServiceImpl (CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository, CartItemRepository cartItemRepository, ModelMapper mapper){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.mapper = mapper;

    }

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest addItemRequest) {
        // get product information
        String productId = addItemRequest.getProductId();
        int quantity = addItemRequest.getQuantity();

        if(quantity<=0){
            throw new BadApiRequest("Requested quantinty is not valid");
        }

        // fetch the user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found by this id !!"));
        // fetch the product
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found by this id !!"));
        // find user cart
        Cart cart = null;
        try{
            //cart exists
            cart = cartRepository.findByUser(user).get();
        }catch(NoSuchElementException e){
            //if not create new
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }

        // perform cart operation
        // if cart item already present then update
        // boolean updated = false;
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        //check cart items if exixts
        List<CartItem> items = cart.getItems();

        List<CartItem> updatedItems = items.stream().map(item -> {

            if(item.getProduct().getProductId().equals(productId)){
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getPrice());
                updated.set(true);
            }
            return item;

        }).collect(Collectors.toList());

        cart.setItems(updatedItems);

        // for new cart create items
        if(!updated.get()){
            CartItem cartItem = CartItem.builder().quantity(quantity).totalPrice(quantity*product.getPrice())
                            .cart(cart).product(product).build();
            cart.getItems().add(cartItem);
        }

        cart.setUser(user);
        cartRepository.save(cart);

        return mapper.map(cart, CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {
        
        CartItem items = cartItemRepository.findById(cartItem).orElseThrow(()-> new ResourceNotFoundException("Cart item not found"));
        cartItemRepository.delete(items);
    }

    @Override
    public void clearCart(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found by this id !!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Cart of given user not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found by this id !!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Cart of given user not found"));
        return mapper.map(cart, CartDto.class);
    }
    

}
