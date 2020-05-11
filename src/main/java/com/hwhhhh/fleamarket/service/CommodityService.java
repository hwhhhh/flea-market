package com.hwhhhh.fleamarket.service;

import com.hwhhhh.fleamarket.controller.param.CommodityReq;
import com.hwhhhh.fleamarket.domain.model.Commodity;

import java.util.List;

/**
 * @Description 商品接口
 * Created by Hwhhhh on 2020/5/6 11:40
 */
public interface CommodityService {
    List<Commodity> getAll();//获取所有商品
    List<Commodity> findByNameLike(String name);//通过商品名查找商品
    List<Commodity> getAllByOwnerId(long ownerId);//通过用户id获取商品信息
    Commodity createCommodity(CommodityReq commodityReq);//添加商品
    Commodity updateCommodity(long id, CommodityReq commodityReq);//修改商品信息
    Commodity getOneCommodity(long id);//获取一件商品的具体信息。
    void deleteCommodity(long id);//删除商品
    int updateQuantity(long id, int num);//更新商品的数量
}
