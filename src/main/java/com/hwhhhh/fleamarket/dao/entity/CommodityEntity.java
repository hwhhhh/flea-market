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
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "tbl_commodity")
public class CommodityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String photoUrl;
    private int quantity;
    private float price;
    private long ownerId;
    @Column(columnDefinition = "tinyint(1) default 0")
    private int status;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifiedOn;
}
