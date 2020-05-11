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
 * Created by Hwhhhh on 2020/5/5 14:11
 */
@Data
@Entity
@Table(name = "tbl_user")
@DynamicUpdate
@DynamicInsert
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String biography;
    private String email;
    private String password;
    private int role;
    private String photoUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifiedOn;
}
