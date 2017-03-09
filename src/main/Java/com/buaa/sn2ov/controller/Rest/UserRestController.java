package com.buaa.sn2ov.controller.Rest;

import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.repository.UserRepository;
import com.buaa.sn2ov.utils.ImageUtils;
import com.buaa.sn2ov.utils.StringUtils;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
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

    @RequestMapping(value = "/rest/update/user",method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserInfo(@RequestBody User user){
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        usersRepository.updateUser(user.getUid(),user.getUserName(),user.getRealName(),user.getPassword(),user.getRole(),
                user.getMobile(),user.getIdCard(),user.getNfc());
        hashMap.put("flag",1);
        hashMap.put("user",user);
        return hashMap;
    }

    @RequestMapping(value = "/rest/avatar/upload/",method = RequestMethod.POST)
    @ResponseBody
    public Object upLoadUserAvatar(@RequestParam("file") MultipartFile file,@RequestParam("fileName") String fileName,@RequestParam("uid")int uid, HttpSession session){
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        //重命名
        String postfix = ".jpg";
        String realPathFolder = session.getServletContext().getRealPath("/resources/upload/image");
        fileName = fileName.replace("\"","");
        String path = realPathFolder + "\\" + fileName;
        File avatarFile = new File(path+postfix);
        if(!avatarFile.exists())
            avatarFile.mkdirs();
        try {
            file.transferTo(avatarFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //压缩图片
        String reducePath = realPathFolder + "\\" +fileName +"_s" +postfix;
        path += postfix;
        ImageUtils.reduceImg(path,reducePath,0,0,(float)0.5);
        if(avatarFile.exists()){
            hashMap.put("flag",1);
        }else
            hashMap.put("flag",0);
        //保存入库
        usersRepository.updateUserAvatar(fileName,uid);
        return hashMap;
    }

    @ResponseBody
    @RequestMapping(value = "/avatar/get/{imgName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void testphoto(@PathVariable("imgName") String imgName, HttpServletRequest request,HttpServletResponse response) throws IOException {
        String fileName = request.getSession().getServletContext().getRealPath("/resources/upload/image/")
                +imgName+".jpg";
        File file = new File(fileName);
        if(!(file.exists() && file.canRead()))
            return;
        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        int length = inputStream.read(data);
        inputStream.close();

        response.setContentType("image/png");

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }
}
