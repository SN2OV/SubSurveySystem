package com.buaa.sn2ov.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


public class WordUtils {
    public static String getDocContent(File file){

        StringBuffer sb=new StringBuffer();

        try {
            FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis);
            Range range = doc.getRange();
            sb.append(range.text());
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return sb.toString();

    }

    public static void getDocContent(String file){

        try {
            FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis);
            Range range = doc.getRange();
            String text = range.text();
            fis.close();
            System.out.println(text);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String getDocxContent(File file){

        StringBuffer sb=new StringBuffer();

        try {
            InputStream  fis = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fis);
            List<XWPFParagraph> paras = doc.getParagraphs();
            for(XWPFParagraph para : paras){
                sb.append(para.getText()+"\n");
            }

            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return sb.toString();
    }


}
