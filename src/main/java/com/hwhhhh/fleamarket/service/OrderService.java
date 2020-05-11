package com.hwhhhh.fleamarket.service;

import com.hwhhhh.fleamarket.controller.param.OrderReq;
import com.hwhhhh.fleamarket.domain.model.Order;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:40
 */
public interface OrderService {
    Order createOrder(OrderReq orderReq);
    Order updateOrder(long id, String note);
    void deleteOrder(long id);
    List<Order> getAllOrderByUserId(long userId);
}
