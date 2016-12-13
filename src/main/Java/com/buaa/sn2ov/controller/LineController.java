package com.buaa.sn2ov.controller;

import com.buaa.sn2ov.model.Line;
import com.buaa.sn2ov.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by SN2OV on 2016/12/6.
 */

@Controller
@RequestMapping("/")
public class LineController {

    // 自动装配数据库接口-----
    @Autowired
    LineRepository lineRepository;

    @RequestMapping(value = "/admin/lines", method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) {

        List<Line> lineList = lineRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在lineList当中
        modelMap.addAttribute("lineList", lineList);

        // 返回pages目录下的line/lineMain.jsp页面
        return "line/lineMain";
    }

    @RequestMapping(value = "/admin/lines/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer lineId, ModelMap modelMap) {

        // 找到userId所表示的用户
        Line lineEntity = lineRepository.findOne(lineId);

        // 传递给请求页面
        modelMap.addAttribute("line", lineEntity);
        return "line/lineDetail";
    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "/admin/lines/add", method = RequestMethod.GET)
    public String addUser() {
        return "line/addLine";
    }

    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "/admin/lines/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("line") Line lineEntity) {
        lineRepository.saveAndFlush(lineEntity);
        //加上redirect，则从@RequestMapping中匹配路径，不加上则在jsp中匹配
        return "redirect:/admin/lines";
    }


    // 更新用户信息 页面
    @RequestMapping(value = "/admin/lines/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer lineId, ModelMap modelMap) {

        // 找到userId所表示的用户
        Line lineEntity = lineRepository.findOne(lineId);

        // 传递给请求页面
        modelMap.addAttribute("line", lineEntity);
        return "line/updateLine";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/admin/lines/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("lineP") Line line) {

        //更新用户信息
        lineRepository.updateLine(line.getLid(),line.getLineName(), line.getLineOrder(),
                line.getIsLooper());
        lineRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/lines";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        String a = null;
        return "admin/lines";
    }


    //删除线路
    @RequestMapping(value = "/admin/lines/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer lineId) {

        // 删除id为userId的用户
        lineRepository.delete(lineId);
        // 立即刷新
        lineRepository.flush();
        return "redirect:/admin/lines";
    }

}
