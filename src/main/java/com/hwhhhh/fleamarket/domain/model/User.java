package com.hwhhhh.fleamarket.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String biography;
    private String email;
    private String password;
    private int role;
    private String photo;
}
