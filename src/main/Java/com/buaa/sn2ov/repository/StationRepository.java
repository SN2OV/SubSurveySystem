package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SN2OV on 2016/12/19.
 */
public interface StationRepository extends JpaRepository<Station,Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update Station station set station.stationName=:qStationName, station.stationType=:qStationType where station.sid=:qSID")
    public void updateStation(@Param("qSID") int SID, @Param("qStationName") String qLineName, @Param("qStationType") String qStationType);

    @Query("select stationName,stationType from Station station where station.sid=:qSID")
    public Station findStationByID(@Param("qSID")int qSID);

    @Query("select sid from Station station where station.stationName =:qStationName")
    public int getStationIDByName(@Param("qStationName") String stationName);


}
