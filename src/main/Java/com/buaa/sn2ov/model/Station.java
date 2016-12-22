package com.buaa.sn2ov.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by SN2OV on 2016/12/19.
 */
@Entity
public class Station {
    private int sid;
    private String stationName;
    private String stationType;

    @Id
    @Column(name = "SID", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "StationName", nullable = false, length = 50)
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Basic
    @Column(name = "StationType", nullable = false, length = 50)
    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (sid != station.sid) return false;
        if (stationName != null ? !stationName.equals(station.stationName) : station.stationName != null) return false;
        if (stationType != null ? !stationType.equals(station.stationType) : station.stationType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (stationName != null ? stationName.hashCode() : 0);
        result = 31 * result + (stationType != null ? stationType.hashCode() : 0);
        return result;
    }
}
