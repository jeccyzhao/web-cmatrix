package com.nsn.cmatrix.test.excel;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nsn.cmatrix.util.excel.AbstractExcelParser;
import com.nsn.cmatrix.util.excel.ExcelRowData;
import com.nsn.cmatrix.util.excel.PoiExcelParser;

public class ExcelParserTest 
{
	private AbstractExcelParser parser = new PoiExcelParser();
	
	private String xlsFile = getClass().getResource("NetAct8_communication_matrix.xlsm").getPath();
	
	@Before
	public void init () throws Exception
	{
		parser.open(xlsFile);
	}
	
	@Test
	public void testGetSheetNumber () throws Exception
	{
		int sheetNumber = parser.getNumberOfSheets();
		Assert.assertEquals(26, sheetNumber);
	}
	
	@Test
	public void testGetSheetData () throws Exception
	{
		List<ExcelRowData> sheetRowData = parser.getRowData(3);
		Assert.assertNotNull(sheetRowData);
		
		for (ExcelRowData row : sheetRowData)
		{
			if (row.getRowData() != null)
			{
				for (String data : row.getRowData())
				{
					System.out.print(data);
					System.out.print(",");
				}
				
				System.out.println();
			}
		}
	}
	
	@After
	public void destroy () throws Exception
	{
		
	}

}
