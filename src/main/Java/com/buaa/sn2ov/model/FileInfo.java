package com.buaa.sn2ov.model;

/**
 * Created by SN2OV on 2016/3/22.
 */
public class FileInfo {
    private String fID;
    private String fcontent;
    private String fpath;
    private String fname;

    public String getfID() {
        return fID;
    }

    public String getFcontent() {
        return fcontent;
    }

    public String getFpath() {
        return fpath;
    }

    public void setfID(String fID) {
        this.fID = fID;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
