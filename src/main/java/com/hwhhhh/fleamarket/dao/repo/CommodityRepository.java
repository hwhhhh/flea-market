package com.hwhhhh.fleamarket.dao.repo;

import com.hwhhhh.fleamarket.dao.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:28
 */
public interface CommodityRepository extends JpaRepository<CommodityEntity, Long> {
    List<CommodityEntity> findByNameLikeAndStatusNot(String name, int status);
    List<CommodityEntity> findAllByOwnerId(long ownerId);
    List<CommodityEntity> findAllByStatus(int status);
    @Query(value = "SELECT * FROM tbl_commodity t WHERE t.status = :status AND t.id > :id LIMIT :limit", nativeQuery = true)
    List<CommodityEntity> findAllByStatusLimit(@Param("status")int status, @Param("id") long id, @Param("limit") int limit);
}
