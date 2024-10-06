package org.flipkart.pom.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {

	public static Object[][] readExcelData(String fileName) throws IOException 
	{
		String sheetName = "Sheet1";
		
		File file =	new File(fileName);
		
		//Create an object of FileInputStream class to read excel file
		
		FileInputStream inputStream = new FileInputStream(file);
		
		//System.out.println("abc");
		
		Workbook wb = null;
		
		//Workbook Workbook = new XSSFWorkbook(inputStream);
		if(fileName.endsWith(".xls")) {
			wb = new HSSFWorkbook(inputStream);
		}
		if(fileName.endsWith(".xlsx")) {
			wb = new XSSFWorkbook(inputStream);
		}
	
		System.out.println(wb.getActiveSheetIndex());
		
		//Read sheet inside the workbook by its name
		
		 Sheet  mySheet = wb.getSheet(sheetName);
		 int rowCount = mySheet.getLastRowNum();
		 int colCount = mySheet.getRow(0).getPhysicalNumberOfCells();
		 
		 //colCount = colCount -1;
		 
		 System.out.println("No of rows: " + rowCount + ". No of cols: " + colCount);
         
		 Object[][] object = new Object[rowCount][colCount];
     	 
         for (int i = 0; i < rowCount; i++) {
    		//Loop over all the rows
     		System.out.println("Row :" + i);
    		Row row = mySheet.getRow(i+1);
    	
    	
    		//Create a loop to print cell values in a row
    		for (int j = 0; j < colCount; j++) {
    			//Print excel data in console
    			object[i][j] = row.getCell(j).toString().replace(".0", "");
    			System.out.println(object[i][j]);
    		}
    		
    	}
        wb.close();
		return object;
	}
	
}