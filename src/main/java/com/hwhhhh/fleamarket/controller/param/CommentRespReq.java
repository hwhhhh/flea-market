package com.hwhhhh.fleamarket.controller.param;

import lombok.Data;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/8 11:49
 */
@Data
public class CommentRespReq {
    private long commentId;
    private long reviewerId;
    private long replierId;
    private long parentId;
    private String comment;
}
