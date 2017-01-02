package com.buaa.sn2ov.controller.Admin;

import com.buaa.sn2ov.model.Admin.FileInfo;
import com.buaa.sn2ov.utils.Constants;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SN2OV on 2016/3/18.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping("/item")
    public String toIndex(HttpServletRequest request,ModelMap modelMap){
        String searchItem = request.getParameter("serarchItem");

        //定义词法分析器 分词
            long startSearchTime = new Date().getTime();
        Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);
        try {
//            SearchController.createIndex(analyzer);
            //生成查询语法树
            QueryParser parser = new QueryParser(Version.LUCENE_46, "content", analyzer);
            //定义query查询对象
            Query query = parser.parse(searchItem);
            SearchController.search(query,modelMap);
        }catch (Exception e){
            e.printStackTrace();
        }

        long endSearchTime = new Date().getTime();
        System.out.println("搜索总共花费了"+(endSearchTime-startSearchTime)+"ms");

        return "main";
    }

    private static void search(Query query,ModelMap modelMap) throws Exception {
        //指定搜索目录
        Directory dire=FSDirectory.open(new File(Constants.INDEX_STORE_PATH));
        IndexReader ir= DirectoryReader.open(dire);
        //创建搜索器
        IndexSearcher is=new IndexSearcher(ir);
        TopDocs td=is.search(query, 1000);
        System.out.println("搜索"+query.toString()+"共为您查找到"+td.totalHits+"条结果");
        ScoreDoc[] sds =td.scoreDocs;
//        List<HashMap<String,String>> sFileInfo = new ArrayList<HashMap<String, String>>();
        List<FileInfo> sFileInfo = new ArrayList<FileInfo>();
        int i = 1;
        for (ScoreDoc sd : sds) {
            Document d = is.doc(sd.doc);
//            HashMap<String,String> tmpHashMap = new HashMap<String, String>();
            FileInfo tmpFileInfo = new FileInfo();
            tmpFileInfo.setfID(i+"");
            tmpFileInfo.setFpath(d.get("path"));
            tmpFileInfo.setFcontent(d.get("content"));
            tmpFileInfo.setFname(d.get("name"));
            sFileInfo.add(tmpFileInfo);
//            tmpHashMap.put("id",i+"");
//            tmpHashMap.put("path",d.get("path"));
//            tmpHashMap.put("name",d.get("name"));
//            tmpHashMap.put("content",d.get("content"));
//            sFileInfo.add(tmpHashMap);
            i++;
        }
        modelMap.addAttribute("searchFileInfo",sFileInfo);
    }

    @RequestMapping(value = "files/show/{fID}")
    private static String showFile(@PathVariable("fID") Integer fileId, @ModelAttribute("fileInfo") FileInfo fileInfo){
        String a = fileInfo.getfID();
        return "index";
    }

//    @RequestMapping(value = "files/show/2")
//    private static String haahh(){
//        int a;
//        return "main";
//
//    }

}
