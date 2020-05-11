package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.dao.entity.OrderEntity;
import com.hwhhhh.fleamarket.dao.repo.OrderRepository;
import com.hwhhhh.fleamarket.domain.model.Order;
import com.hwhhhh.fleamarket.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/11 15:30
 */
@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrder() {
    }

    @Test
    @Transactional
    void updateOrder() {
        Order order = this.orderService.updateOrder(6, "哈哈哈哈哈哈修改了");
        Assertions.assertEquals("哈哈哈哈哈哈修改了", order.getNote());
    }

    @Test
    @Transactional
    void deleteOrder() {
        this.orderService.deleteOrder(6);
        OrderEntity orderEntity = this.orderRepository.getOne((long) 6);
        Assertions.assertEquals(0, orderEntity.getIsEffective());
    }

    @Test
    void getAllOrderByUserId() {
    }
}