package com.hwhhhh.fleamarket.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/8 11:20
 */
@Entity
@Table(name = "tbl_comment_response")
@Data
@DynamicUpdate
@DynamicInsert
public class CommentRespEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long commentId;
    private long reviewerId;    // 评论者的id
    private long replierId; // 被回复者的id，若为0，则表示单纯给楼主的评论
    private long parentId;  // 上级id
    private String comment;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "commentId", insertable = false, updatable = false)
    private CommentEntity commentEntity;
}
