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
public class Commodity {
    private long id;
    private String name;
    private String description;
    private String photoUrl;
    private int quantity;
    private float price;
    private long ownerId;
    private int status;
}
