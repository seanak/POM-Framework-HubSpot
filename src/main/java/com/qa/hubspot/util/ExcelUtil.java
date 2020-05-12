package com.qa.hubspot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;   //org.apache.poi.ss.usermodel.WorkbookFactory - import from here
	
	
	public static String test_data_path = "C:\\Users\\seana\\New Workspace\\1AseleniumNewProjectApril20Pract\\"
			+ "src\\main\\java\\com\\qa\\hubspot\\testdata\\HubSpotTestData.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		
		try {
			FileInputStream fis = new FileInputStream(test_data_path);
			
			book = WorkbookFactory.create(fis); //this giving us workbook so save this in workbook ref as book
			sheet = book.getSheet(sheetName); //this giving us sheet so save this in ref as sheet
			
			System.out.println("get sheet last row number is :- "+sheet.getLastRowNum());
			System.out.println("get row with 0 number "+ sheet.getRow(0));
			System.out.println("get sheet last column number is :- "+ sheet.getRow(0).getLastCellNum());
			
			Object data[][]= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<sheet.getLastRowNum();i++) {
				
				for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {
					
					data[i][k]=sheet.getRow(i+1).getCell(k).toString();
				}
			}
			return data;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
}
