package com.example.myspringproject.repositories;

import com.example.myspringproject.models.Order;
import com.example.myspringproject.models.Person;
import com.example.myspringproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);


    // Поиск по наименованию
    @Query(value = "select * from orders where (number LIKE '%?1') ", nativeQuery = true)
    List<Order> findByOrderNumber(String number);

}
