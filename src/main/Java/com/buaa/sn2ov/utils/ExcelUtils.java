package com.buaa.sn2ov.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.HandlerBase;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ExcelUtils {
	Sheet sheet ;
	public static String getExcelContent(String path) throws FileNotFoundException{
		StringBuffer sb=new StringBuffer();  
		
		try {
			InputStream instream = new FileInputStream(path);
			Workbook readWb = Workbook.getWorkbook(instream);
			//获取第一张sheet
			Sheet readSheet = readWb.getSheet(0);
			int rsColumns = readSheet.getColumns();
			int rsRows = readSheet.getRows();
			for(int i = 0;i<rsRows;i++){
				for(int j=0;j<rsColumns;j++){
					Cell cell = readSheet.getCell(j,i);
					sb.append(cell.getContents()+"    ");
//					System.out.print(cell.getContents()+"    ");
				}
				sb.append("\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public static String getExcelContent(File file) throws FileNotFoundException{
		StringBuffer sb=new StringBuffer();  
		
		try {
			InputStream instream = new FileInputStream(file);
			Workbook readWb = Workbook.getWorkbook(instream);
			//获取第一张sheet
			Sheet readSheet = readWb.getSheet(0);
			int rsColumns = readSheet.getColumns();
			int rsRows = readSheet.getRows();
			for(int i = 0;i<rsRows;i++){
				for(int j=0;j<rsColumns;j++){
					Cell cell = readSheet.getCell(j,i);
					sb.append(cell.getContents()+"    ");
				}
				sb.append("\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public static String getXlsxContent(File file) throws FileNotFoundException{
		StringBuffer sb=new StringBuffer();  
		
		try {
			InputStream instream = new FileInputStream(file);
			XSSFWorkbook readWb = new XSSFWorkbook(instream);
			
			//获取第一张sheet
			XSSFSheet readSheet = readWb.getSheetAt(0);
			int firstRowIndex = readSheet.getFirstRowNum();    
            int lastRowIndex = readSheet.getLastRowNum();  
			int rsColumns = readSheet.getRow(0).getPhysicalNumberOfCells();
			
			for(int rIndex=firstRowIndex;rIndex<=lastRowIndex;rIndex++){
				Row row = readSheet.getRow(rIndex);
				if(row != null){
					int firstCellIndex = row.getFirstCellNum();    
                    int lastCellIndex = row.getLastCellNum();
                    for(int cIndex = firstCellIndex;cIndex<=lastCellIndex;cIndex++){
                    	org.apache.poi.ss.usermodel.Cell cell = row.getCell(cIndex);
                    	if(cell != null){
                    		sb.append(cell.toString()+"  ");
                    	}
                    }
				}
				sb.append("\n");
			}
//			for(int i = 0;i<rsRows;i++){
//				for(int j=0;j<rsColumns;j++){
//					Cell cell = readSheet.getCell(j,i);
//					sb.append(cell.getContents()+"    ");
//				}
//				sb.append("\n");
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
}
