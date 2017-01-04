package com.buaa.sn2ov.model.Captain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by SN2OV on 2016/12/27.
 */
@Entity
public class Teamtask {
    private int teamTaskId;
    private int stationId;
    private String surveyType;
    private String taskName;
    private Date surveyDate;
    private String timeStart;
    private String timeEnd;
    private String timePeriod;
    private String isWeekDay;
    private Timestamp createdAt;

    @Id
    @Column(name = "TeamTaskID", nullable = false)
    public int getTeamTaskId() {
        return teamTaskId;
    }

    public void setTeamTaskId(int teamTaskId) {
        this.teamTaskId = teamTaskId;
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
    @Column(name = "SurveyType", nullable = false, length = 50)
    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    @Basic
    @Column(name = "TaskName", nullable = false, length = 100)
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "SurveyDate", nullable = false)
    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    @Basic
    @Column(name = "TimeStart", nullable = false)
    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    @Basic
    @Column(name = "TimeEnd", nullable = false)
    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Basic
    @Column(name = "TimePeriod", nullable = false, length = 50)
    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Basic
    @Column(name = "IsWeekDay", nullable = false, length = 50)
    public String getIsWeekDay() {
        return isWeekDay;
    }

    public void setIsWeekDay(String isWeekDay) {
        this.isWeekDay = isWeekDay;
    }

    @Basic
    @Column(name = "CreatedAt", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teamtask teamtask = (Teamtask) o;

        if (teamTaskId != teamtask.teamTaskId) return false;
        if (stationId != teamtask.stationId) return false;
        if (surveyType != null ? !surveyType.equals(teamtask.surveyType) : teamtask.surveyType != null) return false;
        if (taskName != null ? !taskName.equals(teamtask.taskName) : teamtask.taskName != null) return false;
        if (surveyDate != null ? !surveyDate.equals(teamtask.surveyDate) : teamtask.surveyDate != null) return false;
        if (timeStart != null ? !timeStart.equals(teamtask.timeStart) : teamtask.timeStart != null) return false;
        if (timeEnd != null ? !timeEnd.equals(teamtask.timeEnd) : teamtask.timeEnd != null) return false;
        if (timePeriod != null ? !timePeriod.equals(teamtask.timePeriod) : teamtask.timePeriod != null) return false;
        if (isWeekDay != null ? !isWeekDay.equals(teamtask.isWeekDay) : teamtask.isWeekDay != null) return false;
        if (createdAt != null ? !createdAt.equals(teamtask.createdAt) : teamtask.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamTaskId;
        result = 31 * result + stationId;
        result = 31 * result + (surveyType != null ? surveyType.hashCode() : 0);
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (surveyDate != null ? surveyDate.hashCode() : 0);
        result = 31 * result + (timeStart != null ? timeStart.hashCode() : 0);
        result = 31 * result + (timeEnd != null ? timeEnd.hashCode() : 0);
        result = 31 * result + (timePeriod != null ? timePeriod.hashCode() : 0);
        result = 31 * result + (isWeekDay != null ? isWeekDay.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
