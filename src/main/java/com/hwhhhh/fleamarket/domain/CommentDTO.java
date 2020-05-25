package com.hwhhhh.fleamarket.domain;

import com.hwhhhh.fleamarket.domain.model.CommentResp;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/8 15:07
 */
@Data
public class CommentDTO {
    private long id;
    private long reviewerId;
    private String reviewerName;
    private int rating;
    private String photoUrl;
    private String comment;
    private Date createdOn;
    private List<CommentResp> commentRespList;
}
