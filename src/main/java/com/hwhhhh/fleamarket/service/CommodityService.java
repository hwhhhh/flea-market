package com.hwhhhh.fleamarket.service;

import com.hwhhhh.fleamarket.controller.param.CommodityReq;
import com.hwhhhh.fleamarket.domain.model.Commodity;
import com.hwhhhh.fleamarket.views.HomeVO;

import java.util.List;

/**
 * @Description 商品接口
 * Created by Hwhhhh on 2020/5/6 11:40
 */
public interface CommodityService {
    // 0 为默认已发布，-1为审核中， 1为热门商品， 2为banner中的商品, -2为审核不通过
    int STATUS_COMMON = 0, STATUS_AUDIT = -1, STATUS_FALSE=-2, STATUS_HOT = 1, STATUS_BANNER = 2;

    HomeVO getHomeAll();//获取所有商品
    List<Commodity> findByNameLike(String name);//通过商品名查找商品
    List<Commodity> getAllByOwnerId(long ownerId);//通过用户id获取商品信息
    List<Commodity> getCommoditiesByStatus(int status);// 获取某种状态中的商品
    List<Commodity> getCommoditiesByStatus(int status, long id, int limit);// 获取某种状态中的从id为？开始的limit个商品
    Commodity createCommodity(CommodityReq commodityReq);//添加商品
    Commodity updateCommodity(long id, CommodityReq commodityReq);//修改商品信息
    Commodity getOneCommodity(long id);//获取一件商品的具体信息。
    boolean saveImgURL(long id, String url);//保存商品的图像
    boolean updateStatus(long id, int status);
    void deleteCommodity(long id);//删除商品
    long updateQuantity(long id, int num);//更新商品的数量
}
