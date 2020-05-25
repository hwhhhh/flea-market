package com.hwhhhh.fleamarket.dao.repo;

import com.hwhhhh.fleamarket.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:28
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByBuyerId(long buyerId);
    List<OrderEntity> findAllBySellerId(long sellerId);
}
