package com.buaa.sn2ov.controller;

import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SN2OV on 2016/12/5.
 */
@RestController
public class UserRestController {

    @Autowired
    UserRepository usersRepository;


    @RequestMapping(value = "/rest/admin/users/show/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User showUserAPI(@PathVariable("id") Integer userId) {

        // 找到userId所表示的用户
        User userEntity = usersRepository.findOne(userId);
        return userEntity;
    }

}
