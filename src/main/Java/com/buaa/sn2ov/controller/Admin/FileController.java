package com.buaa.sn2ov.controller.Admin;

import com.buaa.sn2ov.model.Admin.FileInfo;
import com.buaa.sn2ov.utils.Constants;
import com.buaa.sn2ov.utils.ExcelUtils;
import com.buaa.sn2ov.utils.StringUtils;
import com.buaa.sn2ov.utils.WordUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SN2OV on 2016/3/22.
 */
@Controller
@RequestMapping("/file")
public class FileController{


    @RequestMapping("/manage")
    private String toIndex(ModelMap modelMap){
        //处理文本
        File[] files=new File(Constants.INDEX_FILE_PATH).listFiles();
        List<FileInfo> filesInfo = new ArrayList<FileInfo>();
        int id=1;
        for(File file:files){
            String name=file.getName();
            String path=file.getAbsolutePath();
            FileInfo tmpFile = new FileInfo();
            tmpFile.setfID(id+"");
            tmpFile.setFname(name);
            tmpFile.setFpath(path);
            filesInfo.add(tmpFile);
            id++;
        }
        modelMap.addAttribute("filesInfo",filesInfo);
        return "admin/fileManage";
    }

    @RequestMapping(value = "/manage.do",method = RequestMethod.POST )
    private String uploadFile(@RequestParam(value = "fileAdd",required = false)MultipartFile file, HttpServletRequest request, ModelMap modelmap){
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String uploadResult = "";
        if(fileName.length()==0)
            uploadResult = "请选择正确的文件进行上传";
         else
            uploadResult = "上传成功";
        modelmap.addAttribute("fileName",fileName);
        modelmap.addAttribute("uploadResult",uploadResult);
        toIndex(modelmap);
        return "admin/fileManage";
    }

    @RequestMapping("manage.action")
    private String recreateIndex(ModelMap modelMap){
        //删除过期的索引
        delAllIndex();
//        StringUtils.clearDir(Constants.INDEX_STORE_PATH);
        //定义词法分析器 分词
        long startSearchTime = new Date().getTime();
        Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);
        try {
            FileController.createIndex(analyzer);
        }catch (Exception e){
            e.printStackTrace();
        }

        long endSearchTime = new Date().getTime();
        String durTime = (endSearchTime-startSearchTime)+"";
        System.out.println("索引总共花费了"+(durTime)+"ms");
//        modelMap.addAttribute("durTime",durTime);
        toIndex(modelMap);
        return "admin/fileManage";
    }

    //创建索引
    public static void createIndex(Analyzer analyzer) throws Exception{
        long startCreateIndexTime = new Date().getTime();
        //创建索引库目录
        Directory dire= FSDirectory.open(new File(Constants.INDEX_STORE_PATH));
        IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_46, analyzer);
        //创建IndexWriter，进行索引文件的写入
        IndexWriter iw=new IndexWriter(dire, iwc);
        //进行索引的存储
        FileController.addDoc(iw);
        iw.close();
        long endstartCreateIndexTime = new Date().getTime();
        System.out.println("创建索引总共花费了"+(endstartCreateIndexTime-startCreateIndexTime)+"ms\n");
    }

    //添加到document
    public static void addDoc(IndexWriter iw)  throws Exception{
        //处理数据库

        //处理文本
        File[] files=new File(Constants.INDEX_FILE_PATH).listFiles();
        for (File file : files) {
            Document doc=new Document();
            String content = null;
            //判断文件类型
            if(StringUtils.getExtension(file).equals("txt"))
                content = StringUtils.getContent(file);
            else if(StringUtils.getExtension(file).equals("xls"))
                content = ExcelUtils.getExcelContent(file);
            else if(StringUtils.getExtension(file).equals("doc"))
                content = WordUtils.getDocContent(file);
            else if(StringUtils.getExtension(file).equals("docx"))
                content = WordUtils.getDocxContent(file);
            else if(StringUtils.getExtension(file).equals("xlsx"))
                content = ExcelUtils.getXlsxContent(file);
            else
                continue;

            String name=file.getName();
            String path=file.getAbsolutePath();
            doc.add(new TextField("content", content, Field.Store.YES));
            doc.add(new TextField("name", name, Field.Store.YES));
            doc.add(new TextField("path", path, Field.Store.YES));  //后面通过d.get("path获取")
            System.out.println("文件名："+name+"\n"+content+"路径："+path+"\n");
            iw.addDocument(doc);
            iw.commit();
        }
        iw.close();
    }

    //删除索引
    private void delAllIndex(){
        Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);
        try {
            Directory dire= FSDirectory.open(new File(Constants.INDEX_STORE_PATH));
            IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_46, analyzer);
            //创建IndexWriter，进行索引文件的写入
            IndexWriter iw=new IndexWriter(dire, iwc);
            iw.deleteAll();
            iw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
