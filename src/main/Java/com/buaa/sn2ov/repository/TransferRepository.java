package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.model.Captain.Transfersurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SN2OV on 2017/1/4.
 */
public interface TransferRepository extends JpaRepository<Transfersurvey,Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update Transfersurvey transfer set transfer.teamTaskId =:qTeamTaskID, transfer.name =:qName,transfer.pointLocation =:qPointLocation,transfer.position =:qPosition where transfer.tid =:qTID")
    public void updateTransfer(@Param("qTeamTaskID") int qTeamTaskID, @Param("qName") String qName, @Param("qPointLocation") int qPointLocation, @Param("qPosition") String qPosition, @Param("qTID")int qTID);

    @Query("select transfer from Transfersurvey transfer where transfer.tid =:qTID")
    public Transfersurvey getTransferByID(@Param("qTID")int qTID);

    @Query("select user from User user where uid not in (select userId from PertaskUserRl where perTaskId =:qPerTaskID and surveyType =:qSurveyType) and role = '调查员'")
    public List<User> getUnAllotedUserBySurveyTypeAndPerTaskID(@Param("qPerTaskID")int perTaskID,@Param("qSurveyType")String surveyType);

}
