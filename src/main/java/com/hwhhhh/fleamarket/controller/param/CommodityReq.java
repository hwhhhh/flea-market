package com.hwhhhh.fleamarket.controller.param;

import lombok.Data;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/7 11:09
 */
@Data
public class CommodityReq {
    private String name;
    private String description;
    private String photoUrl;
    private int quantity;
    private float price;
    private long ownerId;
}
