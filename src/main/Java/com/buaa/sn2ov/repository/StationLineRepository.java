package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Line;
import com.buaa.sn2ov.model.LineStationRl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SN2OV on 2016/12/22.
 */
public interface StationLineRepository extends JpaRepository<LineStationRl,Integer> {

    @Query("select lineName from Line line where line.lid in(select lineId from LineStationRl line_station_rl where line_station_rl.stationId=:qStationID)")
    public List<Line> findLineByStationID(@Param("qStationID")int qSID);

    @Query("select count(lsId) from LineStationRl line_station_rl where line_station_rl.lineId =:qLID ")
    public long getLineNumByID(@Param("qLID")int qLID);

}
