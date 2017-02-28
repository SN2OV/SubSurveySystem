package com.buaa.sn2ov.controller.Rest;

import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by SN2OV on 2016/12/5.
 */
@RestController
public class UserRestController {

    @Autowired
    UserRepository usersRepository;


    //根据用户id查找用户信息
    @RequestMapping(value = "/rest/admin/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User showUserAPI(@PathVariable("id") Integer userId) {
        User userEntity = usersRepository.findOne(userId);
        return userEntity;
    }

    //验证用户登录@GET
    @RequestMapping(value = "/rest/validate",method = RequestMethod.GET)
    @ResponseBody
    public Object validateUserPassword(@RequestParam(value = "userName")String userName,@RequestParam(value = "password")String password){
        User loginUser = null;
        String correctPassword = usersRepository.getPassword(userName)+"";
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        if(correctPassword==null||!correctPassword.equals(password)){
            hashMap.put("flag",0);
        }else{
            loginUser = usersRepository.findByUserName(userName);
            hashMap.put("flag",1);
            hashMap.put("user",loginUser);
        }
        return hashMap;

    }

    //验证用户登录@POST
    @RequestMapping(value = "/rest/validate/",method = RequestMethod.POST)
    @ResponseBody
    public Object validateUserPasswordByGet(@RequestBody User user){
        User loginUser = null;
        String inputUserName = user.getUserName();
        String inputPassword = user.getPassword();
        String correctPassword = usersRepository.getPassword(inputUserName)+"";
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        if(correctPassword==null||!correctPassword.equals(inputPassword)){
            hashMap.put("flag",0);
        }else{
            loginUser = usersRepository.findByUserName(inputUserName);
            hashMap.put("flag",1);
            hashMap.put("user",loginUser);
        }
        return hashMap;

    }

    @RequestMapping(value = "/rest/1",method = RequestMethod.GET)
    @ResponseBody
    public Boolean testBoolean(){
        return false;
    }

}
