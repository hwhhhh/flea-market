package com.hwhhhh.fleamarket.dao.repo;

import com.hwhhhh.fleamarket.dao.entity.CommentRespEntity;
import com.hwhhhh.fleamarket.domain.CommentDTO;
import com.hwhhhh.fleamarket.domain.model.CommentResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/8 16:47
 */
public interface CommentRespRepository extends JpaRepository<CommentRespEntity, Long> {

    List<CommentRespEntity> findAllByCommentId(long id);
}
