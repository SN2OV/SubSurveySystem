package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SN2OV on 2016/3/15.
 */
//User为实体类型,Integer为主键类型
public interface UsersRepository extends JpaRepository<Users,Integer>{
    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update Users us set us.userName=:qUserName, us.age=:qAge, us.password=:qPassword where us.id=:qId")
    public void updateUser(@Param("qUserName") String userName, @Param("qAge") Integer qAge,
                            @Param("qPassword") String password, @Param("qId") Integer id);

    @Query("select u.password from Users u where u.userName = :qUserName")
    public String getPassword(@Param("qUserName")String userName);

    @Query("select u from Users u where u.userName = :qUserName")
    public Users findByUserName(@Param("qUserName")String userName);

    @Query("select a.authority from Authorities a where a.id = :userID")
    public String getAuthorityByUserID(@Param("user")int userID);

}