package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.controller.param.CommentReq;
import com.hwhhhh.fleamarket.controller.param.CommentRespReq;
import com.hwhhhh.fleamarket.dao.entity.CommentEntity;
import com.hwhhhh.fleamarket.dao.entity.CommentRespEntity;
import com.hwhhhh.fleamarket.dao.repo.CommentRepository;
import com.hwhhhh.fleamarket.dao.repo.CommentRespRepository;
import com.hwhhhh.fleamarket.domain.CommentDTO;
import com.hwhhhh.fleamarket.domain.model.Comment;
import com.hwhhhh.fleamarket.domain.model.CommentResp;
import com.hwhhhh.fleamarket.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:41
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;

    private CommentRespRepository commentRespRepository;

    @Autowired
    public void setCommentRespRepository(CommentRespRepository commentRespRepository){
        this.commentRespRepository = commentRespRepository;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(CommentReq commentReq) {
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentReq, commentEntity);
        this.commentRepository.save(commentEntity);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentEntity, comment);
        return comment;
    }

    /**
     * @param commentReq
     * @return
     */
    @Override
    public Comment updateComment(long commentId, CommentReq commentReq) {
        CommentEntity commentEntity = this.commentRepository.getOne(commentId);
        BeanUtils.copyProperties(commentReq, commentEntity);
        this.commentRepository.save(commentEntity);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentEntity, comment);
        return comment;
    }

    @Override
    public void deleteComment(long commentId) {
        this.commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDTO> getCommentsByCommodityId(long commodityId) {
//        List<CommentEntity> commentEntities = this.commentRepository.findAllByCommodityId(commodityId);//找到商品的对应评论
//        List<CommentDTO> comments = commentEntities.stream().map(entity -> {
//            CommentDTO commentDTO = new CommentDTO();
//            BeanUtils.copyProperties(entity, commentDTO);
//            //通过评论找到对应的回复表
//            List<CommentRespEntity> commentRespEntities = this.commentRespRepository.findAllByCommentId(commentDTO.getId());
//            List<CommentResp> commentResps = commentRespEntities.stream().map(resp -> {
//                CommentResp commentResp = new CommentResp();
//                BeanUtils.copyProperties(resp, commentResp);
//                return commentResp;
//            }).collect(Collectors.toList());
//            commentDTO.setCommentRespList(commentResps);
//            return commentDTO;
//        }).collect(Collectors.toList());

        List<CommentEntity> commentEntities = this.commentRepository.findAllByCommodityId(commodityId);
        List<CommentDTO> commentDTOList = commentEntities.stream().map(entity -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(entity, commentDTO);
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOList;
    }

    @Override
    public List<Comment> getCommentsByUserId(long userId) {
        return null;
    }

    @Override
    public CommentResp createCommentResp(CommentRespReq commentRespReq) {
        CommentRespEntity commentRespEntity = new CommentRespEntity();
        BeanUtils.copyProperties(commentRespReq, commentRespEntity);
        this.commentRespRepository.save(commentRespEntity);
        CommentResp commentResp = new CommentResp();
        BeanUtils.copyProperties(commentRespEntity, commentResp);
        return commentResp;
    }

    @Override
    public void deleteCommentResp(long responseId) {
        this.commentRespRepository.deleteById(responseId);
    }

    @Override
    public List<CommentResp> getCommentResps(long responseId) {
        return null;
    }
}
