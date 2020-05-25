package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.controller.param.OrderReq;
import com.hwhhhh.fleamarket.dao.entity.CommodityEntity;
import com.hwhhhh.fleamarket.dao.entity.OrderEntity;
import com.hwhhhh.fleamarket.dao.repo.CommodityRepository;
import com.hwhhhh.fleamarket.dao.repo.OrderRepository;
import com.hwhhhh.fleamarket.domain.model.Order;
import com.hwhhhh.fleamarket.service.CommodityService;
import com.hwhhhh.fleamarket.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.ORB;
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
    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public Order createOrder(OrderReq orderReq) {
        long commodityId = orderReq.getCommodityId();
        int num = orderReq.getNum();
        long isSuccessful = this.commodityService.updateQuantity(commodityId, num);  // 查询商品剩余数量是否够
        if (isSuccessful != -1) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setSellerId(isSuccessful);
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
    public List<Order> getAllByBuyerId(long buyerId) {
        List<OrderEntity> entities = this.orderRepository.findAllByBuyerId(buyerId);
        List<Order> orders = entities.stream().map(entity -> {
            CommodityEntity commodityEntity = this.commodityRepository.getOne(entity.getCommodityId());
            Order order = new Order();
            BeanUtils.copyProperties(entity, order);
            order.setCommodityName(commodityEntity.getName());
            order.setPhotoUrl(commodityEntity.getPhotoUrl());
            order.setPrice(commodityEntity.getPrice());
            return order;
        }).collect(Collectors.toList());
        return orders;
    }

    /**
     * 根据卖家id获取订单，订单中的商品id需要转换成商品的名称, 还需添加商品的图片url
     * @param sellerId
     * @return
     */
    @Override
    public List<Order> getAllBySellerId(long sellerId) {
        List<OrderEntity> entities = this.orderRepository.findAllBySellerId(sellerId);
        List<Order> orders = entities.stream().map(entity -> {
            CommodityEntity commodityEntity = this.commodityRepository.getOne(entity.getCommodityId());
            Order order = new Order();
            BeanUtils.copyProperties(entity, order);
            order.setCommodityName(commodityEntity.getName());
            order.setPhotoUrl(commodityEntity.getPhotoUrl());
            order.setPrice(commodityEntity.getPrice());
            return order;
        }).collect(Collectors.toList());
        return orders;
    }

    /**
     * 更改状态，这里的try catch没有用，需要改
     * @param id    订单id
     * @param status    更改的status
     * @return  是否更改成功
     */
    @Override
    public boolean updateStatus(long id, int status) {
        try {
            OrderEntity entity = this.orderRepository.getOne(id);
            entity.setStatus(status);
            this.orderRepository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
