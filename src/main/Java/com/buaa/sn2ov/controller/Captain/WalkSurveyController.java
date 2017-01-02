package com.buaa.sn2ov.controller.Captain;

import com.buaa.sn2ov.model.Captain.Teamtask;
import com.buaa.sn2ov.repository.TeamTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by SN2OV on 2016/12/28.
 */
@Controller
@RequestMapping("/")
public class WalkSurveyController {

    @Autowired
    TeamTaskRepository teamTaskRepository;

    @RequestMapping(value = "/captain/walk", method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) {
        List<Teamtask> walkSurveyList = teamTaskRepository.findTeamTaskByType("走行时间调查");
        modelMap.addAttribute("walkSurveyList", walkSurveyList);
        return "captain/walk/main";
    }

}
