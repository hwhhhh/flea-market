package com.hwhhhh.fleamarket.service;

import com.hwhhhh.fleamarket.controller.param.CommentReq;
import com.hwhhhh.fleamarket.controller.param.CommentRespReq;
import com.hwhhhh.fleamarket.dao.entity.CommentEntity;
import com.hwhhhh.fleamarket.domain.CommentDTO;
import com.hwhhhh.fleamarket.domain.model.Comment;
import com.hwhhhh.fleamarket.domain.model.CommentResp;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:41
 */
public interface CommentService {
    Comment createComment(CommentReq commentReq);
    Comment updateComment(long commentId, CommentReq commentReq);
    CommentResp createCommentResp(CommentRespReq commentRespReq);
    void deleteComment(long commentId);
    void deleteCommentResp(long responseId);
    List<CommentDTO> getCommentsByCommodityId(long commodityId);
    List<Comment> getCommentsByUserId(long userId);
    List<CommentResp> getCommentResps(long responseId);
}
