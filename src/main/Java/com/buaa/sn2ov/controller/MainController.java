package com.buaa.sn2ov.controller;

import com.buaa.sn2ov.model.Users;
import com.buaa.sn2ov.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by SN2OV on 2016/3/19.
 */
@Controller
@RequestMapping("/")
public class MainController {
    // 自动装配数据库接口
    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value = "/")
    public String index() {

        return "login";
    }

    //TODO 登陆到主界面
    @RequestMapping(value = "/loginAction.do",method = RequestMethod.GET)
    public String logina(@ModelAttribute("loginUser") Users inputUser, ModelMap modelMap, HttpSession session){
        String inputUserName = inputUser.getUserName();
        try {
            inputUserName = new String(inputUserName.getBytes("iso-8859-1"),"UTF-8");
        }catch (Exception ee){
            ee.printStackTrace();
        }
        String password = usersRepository.getPassword(inputUser.getUserName())+"";
        if(password == null || !password.equals(inputUser.getPassword())){
            String info = "用户名或密码错误";
            modelMap.addAttribute("loginStatus",info);
            return "login";
        }else{
            // 查询user表中所有记录
            List<Users> userList = usersRepository.findAll();
            // 将所有记录传递给要返回的jsp页面，放在userList当中
            modelMap.addAttribute("userList", userList);

            session.setAttribute("userName",inputUserName);
            return "admin/main";
        }

    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        // 查询user表中所有记录
        List<Users> userList = usersRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);

        // 返回pages目录下的admin/users.jsp页面
        return "admin/users";
    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        // 转到 admin/addUser.jsp页面
        return "admin/addUser";
    }

    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") Users userEntity) {
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        // 数据库中添加一个用户，并立即刷新缓存
        usersRepository.saveAndFlush(userEntity);

        return "redirect:/admin/users";
    }

    // 查看用户详情
// @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
// 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        Users userEntity = usersRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail";
    }

    // 更新用户信息 页面
    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        Users userEntity = usersRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") Users user) {

        // 更新用户信息
        usersRepository.updateUser(user.getUserName(), user.getAge(),
                 user.getPassword(), user.getId());
        usersRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/users";
    }

    //登陆到主界面
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String login(@ModelAttribute("loginUser") Users inputUser, ModelMap modelMap, HttpSession session){
        String inputUserName = inputUser.getUserName();
        try {
            inputUserName = new String(inputUserName.getBytes("iso-8859-1"),"UTF-8");
        }catch (Exception ee){
            ee.printStackTrace();
        }
        String password = usersRepository.getPassword(inputUser.getUserName())+"";
        if(password == null || !password.equals(inputUser.getPassword())){
            String info = "用户名或密码错误";
            modelMap.addAttribute("loginStatus",info);
            return "login";
        }else{
            // 返回pages目录下的admin/users.jsp页面
            session.setAttribute("userName",inputUserName);
            return "main";
        }

    }

    @RequestMapping(value = "/admin/1")
    public String test(){
        return "main";
    }

}
