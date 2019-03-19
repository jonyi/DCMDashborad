package com.nsn.dcm.team2.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nsn.dcm.team2.model.PrHistoryInfo;
import com.nsn.dcm.team2.model.Pronto;
import com.nsn.dcm.team2.utility.DashboardConstants;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class ExcelParser {
	private static ExcelParser excelParser = null;
	private static final Object mutex = new Object();
	private final String prontofilepath = DashboardConstants.PRONTOPATH;

	private ExcelParser() {

	}

	public static ExcelParser getInstance() {
		if (excelParser != null) {
			return excelParser;
		}
		synchronized (mutex) {
			if (excelParser == null) {
				excelParser = new ExcelParser();
			}
			return excelParser;
		}
	}

	public Vector<Pronto> readProntoCSVFile() throws Exception {
		Vector<Pronto> prontovec = new Vector<Pronto>();
		String csvFileName = prontofilepath;
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(csvFileName)));
		XSSFSheet sheet = workbook.getSheetAt(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int rowCount = sheet.getLastRowNum();
		for (int j = 7; j <= rowCount; j++) {
			XSSFRow row = sheet.getRow(j);
			Pronto pr = new Pronto();
			
			System.out.println("row.getCell(0).getStringCellValue()" +row.getCell(0).getStringCellValue());
			System.out.println("row.getCell(1).getStringCellValue()" +row.getCell(1).getStringCellValue());
			System.out.println("row.getCell(2).getStringCellValue()" +row.getCell(2).getStringCellValue());			
			System.out.println("row.getCell(3).getStringCellValue()" +row.getCell(3).getStringCellValue());						
			System.out.println("row.getCell(4).getNumericCellValue()" +row.getCell(4).getNumericCellValue());			
			System.out.println("row.getCell(5).getStringCellValue()" +row.getCell(5).getStringCellValue());			
			System.out.println("row.getCell(6).getStringCellValue()" +row.getCell(6).getStringCellValue());		
			System.out.println("row.getCell(7).getStringCellValue()" +row.getCell(7).getStringCellValue());			
			System.out.println("row.getCell(8).getStringCellValue()" +row.getCell(8).getStringCellValue());			
			System.out.println("row.getCell(9).getStringCellValue()" +row.getCell(9).getStringCellValue());
			System.out.println("row.getCell(10).getStringCellValue()" +row.getCell(10).getCellType());
			if(row.getCell(10).getCellType() == 1){
				pr.setPrReportdate(row.getCell(10).getStringCellValue());
				//System.out.println("row.getCell(10).getStringCellValue()" +row.getCell(9).getStringCellValue());
			}else if(row.getCell(10).getCellType() == 0){
				pr.setPrReportdate(row.getCell(10).getNumericCellValue()+"");
				//System.out.println("row.getCell(10).getNumericCellValue()" +row.getCell(9).getNumericCellValue());
			}else{
				pr.setPrReportdate(df.format(row.getCell(10).getDateCellValue().getTime()));
				
			}
			

			pr.setPrId(row.getCell(0).getStringCellValue());
			pr.setPrTitle(row.getCell(1).getStringCellValue());
			pr.setPrTopimportance(row.getCell(2).getStringCellValue());
			pr.setPrSeverity(row.getCell(3).getStringCellValue());
			pr.setPrPriority(row.getCell(4).getNumericCellValue()+"");
			pr.setPrState(row.getCell(5).getStringCellValue());
			pr.setPrUpdate(row.getCell(6).getStringCellValue());
			pr.setPrRespperson(row.getCell(7).getStringCellValue());
			pr.setPrAuthor(row.getCell(8).getStringCellValue());
			pr.setPrAuthorgrp(row.getCell(9).getStringCellValue());
			
			prontovec.add(pr);
			
			
		}
		return prontovec;
	}

	public Vector<PrHistoryInfo> readProntoHistoryFile() throws Exception {
		Vector<PrHistoryInfo> prontovec = new Vector<PrHistoryInfo>();
		String csvFileName = prontofilepath;

		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(csvFileName)));		
		XSSFSheet sheet = workbook.getSheetAt(1);
		int rowCount = sheet.getLastRowNum();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (int j = 2; j <= rowCount; j++) {
			XSSFRow row = sheet.getRow(j);
			PrHistoryInfo prhistoryinfo = new PrHistoryInfo();
			
	
						
			prhistoryinfo.setPrid(row.getCell(0).getStringCellValue());
			prhistoryinfo.setPrtitle(row.getCell(1).getStringCellValue());			
			prhistoryinfo.setPrseverity(row.getCell(3).getStringCellValue());			
			prhistoryinfo.setPrrespperson(row.getCell(5).getStringCellValue());
			prhistoryinfo.setPrrootcause(row.getCell(6).getStringCellValue());			
			prhistoryinfo.setPrreportdate("");
			
			if (row.getCell(10) != null && row.getCell(11) != null) {
				
				if (row.getCell(10).getCellType() != 0
						&& row.getCell(10).getCellType() != 1) {
					
					prhistoryinfo.setPropendate(df.format(row.getCell(10).getDateCellValue().getTime()));
					prhistoryinfo.setPrclosedate(df.format(row.getCell(11).getDateCellValue().getTime()));
				}
			}
			prontovec.add(prhistoryinfo);
		}
		return prontovec;
	}
	
	
	public String getPRCorrectionTime(String severity){
		String correctiontime = "Pronto File Cell Style Error!";
		String csvFileName = prontofilepath;

		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(csvFileName)));
			XSSFSheet sheet = workbook.getSheetAt(1);
		    XSSFRow row = sheet.getRow(2);
		   
//		    if(row.getCell(13).getCellType()!=0 || row.getCell(14).getCellType()!=0 || row.getCell(15).getCellType()!=0){
//		    	return correctiontime;
//		    }
//		    
		    //System.out.println("row.getCell(13).getCellType()  "+row.getCell(13).getCellType());
		    if(row.getCell(13).getCellType() != 0){
		    	return "";
		    }
		    
		    if(severity.equalsIgnoreCase("A")){
		    	System.out.println("row" +row.getCell(13).getNumericCellValue());
		    	
		    	double tempa = row.getCell(13).getNumericCellValue();
		    	BigDecimal bg = new BigDecimal(tempa);
		    	double da = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                correctiontime = da +"";
		    
		    }else if(severity.equalsIgnoreCase("B")){
		    	//System.out.println("row" +row.getCell(14).getNumericCellValue());
		    	double tempb = row.getCell(14).getNumericCellValue();
		    	BigDecimal bg1 = new BigDecimal(tempb);
		    	double db = bg1.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                correctiontime = db +"";
		    
		    }else if(severity.equalsIgnoreCase("C")){
		    	//System.out.println("row" +row.getCell(15).getNumericCellValue());
		    	double tempc = row.getCell(15).getNumericCellValue();
		    	BigDecimal bg2 = new BigDecimal(tempc);
		    	double dc = bg2.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                correctiontime = dc +"";
		    }
		    
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return correctiontime;
		
		
	}
	
	public static void main(String[] args) {
		try {
			ExcelParser.getInstance().readProntoCSVFile();
			
			ExcelParser.getInstance().readProntoHistoryFile();
			ExcelParser.getInstance().getPRCorrectionTime("a");
			//ExcelParser.getInstance().getPRCorrectionTime("b");
			//ExcelParser.getInstance().getPRCorrectionTime("c");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
