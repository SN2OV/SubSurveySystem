package com.buaa.sn2ov.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by SN2OV on 2016/12/13.
 */
@Entity
public class Line {
    private int lid;
    private String lineName;
    private String isLooper;
    private int lineOrder;

    @Id
    @Column(name = "LID", nullable = false)
    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    @Basic
    @Column(name = "lineName", nullable = false, length = 20)
//    @Size(min = 3,max = 20,message = "线路名称必须是3-20位")
    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Basic
    @Column(name = "IsLooper", nullable = false, length = 50)
    public String getIsLooper() {
        return isLooper;
    }

    public void setIsLooper(String isLooper) {
        this.isLooper = isLooper;
    }

    @Basic
    @Column(name = "LineOrder", nullable = false)
    public int getLineOrder() {
        return lineOrder;
    }

    public void setLineOrder(int lineOrder) {
        this.lineOrder = lineOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (lid != line.lid) return false;
        if (lineOrder != line.lineOrder) return false;
        if (lineName != null ? !lineName.equals(line.lineName) : line.lineName != null) return false;
        if (isLooper != null ? !isLooper.equals(line.isLooper) : line.isLooper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lid;
        result = 31 * result + (lineName != null ? lineName.hashCode() : 0);
        result = 31 * result + (isLooper != null ? isLooper.hashCode() : 0);
        result = 31 * result + lineOrder;
        return result;
    }
}
