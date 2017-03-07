package com.buaa.sn2ov.controller.Rest;

import com.buaa.sn2ov.model.Captain.PertaskUserRl;
import com.buaa.sn2ov.model.Captain.Teamtask;
import com.buaa.sn2ov.model.Captain.Transfersurvey;
import com.buaa.sn2ov.repository.PertaskUserRepository;
import com.buaa.sn2ov.repository.StationRepository;
import com.buaa.sn2ov.repository.TeamTaskRepository;
import com.buaa.sn2ov.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SN2OV on 2017/2/19.
 */

@RestController
public class TransferRestController {

    @Autowired
    TeamTaskRepository teamTaskRepository;
    @Autowired
    PertaskUserRepository pertaskUserRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    TransferRepository transferRepository;

    //获取指定用户最新一条换乘量任务及其对应的主任务
    @RequestMapping(value = "/rest/transfer/new",method = RequestMethod.GET)
    @ResponseBody
    public Object getLatestTransferTaskByUid(@RequestParam(value = "uid")int uid){
        List<Transfersurvey> transfersurveyList = pertaskUserRepository.getTransferSurveyByUid(uid);
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        if(transfersurveyList.size() == 0){
            hashMap.put("flag",0);
        }else{
            Transfersurvey newestTransfersurvey = transfersurveyList.get(0);
            int teamTaskID = newestTransfersurvey.getTeamTaskId();
            Teamtask newestTeamTask = teamTaskRepository.findOne(teamTaskID);
            String station = stationRepository.findStationByID(newestTeamTask.getStationId()).getStationName();
            hashMap.put("flag",1);
            hashMap.put("station",station);
            hashMap.put("perTask",newestTransfersurvey);
            hashMap.put("teamTask",newestTeamTask);

        }
        return hashMap;
    }

    //获取指定用户最新一条换乘量任务及其对应的主任务
    @RequestMapping(value = "/rest/transfers",method = RequestMethod.GET)
    @ResponseBody
    public Object getAllTransferTaskByUid(@RequestParam(value = "uid")int uid){
        List<Transfersurvey> transfersurveyList = pertaskUserRepository.getTransferSurveyByUid(uid);
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        HashMap<String,Object> transferTask;
        ArrayList<HashMap<?,?>>transferTaskArr = new ArrayList<HashMap<?, ?>>();

        if(transfersurveyList.size() == 0){
            hashMap.put("flag",0);
        }else{
            hashMap.put("flag",1);
            for(Transfersurvey transfersurvey :transfersurveyList){
                int teamTaskID = transfersurvey.getTeamTaskId();
                Teamtask teamtask = teamTaskRepository.findOne(teamTaskID);
                String station = stationRepository.findStationByID(teamtask.getStationId()).getStationName();
                transferTask = new HashMap<String, Object>();
                transferTask.put("perTask",transfersurvey);
                transferTask.put("teamTask",teamtask);
                transferTask.put("station",station);
                transferTaskArr.add(transferTask);
                hashMap.put("task",transferTaskArr);
            }
        }
        return hashMap;
    }

    //根据主任务ID和分任务ID获取对应的主分任务
    @RequestMapping(value = "/rest/transfer/",method = RequestMethod.GET)
    @ResponseBody
    public Object getTransferTaskByUidAndTTaskID(@RequestParam(value = "teamID")int teamID,@RequestParam(value = "transID")int transID){
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        Teamtask teamTask = teamTaskRepository.findOne(teamID);
        Transfersurvey perTask = transferRepository.findOne(transID);
        String station = stationRepository.findStationByID(teamTask.getStationId()).getStationName();
        if(teamTask==null||perTask==null)
            hashMap.put("flag",0);
        else{
            hashMap.put("flag",1);
            hashMap.put("station",station);
            hashMap.put("perTask",perTask);
            hashMap.put("teamTask",teamTask);
        }
        return hashMap;
    }


}
