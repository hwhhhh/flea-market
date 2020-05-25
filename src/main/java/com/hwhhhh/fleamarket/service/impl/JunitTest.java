package com.hwhhhh.fleamarket.service.impl;

import org.springframework.stereotype.Service;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/10 22:04
 */
@Service
public class JunitTest {
    public int Func (int x, int y, int z) {
        if((x<2) && (z>5))
            x++;
        else
            x--;
        if((y==3) || (z<8))
            y++;
        else
            y--;
        return x+y+z;
    }
}
