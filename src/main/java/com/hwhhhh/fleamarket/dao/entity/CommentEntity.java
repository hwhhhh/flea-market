package com.hwhhhh.fleamarket.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/5 14:12
 */
@Entity
@Table(name = "tbl_comment")
@Data
@DynamicUpdate
@DynamicInsert
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long commodityId;
    private long reviewerId;
    private int rating;
    private String photoUrl;
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifiedOn;

    @OneToMany(mappedBy = "commentEntity")
    private List<CommentRespEntity> commentRespList;
}
