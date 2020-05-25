package com.hwhhhh.fleamarket.controller.param;

import lombok.Data;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/7 16:40
 */
@Data
public class OrderReq {
    private long buyerId;
    private long commodityId;
    private int num;
    private String note;
}
