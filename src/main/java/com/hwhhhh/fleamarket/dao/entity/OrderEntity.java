package com.hwhhhh.fleamarket.dao.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/5 14:12
 */
@Data
@Table(name = "tbl_order")
@Entity
@DynamicUpdate
@DynamicInsert
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long buyerId;
    private long commodityId;
    private long sellerId;
    private int num;
    private String note;
    @Column(columnDefinition = "tinyint(1) default 1 comment '0为无效，1为有效'")
    private int isEffective = 1;
    // 订单状态 0为待处理，1为发货中，2为待确认，3为已完成，-1为已取消
    private int status;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifiedOn;
}
