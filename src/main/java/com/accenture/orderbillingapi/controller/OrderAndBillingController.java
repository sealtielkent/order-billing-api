package com.accenture.orderbillingapi.controller;

import com.accenture.orderbillingapi.entity.CafeClerk;
import com.accenture.orderbillingapi.entity.Order;
import com.accenture.orderbillingapi.impl.DiscountedBill;
import com.accenture.orderbillingapi.impl.OrderBill;
import com.accenture.orderbillingapi.impl.RegularBill;
import com.accenture.orderbillingapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderAndBillingController {

    private final OrderRepository orderRepository;
    private final RegularBill regularBill;
    private final DiscountedBill discountedBill;
    private final CafeClerk clerk = new CafeClerk("Kentaru");

    @Autowired
    public OrderAndBillingController(OrderRepository orderRepository,
                                     RegularBill regularBill,
                                     DiscountedBill discountedBill) {
        this.orderRepository = orderRepository;
        this.regularBill = regularBill;
        this.discountedBill = discountedBill;
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrderList(){
        return orderRepository.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrder(@RequestBody Order order,
                             @PathVariable("id") long id){
        Optional<Order> orders = orderRepository.findById(id);
        Order orderObj = null;

        if(orders.isPresent()) {
            Order ord = orders.get();
            ord.setOrderName(order.getOrderName());
            ord.setPrice(order.getPrice());
            ord.setIsDiscountPercentage(order.getIsDiscountPercentage());

            orderObj = orderRepository.save(ord);
        }

        return orderObj;

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable("id") Order id){
        orderRepository.delete(id);
    }

    @GetMapping("orders/regularTotalBill")
    @ResponseStatus(HttpStatus.OK)
    public OrderBill getRegularTotalBill(){
        List<Order> totalBill = orderRepository.findAll();
        regularBill.setOrderList(totalBill);
        return regularBill;
    }

    @GetMapping("orders/discountedTotalBill")
    @ResponseStatus(HttpStatus.OK)
    public OrderBill getDiscountedTotalBill(){
        List<Order> totalBill = orderRepository.findAll();
        discountedBill.setOrderList(totalBill);
        return discountedBill;
    }

    @GetMapping("orders/clerk")
    @ResponseStatus(HttpStatus.OK)
    public CafeClerk getClerk() {
        return clerk;
    }
}
