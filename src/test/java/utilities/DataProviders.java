package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException 
	{
		String path = ".\\testData\\OpenCart_LoginData.xlsx"; //taking xl file from test data
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows= xlutil.getRowCount("Sheet1");
		int totalcolumns= xlutil.getCellCount("Sheet1", 1);
		
		String logindata [][] = new String [totalrows][totalcolumns]; // Create a 2D array to store login test data (excluding header row)
		
		// Excel row indexing starts from 1 (excluding row 0 which is typically header)
        // Java arrays start at index 0, so we use i - 1 to align correctly
		for(int i=1; i <= totalrows; i++)  //1
		{ 
			// Columns start from 0 in both Excel and Java, so no adjustment needed for j
			for(int j=0; j < totalcolumns; j++) //0
			{
				logindata [i-1][j] = xlutil.getCellData("Sheet1", i, j); //(1, 0) → logindata[0][0]
			}
		}		
		
		return logindata; //Returning two dimensional array
		
		
		/*
         * ROW NOTES:
         * - Excel data typically starts from row 1 (row 0 = header).
         * - Java arrays are 0-indexed, so use i - 1 to align.
         *
         * COLUMN NOTES:
         * - Columns start at 0 in both Excel and Java.
         * - So j can be used directly without adjustment.
         */
	}
}
