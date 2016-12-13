package com.buaa.sn2ov.controller;

import com.buaa.sn2ov.model.User;
import com.buaa.sn2ov.repository.UserRepository;
import com.buaa.sn2ov.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by SN2OV on 2016/3/19.
 */
@Controller
@RequestMapping("/")
public class MainController {
    // 自动装配数据库接口-----
    @Autowired
    UserRepository usersRepository;

    //自动登录到系统
    @RequestMapping(value = "/")
    public String index(@ModelAttribute("loginUser") User inputUser, ModelMap modelMap, HttpSession session,HttpServletResponse httpResponse,HttpServletRequest request) {

        //调缓存
        Cookie cokLoginName = CookieUtils.getCookieByName(request, "loginName");
        Cookie cokLoginPwd = CookieUtils.getCookieByName(request, "loginPwd");
        if (cokLoginName != null && cokLoginPwd != null && cokLoginName.getValue() != "" && cokLoginPwd.getValue() != "") {
            String loginName = cokLoginName.getValue();
            String loginPwd = cokLoginPwd.getValue();

            //如果此人被删除或者修改密码，因此需要再做验证
            String password = usersRepository.getPassword(loginName);
            if(password == null || !loginPwd.equals(password)){
                String info = "用户名或密码错误";
                modelMap.addAttribute("loginStatus",info);
                return "login";
            }

            List<User> userList = usersRepository.findAll();
            // 将所有记录传递给要返回的jsp页面，放在userList当中
            String role = usersRepository.getRoleByUserName(loginName);
            modelMap.addAttribute("userList", userList);
            modelMap.addAttribute("userName", loginName);
            modelMap.addAttribute("userRole", role);
            return "admin/main";
        }else
            return "login";

    }

    //TODO 登陆到主界面---
    @RequestMapping(value = "/loginAction.do",method = RequestMethod.GET)
    public String logina(@ModelAttribute("loginUser") User inputUser, ModelMap modelMap, HttpSession session,HttpServletResponse httpResponse,HttpServletRequest request){

//        //调缓存
//        Cookie cokLoginName = CookieUtils.getCookieByName(request, "loginName");
//        Cookie cokLoginPwd = CookieUtils.getCookieByName(request, "loginPwd");
//        if (cokLoginName != null && cokLoginPwd != null && cokLoginName.getValue() != "" && cokLoginPwd.getValue() != "") {
//            String loginName = cokLoginName.getValue();
//            String loginPwd = cokLoginPwd.getValue();
//
//            //如果此人被删除或者修改密码，因此需要再做验证
//            String password = usersRepository.getPassword(loginName);
//            if(password == null || !loginPwd.equals(password)){
//                String info = "用户名或密码错误";
//                modelMap.addAttribute("loginStatus",info);
//                return "login";
//            }
//
//            return "admin/main";
//        }else{
            //没有缓存，手动登录
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
                //一周的保存时间
                int loginMaxAge = 7*24*60*60;
                // 查询user表中所有记录
                List<User> userList = usersRepository.findAll();
                // 将所有记录传递给要返回的jsp页面，放在userList当中
                String role = usersRepository.getRoleByUserName(inputUserName);
                modelMap.addAttribute("userList", userList);
                modelMap.addAttribute("userName", inputUserName);
                modelMap.addAttribute("userRole", role);

                session.setAttribute("userName",inputUserName);
                CookieUtils.addCookie(httpResponse,"loginName",inputUserName,loginMaxAge);
                CookieUtils.addCookie(httpResponse,"loginPwd",password,loginMaxAge);
                return "admin/main";
            }
//        }

    }

    //登出
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String loginOut(HttpSession session,HttpServletResponse response) {
        // 清除session
        CookieUtils.addCookie(response, "loginName", null, 0); // 清除Cookie
        CookieUtils.addCookie(response, "loginPwd", null, 0); // 清除Cookie
//        session.invalidate();
        return "login";
    }

    @RequestMapping(value = "/admin/main", method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) {
        // 查询user表中所有记录
        List<User> userList = usersRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);

        // 返回pages目录下的admin/users.jsp页面
        return "admin/main";
    }


    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        // 查询user表中所有记录
        List<User> userList = usersRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);

        return "admin/main";
    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        // 转到 admin/addUser.jsp页面
        return "admin/addUser";
    }

    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") User userEntity) {
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
        User userEntity = usersRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail";
    }

//    @RequestMapping(value = "/restful/admin/users/show/{id}", method = RequestMethod.POST, consumes = "application/json")
//    public @ResponseBody
//    //如果数据是以json传进来的一定要@RequestBody,用于把传来的JSON 转换成接收的对象
//    User showUserJson(@RequestBody User user) {
//        String test = user.getUserName();
//        return user;
//    }


    // 更新用户信息 页面
    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        User userEntity = usersRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") User user) {

        //更新用户信息
        usersRepository.updateUser(user.getUid(),user.getUserName(), user.getRealName(),
                 user.getPassword(), user.getRole(),user.getMobile(),user.getIdCard(),user.getNfc());
        usersRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/users";
    }

    //登陆到主界面
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String login(@ModelAttribute("loginUser") User inputUser, ModelMap modelMap, HttpSession session){
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
            // 返回pages目录下的admin/users.jsp页2面
            session.setAttribute("userName",inputUserName);
            return "main";
        }

    }


    //删除用户
    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {

        // 删除id为userId的用户
        usersRepository.delete(userId);
        // 立即刷新
        usersRepository.flush();
        return "redirect:/admin/users";
    }

}
