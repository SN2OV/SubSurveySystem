package com.buaa.sn2ov.model.Captain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by SN2OV on 2017/1/4.
 */
@Entity
public class Transfersurvey {
    private int tid;
    private int teamTaskId;
    private String name;
    private int pointLocation;
    private String position;

    @Id
    @Column(name = "tid", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "teamTaskID", nullable = false)
    public int getTeamTaskId() {
        return teamTaskId;
    }

    public void setTeamTaskId(int teamTaskId) {
        this.teamTaskId = teamTaskId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pointLocation", nullable = false)
    public int getPointLocation() {
        return pointLocation;
    }

    public void setPointLocation(int pointLocation) {
        this.pointLocation = pointLocation;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 100)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfersurvey that = (Transfersurvey) o;

        if (tid != that.tid) return false;
        if (teamTaskId != that.teamTaskId) return false;
        if (pointLocation != that.pointLocation) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid;
        result = 31 * result + teamTaskId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + pointLocation;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
