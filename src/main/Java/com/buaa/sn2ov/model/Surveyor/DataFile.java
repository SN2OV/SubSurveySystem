package com.buaa.sn2ov.model.Surveyor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by SN2OV on 2017/3/30.
 */
@Entity
public class DataFile {
    private int fid;
    private String fileName;
    private int ptuId;

    @Id
    @Column(name = "fid", nullable = false)
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "fileName", nullable = false, length = 100)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "ptuID", nullable = false)
    public int getPtuId() {
        return ptuId;
    }

    public void setPtuId(int ptuId) {
        this.ptuId = ptuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataFile dataFile = (DataFile) o;

        if (fid != dataFile.fid) return false;
        if (ptuId != dataFile.ptuId) return false;
        if (fileName != null ? !fileName.equals(dataFile.fileName) : dataFile.fileName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + ptuId;
        return result;
    }
}
