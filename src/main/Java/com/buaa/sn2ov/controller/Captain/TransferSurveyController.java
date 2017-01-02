package com.buaa.sn2ov.controller.Captain;

import com.buaa.sn2ov.model.Admin.Station;
import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.model.Captain.Teamtask;
import com.buaa.sn2ov.repository.StationRepository;
import com.buaa.sn2ov.repository.TeamTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SN2OV on 2016/12/28.
 */
@Controller
@RequestMapping("/")
public class TransferSurveyController {
    @Autowired
    TeamTaskRepository teamTaskRepository;
    @Autowired
    StationRepository stationRepository;

    @RequestMapping(value = "/captain/transfer", method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) {
        List<Teamtask> transferSurveyList = teamTaskRepository.findTeamTaskByType("换乘量调查");
        modelMap.addAttribute("transferSurveyList", transferSurveyList);
        List<Station> stationList = new ArrayList<Station>();
        for(Teamtask teamtask : transferSurveyList){
            Station station = stationRepository.findStationByID(teamtask.getStationId());
            stationList.add(station);
        }
        modelMap.addAttribute("transferSurveyList", transferSurveyList);
        modelMap.addAttribute("stationList",stationList);
        return "captain/transfer/main";
    }

    @RequestMapping(value = "/captain/transfer/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer ttID, ModelMap modelMap) {

        Teamtask teamtask = teamTaskRepository.findOne(ttID);
        Station station = stationRepository.findStationByID(teamtask.getStationId());

        modelMap.addAttribute("teamtask", teamtask);
        modelMap.addAttribute("station", station);
        return "captain/transfer/transferDetail";
    }

    @RequestMapping(value = "/captain/transfer/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer teamTaskId, ModelMap modelMap) {

        Teamtask teamtask = teamTaskRepository.findOne(teamTaskId);
        Station station = stationRepository.findStationByID(teamtask.getStationId());
        List<Station> stationList = stationRepository.findAll();
        modelMap.addAttribute("teamtask", teamtask);
        modelMap.addAttribute("stationList", stationList);
        modelMap.addAttribute("station", station);
        return "captain/transfer/updateTransfer";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/captain/transfer/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("transferP") Teamtask teamtask, @ModelAttribute("stationP")Station station ) {

        int sid = stationRepository.getStationIDByName(station.getStationName());
        //更新用户信息
        teamTaskRepository.updateTeamTask(teamtask.getTeamTaskId(),sid,teamtask.getSurveyType(),
                teamtask.getTaskName(),teamtask.getSurveyDate(),teamtask.getTimeStart(),teamtask.getTimeEnd(),
                teamtask.getTimePeriod(),teamtask.getIsWeekDay(),teamtask.getCreatedAt());
        teamTaskRepository.flush(); // 刷新缓冲区
        return "redirect:/captain/transfer";
    }

}
