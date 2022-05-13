package com.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	public static String[][] getData(String filePath, String sheetName){
			try {
				Workbook workbook = new XSSFWorkbook(ExcelUtils.class.getClassLoader().getResourceAsStream(filePath));
				Sheet sheet = workbook.getSheet(sheetName);
				if(sheet != null) {
					//return sheet.getPhysicalNumberOfRows();
					int rowCount = sheet.getPhysicalNumberOfRows();
					int columnCount = sheet.getRow(1).getPhysicalNumberOfCells();
					String[][] data = new String[rowCount-1][columnCount];
					for (int i = 1; i < rowCount; i++) {
						for (int j = 0; j < columnCount; j++) {
							//System.out.println("ROW : "+ i + " Column : " + j);
							Cell cell = sheet.getRow(i).getCell(j);
							if(CellType.NUMERIC.equals(cell.getCellType())) {
								data[i - 1][j] = ""+ cell.getNumericCellValue();
							}else {
								data[i - 1][j] = cell.getStringCellValue();
							}
							
						}
					}
					return data;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		
		public static List<Map<String, String>> getDataAsMap(String filePath, String sheetName){
			try {
				Workbook workbook = new XSSFWorkbook(ExcelUtils.class.getClassLoader().getResourceAsStream(filePath));
				Sheet sheet = workbook.getSheet(sheetName);
				List<Map<String, String>> data = new ArrayList<Map<String,String>>();
				if(sheet != null) {
					//return sheet.getPhysicalNumberOfRows();
					int rowCount = sheet.getPhysicalNumberOfRows();
					int columnCount = sheet.getRow(1).getPhysicalNumberOfCells();
					for (int i = 1; i < rowCount; i++) {
						Map<String, String> rowData = new HashMap<String, String>();
						for (int j = 0; j < columnCount; j++) {
							Cell cell = sheet.getRow(i).getCell(j);
							Cell key = sheet.getRow(0).getCell(j);
							if(CellType.NUMERIC.equals(cell.getCellType())) {
								rowData.put(key.getStringCellValue(), ""+ cell.getNumericCellValue());
							}else {
								rowData.put(key.getStringCellValue(), cell.getStringCellValue());
							}
						}
					}
					return data;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
	 	
	
	}
	
	
		 
	
	
	
		 
	

	

