package com.hwhhhh.fleamarket.controller;

import com.hwhhhh.fleamarket.controller.param.OrderReq;
import com.hwhhhh.fleamarket.domain.model.Order;
import com.hwhhhh.fleamarket.pojo.ResponseCode;
import com.hwhhhh.fleamarket.pojo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/7 16:39
 */
@RestController
@Slf4j
public class OrderController extends BaseController {

    /**
     * 下订单
     * @param orderReq
     * @return
     */
    @PostMapping("/orders")
    public ResponseData createOrder(@RequestBody OrderReq orderReq) {
        Order order = this.orderService.createOrder(orderReq);
        if (order != null) {
            return new ResponseData(ResponseCode.SUCCSEE, order);
        }
        return new ResponseData(ResponseCode.BAD_REQUEST);
    }

    /**
     * 修改订单，一般只需改一些字段因此用patch
     * @param id
     * @param orderReq
     * @return
     */
    @PatchMapping("/orders/{id}")
    public ResponseData updateOrder(@PathVariable long id, @RequestParam("note") String note) {
        Order order = this.orderService.updateOrder(id, note);
        if (order == null) {
            return new ResponseData(ResponseCode.BAD_REQUEST);
        }
        return new ResponseData(ResponseCode.SUCCSEE, order);
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @DeleteMapping("/orders/{id}")
    public ResponseData deleteOrder(@PathVariable long id) {
        this.orderService.deleteOrder(id);
        return new ResponseData(ResponseCode.SUCCSEE);
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseData getAllOrderByUserId(@PathVariable long userId) {
        return new ResponseData(ResponseCode.SUCCSEE, this.orderService.getAllOrderByUserId(userId));
    }
}
