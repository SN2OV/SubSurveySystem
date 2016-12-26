package com.buaa.sn2ov.controller;

import com.buaa.sn2ov.model.Line;
import com.buaa.sn2ov.model.LineStationRl;
import com.buaa.sn2ov.model.Station;
import com.buaa.sn2ov.repository.LineRepository;
import com.buaa.sn2ov.repository.StationLineRepository;
import com.buaa.sn2ov.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
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
    @Autowired
    StationLineRepository stationLineRepository;
    @Autowired
    StationRepository stationRepository;

    @RequestMapping(value = "/admin/lines", method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) {

        List<Line> lineList = lineRepository.findAll();
        modelMap.addAttribute("lineList", lineList);
        ArrayList<Integer> lineCountArr = new ArrayList<Integer>();
        for(Line line : lineList){
            //由于hiberate返回的是long,所以必须要强制转换为int,否则无法
            int lineCount = (int)stationLineRepository.getLineNumByID(line.getLid());
            lineCountArr.add(lineCount);
        }
        modelMap.addAttribute("lineCountArr", lineCountArr);
        return "line/lineMain";
    }

    @RequestMapping(value = "admin/lines/line-stations/{lineID}",method = RequestMethod.GET)
    public String getStationByLineID(@PathVariable("lineID") Integer lineID, ModelMap modelMap){
        //传值：车站列表
//        List<Station> stationList = stationLineRepository.getStationNameByLineID(lineID);
        List<Integer> stationIDList = stationLineRepository.getStationIDByLineID(lineID);
        List<Station> stationList = new ArrayList<Station>();
        for(int sid : stationIDList){
            stationList.add(stationRepository.findOne(sid));
        }
        List<Integer> stationOrderArr = new ArrayList<Integer>();
        for(Station station : stationList){
            int stationOrder = stationLineRepository.getStationOrderByLSID(lineID,station.getSid());
            stationOrderArr.add(stationOrder);
        }
        modelMap.addAttribute("stationList",stationList);
        //传值：车站顺序
        modelMap.addAttribute("stationOrderArr",stationOrderArr);
        //传值：路线名称
        Line line = lineRepository.findLineByID(lineID);
        modelMap.addAttribute("line",line);
        //传值：所有车站-当前线路列表
        List<Station> stationNotInLineList = stationLineRepository.getStationNotInLineID(lineID);
        modelMap.addAttribute("stationNotInLineList",stationNotInLineList);

        return "line/lineStationList";
    }


    @RequestMapping(value = "/admin/lines/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer lineId, ModelMap modelMap) {

        Line lineEntity = lineRepository.findOne(lineId);
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
    public String updateLine(@PathVariable("id") Integer lineId, ModelMap modelMap) {

        // 找到userId所表示的用户
        Line lineEntity = lineRepository.findOne(lineId);

        // 传递给请求页面
        modelMap.addAttribute("line", lineEntity);
        return "line/updateLine";
    }

    @RequestMapping(value = "/admin/lines/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("line") Line line) {
        //@Valid Line line
//        if(result.hasErrors()){
//            return "line/updateLine";
//            //之前用了
////            return "redirect:/admin/lines/update/"+line.getLid();
//        }else{
//            //更新线路信息
//            lineRepository.updateLine(line.getLid(),line.getLineName(), line.getLineOrder(),
//                    line.getIsLooper());
//            lineRepository.flush(); // 刷新缓冲区
//            return "redirect:/admin/lines";
//        }
        //更新线路信息
        lineRepository.updateLine(line.getLid(),line.getLineName(), line.getLineOrder(),
                line.getIsLooper());
        lineRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/lines";
    }

    //删除线路
    @RequestMapping(value = "/admin/lines/delete/{id}", method = RequestMethod.GET)
    public String deleteLine(@PathVariable("id") Integer lineId) {

        lineRepository.delete(lineId);
        lineRepository.flush();
        return "redirect:/admin/lines";
    }

    @RequestMapping(value = "/admin/lines/line-stations/addP/{id}", method = RequestMethod.POST)
    public String addLineStation(@PathVariable("id")Integer lineId,@ModelAttribute("stationLineAdd") String stationName){
        int stationID = stationRepository.getStationIDByName(stationName);
        long stationOrder = stationLineRepository.getLineNumByID(lineId)+1;
        LineStationRl lineStation = new LineStationRl();
        lineStation.setLineId(lineId);
        lineStation.setStationId(stationID);
        lineStation.setStationOrder((int)stationOrder);
        stationLineRepository.saveAndFlush(lineStation);
        return "redirect:/admin/lines/line-stations/"+lineId;
    }

    @RequestMapping(value = "/admin/lines/line-stations/delete/{lid_sid}",method = RequestMethod.GET)
    public String deleteStationLineRL(@PathVariable("lid_sid")String l_sID){
        //传不进来值，因为之前使用了${}
        String[] lsArr = l_sID.split("_");
        int lineId = Integer.parseInt(lsArr[0]);
        int stationId = Integer.parseInt(lsArr[1]);
        stationLineRepository.delRLByLineIDAndStationID(lineId,stationId);
        return "redirect:/admin/lines/line-stations/"+lineId;
    }

    // 车站在线路中向上排序
    @RequestMapping(value = "/admin/lines/line-stations/up/{lid_sid}",method = RequestMethod.GET)
    public String sortStationUpByID(@PathVariable("lid_sid")String l_sID){
        String[] lsArr = l_sID.split("_");
        int lineId = Integer.parseInt(lsArr[0]);
        int stationId = Integer.parseInt(lsArr[1]);
        //1)根据lsID获取需要修改的order
        int editOrder = stationLineRepository.getStationOrderByLSID(lineId,stationId);
        //2)将老order(up)设置成需要修改的order,对
        int upOrder = editOrder - 1;
        if(upOrder == 0)
            return "redirect:/admin/lines/line-stations/"+lineId;
        stationLineRepository.updateStationOrderByOrder(editOrder,upOrder);
        //3)根据lsID设置新的order(up)
        stationLineRepository.updateStationOrderByLsId(upOrder,lineId,stationId);
        return "redirect:/admin/lines/line-stations/"+lineId;
    }

    //TODO 车站在线路中向下排序
    @RequestMapping(value = "/admin/lines/line-stations/down/{lid_sid}",method = RequestMethod.GET)
    public String sortStationDownByID(@PathVariable("lid_sid")String l_sID){
        String[] lsArr = l_sID.split("_");
        int lineId = Integer.parseInt(lsArr[0]);
        int stationId = Integer.parseInt(lsArr[1]);
        long stationCount = stationLineRepository.getLineNumByID(lineId);
        //1)根据lsID获取需要修改的order
        int editOrder = stationLineRepository.getStationOrderByLSID(lineId,stationId);
        //2)将老order(up)设置成需要修改的order,对
        int downOrder = editOrder + 1;
        if(downOrder > stationCount)
            return "redirect:/admin/lines/line-stations/"+lineId;
        stationLineRepository.updateStationOrderByOrder(editOrder,downOrder);
        //3)根据lsID设置新的order(up)
        stationLineRepository.updateStationOrderByLsId(downOrder,lineId,stationId);
        return "redirect:/admin/lines/line-stations/"+lineId;
    }


}
