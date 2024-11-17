package utilites_OpenCart;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders_OpenCart {
	
	
	public Excelutils_OpenCart utils = new Excelutils_OpenCart(".//testData//DataDriven-TestData.xlsx");
	
	@DataProvider(name="LoginData1")
	public String[][] getLoginData() throws IOException
	{
		int totRows = utils.getRowCount("OpenCart_Login");
		int totCells = utils.getCellCount("OpenCart_Login", totRows);
		
		String loginData[][] = new String[totRows][totCells];
		
		for(int r=1; r<=totRows; r++)
		{
			for(int c=0; c<totCells; c++)
			{
				loginData[r-1][c] = utils.getCellData("OpenCart_Login", r, c);
			}
		}
		return loginData;
		
	}

}
