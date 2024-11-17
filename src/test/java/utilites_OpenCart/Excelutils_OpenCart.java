package utilites_OpenCart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Excelutils_OpenCart {


	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static int rowCount;
	public static int cellCount;

	public String fpath=null;

	public Excelutils_OpenCart(String fpath)
	{
		this.fpath = fpath;
	}



	public int getRowCount(String sheetName) throws IOException
	{
		fis = new FileInputStream(fpath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		rowCount =sheet.getLastRowNum();

		workbook.close();
		fis.close();

		return rowCount;
	}
	public int getCellCount(String sheetName, int rowNum) throws IOException
	{
		fis = new FileInputStream(fpath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cellCount = row.getLastCellNum();

		workbook.close();
		fis.close();

		return cellCount;
	}
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException
	{
		fis = new FileInputStream(fpath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		String data= "";

		DataFormatter dataFormatter = new DataFormatter();

		try {
			data = dataFormatter.formatCellValue(cell);
		} catch (Exception e) {
			data="";
		}

		workbook.close();
		fis.close();

		return data;

	}
	public void SetCellData(String sheetName, int rowNum, int cellNum, String data) throws IOException
	{
		File xlfile = new File(fpath);
		if(!xlfile.exists())
		{
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(xlfile);
			workbook.write(fos);
		}
		fis = new FileInputStream(fpath);
		workbook = new XSSFWorkbook(fis);

		if(workbook.getSheetIndex(sheetName)==-1)
		{
			workbook.createSheet(sheetName);
		}
		sheet = workbook.getSheet(sheetName);
		if(sheet.getRow(rowNum)==null)
		{
			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);
		if(row.getCell(cellNum)==null)
		{
			row.createCell(cellNum);
		}
		cell=row.getCell(cellNum);

		cell.setCellValue(data);

		fos = new FileOutputStream(fpath);
		workbook.write(fos);
		workbook.close();
		fos.close();
		fis.close();

	}


}
