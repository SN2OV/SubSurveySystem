package com.buaa.sn2ov.controller.Captain;

import com.buaa.sn2ov.model.Admin.Station;
import com.buaa.sn2ov.model.Admin.User;
import com.buaa.sn2ov.model.Captain.PertaskUserRl;
import com.buaa.sn2ov.model.Captain.Teamtask;
import com.buaa.sn2ov.model.Captain.Transfersurvey;
import com.buaa.sn2ov.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
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
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    PertaskUserRepository pertaskUserRepository;
    @Autowired
    UserRepository userRepository;

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

    @RequestMapping(value = "/captain/transfer/add", method = RequestMethod.GET)
    public String addTransfer(ModelMap modelMap) {
        List<Station> stationList = stationRepository.findAll();
        modelMap.addAttribute("stationList", stationList);
        return "captain/transfer/addTransfer";
    }

    @RequestMapping(value = "/captain/transfer/addP", method = RequestMethod.POST)
    public String addTransferPost(@ModelAttribute("transfer") Teamtask teamtask,@ModelAttribute("stationName") String stationName) {
        int sid = stationRepository.getStationIDByName(stationName);
        teamtask.setStationId(sid);
        Timestamp currTimeStamp = new Timestamp(System.currentTimeMillis());
        teamtask.setCreatedAt(currTimeStamp);
        teamTaskRepository.saveAndFlush(teamtask);
        return "redirect:/captain/transfer";
    }

    @RequestMapping(value = "/captain/transfer/delete/{id}", method = RequestMethod.GET)
    public String deleteStation(@PathVariable("id") Integer teamTaskID) {
        teamTaskRepository.delete(teamTaskID);
        teamTaskRepository.flush();
        return "redirect:/captain/transfer";
    }

