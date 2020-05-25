package com.hwhhhh.fleamarket.controller;

import com.hwhhhh.fleamarket.service.CommentService;
import com.hwhhhh.fleamarket.service.CommodityService;
import com.hwhhhh.fleamarket.service.OrderService;
import com.hwhhhh.fleamarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/7 16:47
 */
@RequestMapping("/api")
public class BaseController {
    @Autowired
    UserService userService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    OrderService orderService;
    @Autowired
    CommentService commentService;
}
