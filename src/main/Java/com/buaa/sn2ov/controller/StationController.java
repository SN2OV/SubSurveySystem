package com.buaa.sn2ov.controller;

import com.buaa.sn2ov.model.Line;
import com.buaa.sn2ov.model.Station;
import com.buaa.sn2ov.repository.StationLineRepository;
import com.buaa.sn2ov.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SN2OV on 2016/12/19.
 */

@Controller
@RequestMapping("/")
public class StationController {

    @Autowired
    StationRepository stationRepository;
    @Autowired
    StationLineRepository stationLineRepository;

    @RequestMapping(value = "/admin/stations", method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) {

        List<Station> stationList = stationRepository.findAll();
        modelMap.addAttribute("stationList", stationList);
        ArrayList<List<Line>> linesArr = new ArrayList<List<Line>>  ();
        for(Station station : stationList){
            int sid = station.getSid();
            List<Line> lineList = stationLineRepository.getLineByStationID(sid);
            linesArr.add(lineList);
        }
        modelMap.addAttribute("linesArr", linesArr);
        return "station/stationMain";
    }

    @RequestMapping(value = "/admin/stations/add", method = RequestMethod.GET)
    public String addUser() {
        return "station/addStation";
    }

    @RequestMapping(value = "/admin/stations/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("station") Station stationEntity) {
        stationRepository.saveAndFlush(stationEntity);
        return "redirect:/admin/stations";
    }

    @RequestMapping(value = "/admin/stations/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer stationID, ModelMap modelMap) {

        Station stationEntity = stationRepository.findOne(stationID);
        modelMap.addAttribute("station", stationEntity);
        return "station/stationDetail";
    }

    // 更新用户信息 页面
    @RequestMapping(value = "/admin/stations/update/{id}", method = RequestMethod.GET)
    public String updateLine(@PathVariable("id") Integer lineId, ModelMap modelMap) {

        // 找到userId所表示的用户
        Station stationEntity = stationRepository.findOne(lineId);

        // 传递给请求页面
        modelMap.addAttribute("station", stationEntity);
        return "station/updateStation";
    }

    @RequestMapping(value = "/admin/stations/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("station") Station station) {
        //更新线路信息
        stationRepository.updateStation(station.getSid(),station.getStationName(),station.getStationType());
        stationRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/stations";
    }

    @RequestMapping(value = "/admin/stations/delete/{id}", method = RequestMethod.GET)
    public String deleteStation(@PathVariable("id") Integer stationId) {
        stationRepository.delete(stationId);
        stationRepository.flush();
        return "redirect:/admin/stations";
    }
}
