package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.dao.entity.CommodityEntity;
import com.hwhhhh.fleamarket.domain.model.Commodity;
import com.hwhhhh.fleamarket.service.CommodityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/23 10:23
 */
@SpringBootTest
public class CommodityServ {
    @Autowired
    CommodityService commodityService;

    @Test
    public void test() {
        List<Commodity> entities = this.commodityService.getCommoditiesByStatus(CommodityService.STATUS_COMMON, 16, 1);
        System.err.println(entities.isEmpty());
    }
}
