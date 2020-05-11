package com.hwhhhh.fleamarket.controller.param;

import lombok.Data;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/8 10:50
 */
@Data
public class CommentReq {
    private long commodityId;
    private long reviewerId;
    private int rating;
    private String photoUrl;
    private String comment;
}
