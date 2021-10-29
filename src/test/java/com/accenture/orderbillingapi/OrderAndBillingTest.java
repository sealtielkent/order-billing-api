package com.accenture.orderbillingapi;

import com.accenture.orderbillingapi.entity.Order;
import com.accenture.orderbillingapi.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderAndBillingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void getOrderList() {
        Order order1 = new Order("Latte", 4.5,5);
        Order saveOrder1 = orderRepository.save(order1);

        Order order2 = new Order("Tea", 5.5, 5);
        Order saveOrder2 = orderRepository.save(order2);

        List<Order> getOrders = orderRepository.findAll();

        assertThat(getOrders).contains(saveOrder1, saveOrder2);
    }

    @Test
    void addOrder() {
        Order order = new Order("Cafe Latte", 3.5, 10);
        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder);
    }

    @Test
    void deleteOrder() {
        Order order = new Order("Cafe Mocha", 3.5, 2);
        Order savedOrder = orderRepository.save(order);

        orderRepository.deleteById(savedOrder.getId());
        boolean isExisting = orderRepository.findById(savedOrder.getId()).isPresent();

        assertFalse(isExisting);
    }

    @Test
    void updateOrder() {
        Order order = new Order("Cafe Latte", 3.5, 50);
        Order savedOrder = orderRepository.save(order);
        Order updateOrd = orderRepository.findById(savedOrder.getId()).get();
        updateOrd.setOrderName("caffe mocha");
        updateOrd.setPrice(3.5);
        orderRepository.save(updateOrd);

        Order validateOrder = orderRepository.findById(savedOrder.getId()).get();

        assertThat(validateOrder.getId()).isEqualTo(savedOrder.getId());
        assertThat(validateOrder.getOrderName()).isEqualTo(savedOrder.getOrderName());
        assertThat(validateOrder.getPrice()).isEqualTo(savedOrder.getPrice());
    }
}
