package com.example.myspringproject.services;

import com.example.myspringproject.models.Order;
import com.example.myspringproject.models.Person;
import com.example.myspringproject.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    // Данный метод позволяет получить список всех
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public Order getOrderId(int id){
        Optional<Order> optionalPerson = orderRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    @Transactional
    public void editOrder(int id, Order order){
        order.setId(id);
        orderRepository.save(order);
    }

}
