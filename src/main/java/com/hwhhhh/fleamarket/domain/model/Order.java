package com.hwhhhh.fleamarket.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    private long buyerId;
    private long commodityId;
    private long sellerId;
    private int num;
    private String note;
    private int status;
}
