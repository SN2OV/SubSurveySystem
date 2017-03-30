package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.model.Captain.PertaskUserRl;
import com.buaa.sn2ov.model.Captain.Transfersurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SN2OV on 2017/1/4.
 */
public interface PertaskUserRepository extends JpaRepository<PertaskUserRl,Integer> {
    @Query("select user from User user where uid in (select userId from PertaskUserRl  where perTaskId =:qPerTaskID and teamTaskId =:qTeamTaskID)")
    public List<User> getUserByPertaskIDAndTeamTaskID(@Param("qPerTaskID")int perTaskID,@Param("qTeamTaskID")int teamTaskID);

    @Modifying
    @Transactional
    @Query("delete from PertaskUserRl where perTaskId =:qPerTaskID and userId =:qUserID")
    public void deleteRlByPertaskIDAndUserID(@Param("qPerTaskID")int pertaskID,@Param("qUserID")int uid);

    @Query("select transfersurvey from Transfersurvey transfersurvey where tid in (" +
            "select perTaskId from PertaskUserRl pertaskUserRl where userId =:qUid and surveyType = '换乘量调查') order by tid desc")
    public List<Transfersurvey> getTransferSurveyByUid(@Param("qUid")int uid);

    @Query("select ptuId from PertaskUserRl where userId =:qUid and teamTaskId =:qTeamTaskId and perTaskId =:qPerTaskId")
    public Integer getPtuIdByUIDAndTaskID(@Param("qUid")int uid,@Param("qTeamTaskId")int teamTaskId,@Param("qPerTaskId")int perTaskId);


}
