package com.hwhhhh.fleamarket.controller;

import com.hwhhhh.fleamarket.controller.param.CommodityReq;
import com.hwhhhh.fleamarket.pojo.ResponseCode;
import com.hwhhhh.fleamarket.pojo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/7 15:02
 */
@RestController
@Slf4j
public class CommodityController extends BaseController{

    /**
     * 获取某个商品的信息
     * @param id
     * @return
     */
    @GetMapping("/commodities/{id}")
    public ResponseData getCommodity(@PathVariable long id) {
        return new ResponseData(ResponseCode.SUCCSEE, this.commodityService.getOneCommodity(id));
    }

    /**
     * 普通用户创建新商品
     * @param commodityReq
     * @return
     */
    @PostMapping("/commodities")
    public ResponseData createCommodity(@RequestBody CommodityReq commodityReq) {
        return new ResponseData(ResponseCode.SUCCSEE,this.commodityService.createCommodity(commodityReq));
    }

    /**
     * 普通用户对商品进行更改
     * @param id
     * @param commodityReq
     * @return
     */
    @PutMapping("/commodities/{id}")
    public ResponseData updateCommodity(@PathVariable("id") long id, @RequestBody CommodityReq commodityReq) {
        return new ResponseData(ResponseCode.SUCCSEE, this.commodityService.updateCommodity(id, commodityReq));
    }

    /**
     * 普通用户删除商品
     * @param id
     */
    @DeleteMapping("/commodities/{id}")
    public ResponseData deleteCommodity(@PathVariable("id") long id) {
        this.commodityService.deleteCommodity(id);
        return new ResponseData(ResponseCode.SUCCSEE);
    }

    /**
     * 普通用户获取在售的所有的商品
     * @return
     */
    @GetMapping("/commodities")
    public ResponseData getAllCommodity(){
        return new ResponseData(ResponseCode.SUCCSEE, this.commodityService.getAll());
    }

    /**
     * 通过用户的id查找用户所售卖的全部商品
     * @param ownerId 用户id
     * @return 用户所有的商品
     */
    @GetMapping("/users/{ownerId}/commodities")
    public ResponseData getAllCommodityByUserId(@PathVariable long ownerId) {
        return new ResponseData(ResponseCode.SUCCSEE, this.commodityService.getAllByOwnerId(ownerId));
    }
}
