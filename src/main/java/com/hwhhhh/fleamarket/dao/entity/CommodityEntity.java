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
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String photoUrl;
    @Column
    private int quantity;
    @Column
    private float price;
    @Column
    private long ownerId;
    @Column
    private int status;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifiedOn;
}
