package com.hwhhhh.fleamarket.controller;

import com.hwhhhh.fleamarket.controller.param.OrderReq;
import com.hwhhhh.fleamarket.domain.model.Order;
import com.hwhhhh.fleamarket.domain.pojo.ResponseCode;
import com.hwhhhh.fleamarket.domain.pojo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * 修改订单
     * @param id
     * @param note
     * @return
     */
    @PutMapping("/orders/{id}")
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

    /**
     * 根据买家id获得订单，即已购买的商品
     * @param buyerId
     * @return
     */
    @GetMapping("/buyer-orders/{buyerId}")
    public ResponseData getAllByBuyerId(@PathVariable long buyerId) {
        return new ResponseData(ResponseCode.SUCCSEE, this.orderService.getAllByBuyerId(buyerId));
    }

    /**
     * 用户的订单管理，即卖出去的商品的订单
     * @param sellerId
     * @return
     */
    @GetMapping("/pending-orders/{sellerId}")
    public ResponseData getAllBySellerId(@PathVariable long sellerId) {
        return new ResponseData(ResponseCode.SUCCSEE, this.orderService.getAllBySellerId(sellerId));
    }

    /**
     * 改变订单的状态
     * @param id
     * @param status
     * @return
     */
    @PutMapping("/pending-orders/{id}")
    public ResponseData changeStatus(@PathVariable long id, @RequestParam int status) {
        if (this.orderService.updateStatus(id, status)) {
            log.info(status + "");
            return new ResponseData(ResponseCode.SUCCSEE, status);
        } else {
            return new ResponseData(ResponseCode.BAD_REQUEST);
        }
    }
}
