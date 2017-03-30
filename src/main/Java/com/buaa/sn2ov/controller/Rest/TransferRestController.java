package com.buaa.sn2ov.controller.Rest;

import com.buaa.sn2ov.model.Captain.Teamtask;
import com.buaa.sn2ov.model.Captain.Transfersurvey;
import com.buaa.sn2ov.model.Surveyor.DataFile;
import com.buaa.sn2ov.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    FileRepository fileRepository;

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

    @RequestMapping(value = "/rest/transferFile/upload/",method = RequestMethod.POST)
    @ResponseBody
//    public Object upLoadTransferFile(@RequestParam("file") MultipartFile file, @RequestParam("fileInfo") HashMap<String,Object> fileInfo, HttpSession session){
    public Object upLoadTransferFile(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName,@RequestParam("uid")int uid,@RequestParam("teamTaskId")int teamTaskId,@RequestParam("perTaskId")int perTaskId,HttpSession session){
            HashMap<String,Object> hashMap = new HashMap<String, Object>();
        //重命名
//        String postfix = ".jpg";
//        fileName.endsWith(".xlsx")
        //截取文件名 获得时间；判断后缀获得文件名；
        String postfix = "";
//        String fileName = fileInfo.get("fileName").toString();
//        int perTaskID = Integer.parseInt(fileInfo.get("pertaskID").toString());
//        int teamTaskID = Integer.parseInt(fileInfo.get("teamTaskID").toString());
//        int uid = Integer.parseInt(fileInfo.get("uid").toString());
        fileName = fileName.replace("\"","");
        String surveyDate = fileName.substring(0,10);
        String realPathFolder = session.getServletContext().getRealPath("/resources/upload/data/transfer/"+surveyDate+"/");
        // 根据Windows和Linux系统的不同 路径分隔符不同
        String seperator = File.separator;
        String path = realPathFolder + seperator + fileName;
        File newFile = new File(path+postfix);
        if(!newFile.exists())
            newFile.mkdirs();
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(newFile.exists()){
            hashMap.put("flag",1);
        }else
            hashMap.put("flag",0);

        //ptuID == null -> hashMap返回false
        //根据ptuID获取count，判断是否已经有数据执行update or save
        if(pertaskUserRepository.getPtuIdByUIDAndTaskID(uid,teamTaskId,perTaskId)!=null){
            int ptuId = pertaskUserRepository.getPtuIdByUIDAndTaskID(uid,teamTaskId,perTaskId);
            if(fileRepository.getCountByPtuId(ptuId)==0){
                DataFile dataFile = new DataFile();
                String[] temp = fileName.split("\\.");
                fileName = temp[0];
                dataFile.setFileName(fileName);
                dataFile.setPtuId(ptuId);
                fileRepository.saveAndFlush(dataFile);
            }
            hashMap.put("flag",1);
        }else{
            hashMap.put("flag",0);
        }
        return hashMap;
    }

}
