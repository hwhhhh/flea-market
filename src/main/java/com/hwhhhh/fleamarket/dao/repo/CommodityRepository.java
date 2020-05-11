package com.hwhhhh.fleamarket.dao.repo;

import com.hwhhhh.fleamarket.dao.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:28
 */
public interface CommodityRepository extends JpaRepository<CommodityEntity, Long> {
    List<CommodityEntity> findByNameLike(String name);
    List<CommodityEntity> findAllByOwnerId(long ownerId);
}
