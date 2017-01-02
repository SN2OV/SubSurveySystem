package com.buaa.sn2ov.model.Admin;

import javax.persistence.*;

/**
 * Created by SN2OV on 2016/12/22.
 */
@Entity
@Table(name = "line_station_rl", schema = "subsurveysystem")
public class LineStationRl {
    private int lsId;
    private int lineId;
    private int stationId;
    private int stationOrder;

    @Id
    @Column(name = "lsID", nullable = false)
    public int getLsId() {
        return lsId;
    }

    public void setLsId(int lsId) {
        this.lsId = lsId;
    }

    @Basic
    @Column(name = "lineID", nullable = false)
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    @Basic
    @Column(name = "StationID", nullable = false)
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "StationOrder", nullable = false)
    public int getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(int stationOrder) {
        this.stationOrder = stationOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineStationRl that = (LineStationRl) o;

        if (lsId != that.lsId) return false;
        if (lineId != that.lineId) return false;
        if (stationId != that.stationId) return false;
        if (stationOrder != that.stationOrder) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lsId;
        result = 31 * result + lineId;
        result = 31 * result + stationId;
        result = 31 * result + stationOrder;
        return result;
    }
}
