package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Surveyor.DataFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by SN2OV on 2017/3/29.
 */
public interface FileRepository extends JpaRepository<DataFile,Integer>{
//    @Modifying      // 说明该方法是修改操作
//    @Transactional  // 说明该方法是事务性操作
//    @Query("update Line line set line.lineName=:qLineName, line.lineOrder=:qLineOrder, line.isLooper=:qIsLooper where line.lid=:qLID")
//    public void updateLine(@Param("qLID") int UId, @Param("qLineName") String qLineName, @Param("qLineOrder") int qLineOrder, @Param("qIsLooper") String qIsLooper);

//    @Query("select file from DataFile file where file.uid=:qUID")
//    public DataFile findFileByUID(@Param("qUID")int qUID);

    @Query("select count(*) from DataFile dataFile where dataFile.ptuId =:qPtuId")
    public long getCountByPtuId(@Param("qPtuId")int ptuId);
}
