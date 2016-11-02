package com.buaa.sn2ov.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class StringUtils {

	public static String getExtension(File file){
		String fileName = file.getName();
		String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
		return prefix;
	}
	
	 //获取文本内容
    @SuppressWarnings("resource")  
    public static String getContent(File file) throws Exception{  
        FileInputStream fis=new FileInputStream(file);  
        InputStreamReader isr=new InputStreamReader(fis,"utf-8");
        BufferedReader br=new BufferedReader(isr);  
        StringBuffer sb=new StringBuffer();  
        String line=br.readLine();  
        while(line!=null){  
            sb.append(line+"\n");  
            line=br.readLine();
        }  
        return sb.toString();  
    }  
    
  //清空所选文件夹的所有内容
    public static void clearDir(String dirPath){
    	File file = new File(dirPath);
    	if(file.exists())
    		delFiles(file);
    }
     
    public static void delFiles(File file){
    	if(file.isDirectory()){
    		File files[] = file.listFiles();
    		for(File f : files)
    			delFiles(f);
    	}
    	file.delete();
    }
}
