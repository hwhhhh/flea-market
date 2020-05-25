package com.hwhhhh.fleamarket.views;

import com.hwhhhh.fleamarket.dao.entity.CommodityEntity;
import com.hwhhhh.fleamarket.domain.model.Commodity;
import lombok.Data;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/14 16:25
 */
@Data
public class HomeVO {
    private List<Commodity> bannerList;
    private List<Commodity> hotList;
}
