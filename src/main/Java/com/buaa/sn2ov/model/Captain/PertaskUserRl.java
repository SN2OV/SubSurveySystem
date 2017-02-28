package com.buaa.sn2ov.model.Captain;

import javax.persistence.*;

/**
 * Created by SN2OV on 2017/1/4.
 */
@Entity
@Table(name = "pertask_user_rl", schema = "subsurveysystem", catalog = "")
public class PertaskUserRl {
    private int ptuId;
    private String surveyType;
    private int perTaskId;
    private int userId;
    private int teamTaskId;

    @Id
    @Column(name = "ptuID", nullable = false)
    public int getPtuId() {
        return ptuId;
    }

    public void setPtuId(int ptuId) {
        this.ptuId = ptuId;
    }

    @Basic
    @Column(name = "surveyType", nullable = false, length = 50)
    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    @Basic
    @Column(name = "perTaskID", nullable = false)
    public int getPerTaskId() {
        return perTaskId;
    }

    public void setPerTaskId(int perTaskId) {
        this.perTaskId = perTaskId;
    }

    @Basic
    @Column(name = "userID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PertaskUserRl that = (PertaskUserRl) o;

        if (ptuId != that.ptuId) return false;
        if (perTaskId != that.perTaskId) return false;
        if (userId != that.userId) return false;
        if (surveyType != null ? !surveyType.equals(that.surveyType) : that.surveyType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ptuId;
        result = 31 * result + (surveyType != null ? surveyType.hashCode() : 0);
        result = 31 * result + perTaskId;
        result = 31 * result + userId;
        return result;
    }

    @Basic
    @Column(name = "teamTaskID", nullable = false)
    public int getTeamTaskId() {
        return teamTaskId;
    }

    public void setTeamTaskId(int teamTaskId) {
        this.teamTaskId = teamTaskId;
    }
}
