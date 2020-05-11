package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.controller.param.CommodityReq;
import com.hwhhhh.fleamarket.dao.entity.CommodityEntity;
import com.hwhhhh.fleamarket.dao.repo.CommodityRepository;
import com.hwhhhh.fleamarket.domain.model.Commodity;
import com.hwhhhh.fleamarket.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:42
 */
@Service
@Slf4j
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public Commodity createCommodity(CommodityReq commodityReq) {
        CommodityEntity commodityEntity = new CommodityEntity();
        return save(commodityReq, commodityEntity);
    }

    @Override
    public void deleteCommodity(long id) {
        this.commodityRepository.deleteById(id);
    }

    @Override
    public Commodity updateCommodity(long id, CommodityReq commodityReq) {
        CommodityEntity commodityEntity = this.commodityRepository.getOne(id);//通过id找到commodity，否则会覆盖数据库中的数据，导致为null
        return save(commodityReq, commodityEntity);
    }

    /**
     * 内部方法，将CommodityReq请求拷贝到Entity中并保存，再拷贝至Commodity并返回
     * @param commodityReq
     * @param commodityEntity
     * @return
     */
    private Commodity save(CommodityReq commodityReq, CommodityEntity commodityEntity) {
        BeanUtils.copyProperties(commodityReq, commodityEntity);
        this.commodityRepository.save(commodityEntity);
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityEntity, commodity);
        return commodity;
    }

    @Override
    public Commodity getOneCommodity(long id) {
        CommodityEntity commodityEntity = this.commodityRepository.getOne(id);
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityEntity, commodity);
        return commodity;
    }

    @Override
    public List<Commodity> getAllByOwnerId(long ownerId) {
        List<CommodityEntity> entities = this.commodityRepository.findAllByOwnerId(ownerId);
        return entities.stream().map(entity -> {
            Commodity commodity = new Commodity();
            BeanUtils.copyProperties(entity, commodity);
            return commodity;
        }).collect(Collectors.toList());
    }

    /**
     * 通过商品名称查找
     * @param name
     * @return
     */
    @Override
    public List<Commodity> findByNameLike(String name) {
        List<CommodityEntity> entities = this.commodityRepository.findByNameLike(name);
        List<Commodity> commodities = entities.stream().map(entity -> {
            Commodity commodity = new Commodity();
            BeanUtils.copyProperties(entity, commodity);
            return commodity;
        }).collect(Collectors.toList());
        return commodities;
    }

    /**
     * 获取所有商品
     * @return
     */
    @Override
    public List<Commodity> getAll() {
        List<CommodityEntity> entities = this.commodityRepository.findAll();
        List<Commodity> commodities = entities.stream().map(entity -> {
            Commodity commodity = new Commodity();
            BeanUtils.copyProperties(entity, commodity);
            return commodity;
        }).collect(Collectors.toList());
        return commodities;
    }

    @Override
    public int updateQuantity(long id, int num) {
        try {
            CommodityEntity entity = this.commodityRepository.getOne(id);
            int quantity = entity.getQuantity();
            if (quantity > num) {   //如果商品剩余数量大于购买的数量则执行操作
                if (num <= 0) {
                    return -1;
                }
                entity.setQuantity(quantity-num);
                this.commodityRepository.save(entity);
                return quantity - num;
            }
            return -1;  //如果不够返回-1
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
