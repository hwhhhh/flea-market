package com.hwhhhh.fleamarket.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/16 17:13
 */
@Data
public class UserDTO {
    private long id;
    private String name;
    private Date createdOn;
    private String email;
}
