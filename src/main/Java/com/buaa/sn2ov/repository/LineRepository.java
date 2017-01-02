package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Admin.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SN2OV on 2016/12/6.
 */
public interface LineRepository extends JpaRepository<Line,Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update Line line set line.lineName=:qLineName, line.lineOrder=:qLineOrder, line.isLooper=:qIsLooper where line.lid=:qLID")
    public void updateLine(@Param("qLID") int UId, @Param("qLineName") String qLineName, @Param("qLineOrder") int qLineOrder, @Param("qIsLooper") String qIsLooper);

    @Query("select line from Line line where line.lid=:qLID")
    public Line findLineByID(@Param("qLID")int qLID);

}
