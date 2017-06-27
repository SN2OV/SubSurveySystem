package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Admin.Token;
import com.buaa.sn2ov.model.Admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SN2OV on 2016/11/3.
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update User us set us.userName=:qUserName, us.realName=:qRealName, us.password=:qPassword ," +
            "us.role =:qRole,us.mobile =:qMobile,us.idCard =:qIDCard, us.nfc =:qNFC where us.uid=:qUID")
    public void updateUser(@Param("qUID") int UId,@Param("qUserName") String userName, @Param("qRealName") String qRealName,
                           @Param("qPassword") String password, @Param("qRole") String role,
                           @Param("qMobile") String mobile,@Param("qIDCard") String IDCard,@Param("qNFC") String NFC);

    @Query("select u.password from User u where u.userName = :qUserName")
    public String getPassword(@Param("qUserName")String userName);

    @Query("select u from User u where u.realName = :qRealName")
    public User findByUserRealName(@Param("qRealName")String realName);

    @Query("select u from User u where u.userName = :qUserName")
    public User findByUserName(@Param("qUserName")String userName);

    @Query("select u.role from User u where u.userName = :qUserName")
    public String getRoleByUserName(@Param("qUserName")String userName);

    @Modifying
    @Transactional
    @Query("update User us set us.avatarUrl=:qAvatarUrl where us.uid =:qUID")
    public void updateUserAvatar(@Param("qAvatarUrl")String avatarUrl,@Param("qUID")int uid);

    @Query("select deviceToken from User user where user.uid =:qUID")
    public String getTokenByUID(@Param("qUID")int qUID);

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    @Query("update User user set user.deviceToken =:qDeviceToken where user.uid =:qUID")
    public void updateTransfer(@Param("qDeviceToken") String qDeviceToken, @Param("qUID") int qUid);

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    @Query("update User user set user.deviceToken = null where user.uid =:qUID")
    public void delTokenByUid(@Param("qUID") int qUid);

}
