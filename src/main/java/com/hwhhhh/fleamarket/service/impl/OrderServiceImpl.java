package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.controller.param.OrderReq;
import com.hwhhhh.fleamarket.dao.entity.OrderEntity;
import com.hwhhhh.fleamarket.dao.repo.OrderRepository;
import com.hwhhhh.fleamarket.domain.model.Order;
import com.hwhhhh.fleamarket.service.CommodityService;
import com.hwhhhh.fleamarket.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:41
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CommodityService commodityService;

    @Override
    public Order createOrder(OrderReq orderReq) {
        long commodityId = orderReq.getCommodityId();
        int num = orderReq.getNum();
        int isSuccessful = this.commodityService.updateQuantity(commodityId, num);
        if (isSuccessful != -1) {
            OrderEntity orderEntity = new OrderEntity();
            return save(orderReq, orderEntity);
        }
        return null;
    }

    @Override
    public Order updateOrder(long id, String note) {
        try {
            OrderEntity orderEntity = this.orderRepository.getOne(id); //先通过id找到Order，否则创建时间会变为空
            orderEntity.setNote(note);
            this.orderRepository.save(orderEntity);
            Order order = new Order();
            BeanUtils.copyProperties(orderEntity, order);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 内部方法，将OrderReq请求拷贝保存，并返回Order
     * @param orderReq
     * @param orderEntity
     * @return
     */
    private Order save(OrderReq orderReq, OrderEntity orderEntity) {
        BeanUtils.copyProperties(orderReq, orderEntity);
        this.orderRepository.save(orderEntity);
        Order order = new Order();
        BeanUtils.copyProperties(orderEntity, order);
        return order;
    }

    @Override
    public void deleteOrder(long id) {
        OrderEntity order = this.orderRepository.getOne(id);
        order.setIsEffective(0);
        this.orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrderByUserId(long userId) {
        List<OrderEntity> entities = this.orderRepository.findAllByBuyerId(userId);
        List<Order> orders = entities.stream().map(entity -> {
            Order order = new Order();
            BeanUtils.copyProperties(entity, order);
            return order;
        }).collect(Collectors.toList());
        return orders;
    }
}
