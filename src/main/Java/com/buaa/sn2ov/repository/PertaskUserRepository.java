package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.model.Captain.PertaskUserRl;
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
    @Query("select user from User user where uid in (select userId from PertaskUserRl  where perTaskId =:qPerTaskID and surveyType =:qSurveyType)")
    public List<User> getUserByPertaskIDAndSurveyType(@Param("qPerTaskID")int perTaskID,@Param("qSurveyType")String surveyType);

    @Modifying
    @Transactional
    @Query("delete from PertaskUserRl where perTaskId =:qPerTaskID and userId =:qUserID")
    public void deleteRlByPertaskIDAndUserID(@Param("qPerTaskID")int pertaskID,@Param("qUserID")int uid);
}
