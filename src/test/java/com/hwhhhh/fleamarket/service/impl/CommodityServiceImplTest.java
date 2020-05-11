package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.service.CommodityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/11 11:51
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommodityServiceImplTest {
    @Autowired
    private CommodityService commodityService;

    @Test
    @Transactional
    void test() {
        int result = this.commodityService.updateQuantity(10, 4);
        Assertions.assertEquals(-1, result);
    }
}