package com.buaa.sn2ov.repository;

import com.buaa.sn2ov.model.Admin.Token;
import com.buaa.sn2ov.model.Captain.Transfersurvey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SN2OV on 2017/6/27.
 */
public interface TokenRepository {
    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    @Query("update Token token set token.deviceToken =:qDeviceToken where token.uid =:qUID")
    public void updateTransfer(@Param("qDeviceToken") String qDeviceToken, @Param("qUID") int qUid);

    @Query("select deviceToken from Token token where token.uid =:qUID")
    public Token getTokenByUID(@Param("qUID")int qUID);

}
