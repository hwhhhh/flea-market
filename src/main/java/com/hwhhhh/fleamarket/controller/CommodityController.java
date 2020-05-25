package com.hwhhhh.fleamarket.controller;

import com.hwhhhh.fleamarket.controller.param.CommoStatusReq;
import com.hwhhhh.fleamarket.controller.param.CommodityReq;
import com.hwhhhh.fleamarket.domain.model.Commodity;
import com.hwhhhh.fleamarket.domain.pojo.ResponseCode;
import com.hwhhhh.fleamarket.domain.pojo.ResponseData;
import com.hwhhhh.fleamarket.service.CommodityService;
import com.hwhhhh.fleamarket.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
     * 获取在售的所有热门的商品 Banner和Common
     * @return
     */
    @GetMapping("/commodities/hot")
    public ResponseData getHotCommodities(){
        return new ResponseData(ResponseCode.SUCCSEE, this.commodityService.getHomeAll());
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

    @PostMapping("/commodities/{id}/images")
    public ResponseData uploadImg(@PathVariable long id, @RequestParam(value = "file") MultipartFile multipartFile) {
        try {
            String url = ImageUtil.uploadImg(multipartFile, ImageUtil.COMMODITY_IMG);
            boolean isSuccessful = this.commodityService.saveImgURL(id, url);
            if (isSuccessful) {
                return new ResponseData(ResponseCode.SUCCSEE, url);
            } else {
                return new ResponseData(ResponseCode.BAD_REQUEST);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.BAD_REQUEST);
        }
    }

    /**
     * 根据名称搜索
     * @param search
     * @return
     */
    @GetMapping("/commodities/search")
    public ResponseData getCommoditiesByNameLike(@RequestParam("name") String search) {
        return new ResponseData(ResponseCode.SUCCSEE, this.commodityService.findByNameLike(search));
    }

    /**
     * 获取处于审核状态的商品
     * @return
     */
    @GetMapping("/commodities/audit")
    public ResponseData getCommoditiesInAudit() {
        return new ResponseData(ResponseCode.SUCCSEE, this.commodityService.getCommoditiesByStatus(CommodityService.STATUS_AUDIT));
    }

    /**
     * 设置商品状态
     * @param id    商品id
     * @param commoStatusReq 设置状态的请求
     * @return
     */
    @PutMapping("/commodities/{id}/status")
    public ResponseData setCommodityStatus(@PathVariable long id, @RequestBody CommoStatusReq commoStatusReq) {
        this.commodityService.updateStatus(id, commoStatusReq.getStatus());
        return new ResponseData(ResponseCode.SUCCSEE);
    }

    /**
     * 获取limit个商品
     * @param status    商品的状态
     * @param id    商品的最小id
     * @param limit     个数
     * @return  返回结果
     */
    @GetMapping("/commodities/status")
    public ResponseData getCommonCommodities(@RequestParam int status, @RequestParam long id, @RequestParam int limit) {
        List<Commodity> commodities = this.commodityService.getCommoditiesByStatus(status, id, limit);
        if (!commodities.isEmpty()) {
            return new ResponseData(ResponseCode.SUCCSEE, commodities);
        } else {
            return new ResponseData(ResponseCode.BAD_REQUEST);
        }
    }
}