//    换乘量调查子任务

    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask", method = RequestMethod.GET)
    public String getTransferMainPage(@PathVariable("teamTaskId") Integer ttID, ModelMap modelMap) {

        List<Transfersurvey> subTransferList = new ArrayList<Transfersurvey>();
        subTransferList = transferRepository.getTransferByTeamtaskID(ttID);
        modelMap.addAttribute("subTransferList", subTransferList);
        Teamtask teamTask = teamTaskRepository.findOne(ttID);
        modelMap.addAttribute("teamTask",teamTask);
        //获取每个子任务所分配的调查员
        ArrayList<List<User>> userListArr = new ArrayList<List<User>>();
        for (Transfersurvey transfersurvey : subTransferList){
            int perTaskID = transfersurvey.getTid();
            List<User> userList = pertaskUserRepository.getUserByPertaskIDAndTeamTaskID(perTaskID,ttID);
            userListArr.add(userList);
        }
        modelMap.addAttribute("userListArr",userListArr);
        //获取每个子任务未分配的调查员
        ArrayList<List<User>> unAllotedUserListArr = new ArrayList<List<User>>();
        for (Transfersurvey transfersurvey : subTransferList){
            int perTaskID = transfersurvey.getTid();
            List<User> unAllotedUserList = transferRepository.getUnAllotedUserBySurveyTypeAndPerTaskID(perTaskID,"换乘量调查");
            unAllotedUserListArr.add(unAllotedUserList);
        }

        modelMap.addAttribute("unAllotedUserListArr",unAllotedUserListArr);

        return "captain/transfer/subTransferMain";
    }

    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask/show/{tid}", method = RequestMethod.GET)
    public String showSubTransfer(@PathVariable("teamTaskId") Integer teamTaskId,  @PathVariable("tid") Integer tid, ModelMap modelMap) {
        Teamtask teamTask = teamTaskRepository.findOne(teamTaskId);
        Transfersurvey transfersurvey = transferRepository.findOne(tid);
        modelMap.addAttribute("teamTask",teamTask);
        modelMap.addAttribute("transfersurvey", transfersurvey);
        return "captain/transfer/subTransferDetail";
    }

    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask/add", method = RequestMethod.GET)
    public String addSubTransfer(@PathVariable("teamTaskId")Integer teamTaskId ,  ModelMap modelMap) {
        String teamTaskName = teamTaskRepository.findTeamTaskByID(teamTaskId).getTaskName();
        modelMap.addAttribute("teamTaskName",teamTaskName);
        modelMap.addAttribute("teamTaskId",teamTaskId);
        return "captain/transfer/addSubTransfer";
    }

    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask/addP", method = RequestMethod.POST)
    public String addSubTransferPost(@ModelAttribute("transfer") Transfersurvey transfersurvey,@PathVariable("teamTaskId") int teamTaskId) {
        transfersurvey.setTeamTaskId(teamTaskId);
        transferRepository.saveAndFlush(transfersurvey);
        return "redirect:/captain/transfer/show/{teamTaskId}/subtask";
    }

    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask/update/{tid}", method = RequestMethod.GET)
    public String updateSubTransfer(@PathVariable("teamTaskId") Integer teamTaskId, @PathVariable("tid") Integer tid, ModelMap modelMap) {

//        String teamTaskName = teamTaskRepository.findTeamTaskByID(teamTaskId).getTaskName();
        Teamtask teamTask = teamTaskRepository.findTeamTaskByID(teamTaskId);
        Transfersurvey transfersurvey = transferRepository.findOne(tid);

        modelMap.addAttribute("transfersurvey",transfersurvey);
        modelMap.addAttribute("teamTask",teamTask);
        return "captain/transfer/updateSubTransfer";
    }

    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask/updateP", method = RequestMethod.POST)
    public String updateSubTransferPost(@ModelAttribute("subTransferP") Transfersurvey transfersurvey, @PathVariable("teamTaskId") Integer teamTaskId ) {

        transferRepository.updateTransfer(transfersurvey.getTeamTaskId(),transfersurvey.getName(),transfersurvey.getPointLocation(),
                transfersurvey.getPosition(),transfersurvey.getTid());
        transferRepository.flush(); // 刷新缓冲区
        return "redirect:/captain/transfer/show/{teamTaskId}/subtask";
    }

    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask/delete/{tid}", method = RequestMethod.GET)
    public String deleteSubTransfer(@PathVariable("tid") Integer tid) {
        transferRepository.delete(tid);
        transferRepository.flush();
        return "redirect:/captain/transfer/show/{teamTaskId}/subtask";
    }


    //子任务分配调查员
    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask/addPerson/{index}",method = RequestMethod.POST)
    public String addPersonForPerTask(@PathVariable("index")Integer index,@PathVariable("teamTaskId")int ttID,@ModelAttribute("perTaskUser")String realName, ModelMap modelMap){
        List<Transfersurvey> subTransferList = transferRepository.getTransferByTeamtaskID(ttID);
        int tid = subTransferList.get(index).getTid();

        int uid = userRepository.findByUserRealName(realName).getUid();
        PertaskUserRl ptuRl = new PertaskUserRl();
        ptuRl.setSurveyType("换乘量调查");
        ptuRl.setUserId(uid);
        ptuRl.setPerTaskId(tid);
        ptuRl.setTeamTaskId(ttID);
        pertaskUserRepository.saveAndFlush(ptuRl);
        pertaskUserRepository.flush();
        return "redirect:/captain/transfer/show/{teamTaskId}/subtask";
    }

    //删除子任务分配的调查员
    @RequestMapping(value = "/captain/transfer/show/{teamTaskId}/subtask/RemovePerson/{per_user_index}",method = RequestMethod.GET)
    public String removePersonForPerTask(@PathVariable("per_user_index")String index,@PathVariable("teamTaskId")int ttID){

        String[] lsArr = index.split("_");
        int subTransferIndex = Integer.parseInt(lsArr[0]);
        int userIndex = Integer.parseInt(lsArr[1]);
        List<Transfersurvey> subTransferList = transferRepository.getTransferByTeamtaskID(ttID);
        int tid = subTransferList.get(subTransferIndex).getTid();
        //获取每个子任务所分配的调查员
        ArrayList<List<User>> userListArr = new ArrayList<List<User>>();
        for (Transfersurvey transfersurvey : subTransferList){
            int perTaskID = transfersurvey.getTid();
            List<User> userList = pertaskUserRepository.getUserByPertaskIDAndTeamTaskID(perTaskID,ttID);
            userListArr.add(userList);
        }
        int uid = userListArr.get(subTransferIndex).get(userIndex).getUid();
        pertaskUserRepository.deleteRlByPertaskIDAndUserID(tid,uid);
        pertaskUserRepository.flush();
        return "redirect:/captain/transfer/show/{teamTaskId}/subtask";
    }



}
