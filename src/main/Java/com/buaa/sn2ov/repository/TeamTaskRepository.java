package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Captain.Teamtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by SN2OV on 2016/12/27.
 */
public interface TeamTaskRepository extends JpaRepository<Teamtask,Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update Teamtask teamtask set teamtask.stationId=:qStationID, teamtask.surveyType =:qSurveyType,teamtask.taskName =:qTaskName,teamtask.surveyDate =:qSurveyDate," +
            "teamtask.timeStart =:qTimeStart,teamtask.timeEnd =:qTimeEnd,teamtask.timePeriod =:qTimePeriod,teamtask.isWeekDay =:qIsWeekDay," +
            "teamtask.createdAt =:qCreatedAt where teamtask.teamTaskId=:qTTID")
    public void updateTeamTask(@Param("qTTID") int TTId, @Param("qStationID") int stationId, @Param("qSurveyType") String surveyType, @Param("qTaskName") String taskName,
                               @Param("qSurveyDate") Date surveyDate, @Param("qTimeStart") Time timeStart, @Param("qTimeEnd") Time timeEnd, @Param("qTimePeriod") String timePeriod,
                               @Param("qIsWeekDay") String isWeekDay, @Param("qCreatedAt") Timestamp createdAt);

    @Query("select teamTask from Teamtask teamTask where teamTask.teamTaskId=:qTTID")
    public Teamtask findTeamTaskByID(@Param("qTTID")int qTTID);

    @Query("select teamTask from Teamtask teamTask where teamTask.surveyType=:qSurveyType")
    public List<Teamtask> findTeamTaskByType(@Param("qSurveyType")String surveyType);

}
