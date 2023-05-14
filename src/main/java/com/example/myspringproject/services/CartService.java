package com.example.myspringproject.services;

import com.example.myspringproject.models.Cart;
import com.example.myspringproject.repositories.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public Cart findByPersonId(int id){
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElse(null);
    }

    @Transactional
    public void deleteCartByProductId(int id){
        cartRepository.deleteById(id);
    }

}
