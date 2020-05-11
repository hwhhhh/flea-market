package com.hwhhhh.fleamarket.dao.repo;

import com.hwhhhh.fleamarket.dao.entity.CommentEntity;
import com.hwhhhh.fleamarket.domain.CommentDTO;
import com.hwhhhh.fleamarket.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:28
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByCommodityId(long id);
}
