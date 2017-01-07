package com.buaa.sn2ov.other;

import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SN2OV on 2016/4/7.
 */
public class CustomUserDetailsService implements UserDetailsService {

    // 自动装配数据库接口
    @Autowired
    UserRepository userRepository;
    private DriverManagerDataSource datasource;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails user = null;
        User loginUser = userRepository.findByUserRealName(s);
//        String access = userRepository.getAuthorityByUserID(loginUser.getId());
//        user = new User(loginUser.getUserName(),loginUser.getPassword(),true,true,true,true,getAuthorties(access));
        return user;
    }

    //获得用户权限
    public Collection<GrantedAuthority> getAuthorties(String access){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        //所有用户默认拥有Role_User权限
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(access.equals("ROLE_ADMIN"))
            //添加Role_admin权限
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            ;
        return authList;
    }

    public void setDatasource(DriverManagerDataSource datasource) {
        this.datasource = datasource;
    }

    public DriverManagerDataSource getDatasource() {
        return datasource;
    }
}
