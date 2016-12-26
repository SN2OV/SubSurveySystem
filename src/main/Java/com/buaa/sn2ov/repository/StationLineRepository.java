package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Line;
import com.buaa.sn2ov.model.LineStationRl;
import com.buaa.sn2ov.model.Station;
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

    @Query("select line from Line line where line.lid in(select lineId from LineStationRl line_station_rl where line_station_rl.stationId=:qStationID)")
    public List<Line> getLineByStationID(@Param("qStationID")int qSID);

    @Query("select count(lsId) from LineStationRl line_station_rl where line_station_rl.lineId =:qLID ")
    public long getLineNumByID(@Param("qLID")int qLID);

    @Query("select station from Station station where sid in(select stationId from LineStationRl where lineId =:qLineID order by stationOrder)")
    public List<Station> getStationNameByLineID(@Param("qLineID") int lineID);

    @Query("select stationId from LineStationRl where lineId =:qLineID order by stationOrder")
    public List<Integer> getStationIDByLineID(@Param("qLineID") int lineID);

    @Query("select stationOrder from LineStationRl where stationId =:qSID and lineId =:qLID")
    public int getStationOrderByLSID(@Param("qLID") int lineID,@Param("qSID") int stationID);

    //获取当前线路外的所有车站
    @Query("select station from Station station where sid not in(select stationId from LineStationRl where lineId =:qLineID )")
    public List<Station> getStationNotInLineID(@Param("qLineID") int qLineID);

    @Modifying
    @Transactional
    @Query("delete from LineStationRl line_station_rl where line_station_rl.lineId =:qLineID and line_station_rl.stationId =:qStationID")
    public void delRLByLineIDAndStationID(@Param("qLineID") int qLineID,@Param("qStationID") int qStationID);

    @Modifying
    @Transactional
    @Query("update LineStationRl line_station_rl set stationOrder =:qNewOrder where stationOrder =:qOldOrder")
    public void updateStationOrderByOrder(@Param("qNewOrder") int newOrder,@Param("qOldOrder") int oldOrder);

    @Modifying
    @Transactional
    @Query("update LineStationRl line_station_rl set stationOrder =:qNewOrder where lineId =:qLID and stationId =:qSID")
    public void updateStationOrderByLsId(@Param("qNewOrder") int newOrder,@Param("qLID") int lineID,@Param("qSID") int stationID);



}
