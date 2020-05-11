package com.hwhhhh.fleamarket.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/8 11:37
 */
@Data
@NoArgsConstructor
public class CommentResp {
    private long id;
    private long commentId;
    private long reviewerId;
    private long replierId;
    private long parentId;
    private String comment;
}
