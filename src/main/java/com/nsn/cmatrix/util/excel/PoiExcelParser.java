/*
 * Copyright (c) 2010-2012 Zhao.Xiang<z405656232x@163.com> Holding Limited.
 * All rights reserved.
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nsn.cmatrix.util.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFConditionalFormattingRule;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheetConditionalFormatting;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * POI excel parser.
 * 
 * @author Jeccy.Zhao
 *
 */
public class PoiExcelParser extends AbstractExcelParser 
{
	
	private static Logger logger = Logger.getLogger(PoiExcelParser.class);
	
	/**
	 * Input stream
	 */
	private InputStream stream;
	
	/**
	 * Workbook 
	 */
	private Workbook book;
	private Sheet sheet;
	private HSSFSheetConditionalFormatting cfms;
	private HashMap<HSSFCell, String> cfmCells;

	public PoiExcelParser()
	{
		this.book = null;
		this.sheet = null;
		this.cfms = null;
		this.cfmCells = new HashMap<HSSFCell, String>();
		
		this.htmlTbWidth = 1000;
	}

	/**
	 * Returns true if the specified file is excel 2007
	 * 
	 * @param file
	 * @return
	 */
	private boolean isVersion2007 (String file) 
	{
		return file.trim().toLowerCase().endsWith(".xlsx") || file.trim().toLowerCase().endsWith(".xlsm");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.util.excel.AbstractExcelParser#open(java.lang.String)
	 */
	@Override
	public void open (String xlsFile) throws IOException 
	{
		this.xlsFile = xlsFile;
		this.stream = new FileInputStream(this.xlsFile);
		
		if (isVersion2007(this.xlsFile)) 
		{
			this.book = new XSSFWorkbook(this.stream);
		} 
		else 
		{
			this.book = WorkbookFactory.create(new POIFSFileSystem(this.stream));
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.util.excel.AbstractExcelParser#getSheetIndex()
	 */
	@Override
	public Integer getNumberOfSheets () throws Exception
	{
		return this.book.getNumberOfSheets();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.util.excel.AbstractExcelParser#open(int)
	 */
	@Override
	public void openSheet (int sheetIdx) throws Exception
	{
		this.sheet = getSheet(sheetIdx);
	}
	
	public String getCellStyle(int rowIdx, int columnIdx) 
	{
		return getCellStyle(getCell(rowIdx, columnIdx));
	}

	public String getCellAlignment(HSSFCell cell) 
	{
		HSSFCellStyle style = cell.getCellStyle();
		if ((cell != null) && (style != null)) 
		{
			switch (style.getAlignment())
			{
				case 2:
					return "text-align:center;";
				case 1:
					return "text-align:left;";
				case 3:
					return "text-align:right;";
			}
		}
		return "";
	}

	public String getCellFontStyle(HSSFCell cell) 
	{
		HSSFCellStyle style = cell.getCellStyle();
		StringBuilder sb = new StringBuilder();
		if ((cell != null) && (style != null)) 
		{
			HSSFFont font = style.getFont(this.book);

			sb.append(new StringBuilder().append("font-size:").append(
					font.getFontHeightInPoints()).append("pt;").toString());
			sb.append(new StringBuilder().append("font-weight:").append(
					font.getBoldweight()).append(";").toString());

			sb.append("font-family:tahoma;");

			if (font.getItalic())
			{
				sb.append("font-style:italic;");
			}
			
			return sb.toString();
		}
		return "";
	}
	
	public Object getCell(int rowIdx, int columnIdx) {
		Row row = this.sheet.getRow(rowIdx);
		Cell cell = null;
		if (row != null) {
			cell = row.getCell(columnIdx);
		}

		return cell;
	}

	public Object getCellContent(int rowIdx, int columnIdx) {
		return getCellContent(getCell(rowIdx, columnIdx), isVersion2007(this.xlsFile));
	}

	public Object getCellContent(Object cellObj, boolean ver2007) {
		if (cellObj != null) {
			Cell cell = (Cell) cellObj;
			FormulaEvaluator evaluator = ver2007 ? new XSSFFormulaEvaluator((XSSFWorkbook)book) : new HSSFFormulaEvaluator((HSSFWorkbook)book);
			CellValue cellValue = evaluator.evaluate(cell);
			switch (cell.getCellType()) {
			case 0:
				return NumberToTextConverter.toText(cell.getNumericCellValue());
			case 1:
				return cellValue.getStringValue();
			case 4:
				return Boolean.valueOf(cellValue.getBooleanValue());
			case 5:
				return Byte.valueOf(cellValue.getErrorValue());
			case 3:
				break;
			case 2:
				return cellValue.formatAsString();
			default:
				return null;
			}
		}
		return null;
	}

	public Integer getColumnCount() {
		Row headerRow = this.sheet.getRow(1);
		int count = headerRow.getLastCellNum() - headerRow.getFirstCellNum();
		//return Integer.valueOf((this.sheet != null) ? headerRow.getRowNum()
		//		: 0);
		//return null;
		return Integer.valueOf(count);
	}
	
	public Integer getRowCount() {
		int count = this.sheet.getLastRowNum() - this.sheet.getFirstRowNum() + 1;
		//this.sheet.
		//Iterator rows = this.sheet.rowIterator();
		//while (rows.hasNext()) {
		//	++count;
		//}
		return Integer.valueOf(count);
	}
	
	/**
	 * 
	 * @param sheetIdx
	 * @return
	 */
	private Sheet getSheet(int sheetIdx) 
	{
		return book != null ? book.getSheetAt(sheetIdx) : null;
	}

	private Boolean isCellInConditionalFormat(
			HSSFSheetConditionalFormatting cfms, HSSFCell cell) {
		if ((cell != null) && (cfms != null)) {
			int rowIdx = cell.getRowIndex();
			int columnIdx = cell.getColumnIndex();
			if (cfms.getNumConditionalFormattings() > 0) {
				int i = 0;
				for (int len = cfms.getNumConditionalFormattings(); i < len; ++i) {
					
					if (cfms.getConditionalFormattingAt(i).getNumberOfRules() <= 0) {
						continue;
					}
					
					for (int k = 0; k < cfms.getConditionalFormattingAt(i).getFormattingRanges().length; ++k) {
						
						if (!(cfms.getConditionalFormattingAt(i).getFormattingRanges()[k].isInRange(rowIdx,columnIdx))) {
							continue;
						}
						
						for (int j = 0, size = cfms.getConditionalFormattingAt(i).getNumberOfRules(); j < size; ++j) {
							HSSFConditionalFormattingRule rule = cfms.getConditionalFormattingAt(i).getRule(j);
							handleContionalFormatCell(rule, cell);
						}
						return Boolean.valueOf(true);
					}
				}
			}
		}
		return Boolean.valueOf(false);
	}

	@SuppressWarnings("unused")
	private Boolean isCellInConditionalFormat(
			HSSFSheetConditionalFormatting cfms, int rowIdx, int columnIdx) {
		return isCellInConditionalFormat(cfms, (HSSFCell) getCell(rowIdx,
				columnIdx));
	}

	private void handleContionalFormatCell(HSSFConditionalFormattingRule rule,
			HSSFCell cell) {
		if ((cell != null) && (rule != null)) {
			
			Object cellValue = getCellContent(cell, this.isVersion2007(this.xlsFile));
			String formula1 = rule.getFormula1();
			String formula2 = rule.getFormula2();
			
			if (this.cfmCells.containsKey(cell)) {
				return;
			}
			
			if (cellValue == null && cellValue instanceof Double) {
				cellValue = 0.0;
			}
			
			try {
//				switch (cell.getCellType()) {
//					case 0:
//					case 2:
//					case 3:
				switch (rule.getConditionType()) {
					case ExcelConstants.ConditionType.CELL_VALUE_IS:
						switch (rule.getComparisonOperation()) {
							case ExcelConstants.ComparisionType.BETWEEN:		//1
								if (cellValue instanceof Double) {
									if ((Double.parseDouble(cellValue.toString()) >= Double.parseDouble(formula1))
										&& (Double.parseDouble(cellValue.toString()) <= Double.parseDouble(formula2))) {
										return;
									}
								}
								break;
							case ExcelConstants.ComparisionType.EQUALTO:		//3
								if (cellValue instanceof String) {
									if (("\"" + cellValue.toString() + "\"").equals(formula1)) {
										return;
									}
								} else if (cellValue instanceof Double) {
									if (Double.parseDouble(cellValue.toString()) == Double.parseDouble(formula1)) {
										return;
									}
								}
								break;
							case ExcelConstants.ComparisionType.GREATERTHAN:	//5
								if (Double.parseDouble(cellValue.toString()) > Double.parseDouble(formula1)) {
									return;
								}
								break;
							case ExcelConstants.ComparisionType.GREATERTHAN_OR_EQUALTO:		//7
								if (Double.parseDouble(cellValue.toString()) >= Double.parseDouble(formula1)) {
									return;
								}
								break;
							case ExcelConstants.ComparisionType.LESSTHAN:		//6
								if (Double.parseDouble(cellValue.toString()) < Double.parseDouble(formula1)) {
									return;
								}
								break;
							case ExcelConstants.ComparisionType.LESSTHAN_OR_EQUALTO:	//8
								if (Double.parseDouble(cellValue.toString()) <= Double.parseDouble(formula1)) {
									return;
								}
								break;
							case ExcelConstants.ComparisionType.NOT_EQUALTO:			//4
								if (Double.parseDouble(cellValue.toString()) != Double.parseDouble(formula1)) {
									return;
								}
								break;
							case ExcelConstants.ComparisionType.NOTBETWEEN:				//2
								if ((Double.parseDouble(cellValue.toString()) < Double.parseDouble(formula1))
										&& (Double.parseDouble(cellValue.toString()) > Double.parseDouble(formula2))) {
									return;
								}
								break;
						}
					case ExcelConstants.ConditionType.FORMULA_IS:
						break;
				}
//				}
			} catch (Exception e) {
				this.cfmCells.put(cell, "#FFFFFF");
				logger.error("handling the contional format cell errors: " + e);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.util.excel.AbstractExcelParser#getRowData(int)
	 */
	@Override
	public List<ExcelRowData> getRowData(int sheetIndex)
	{
		try 
		{
			this.openSheet(sheetIndex);
			int colNum = this.getColumnCount();
			int rowNum = this.getRowCount();
			
			List<ExcelRowData> rowDataList = new ArrayList<ExcelRowData>();
			
			for(int i = 0; i< rowNum; i++)
			{
				ExcelRowData exRowData = new ExcelRowData();
				for(int j=0; j< colNum; j++)
				{
					Object dat = this.getCell(i, j);
					String style = this.getCellStyle(i, j);
					exRowData.setRowId(i);
					exRowData.setRowData(String.valueOf(this.getCellContent(dat, this.isVersion2007(this.xlsFile))));
					exRowData.setRowStyle(style);
				}
				
				rowDataList.add(exRowData);
			}
			
			return rowDataList;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public void setBook(HSSFWorkbook book) {
		this.book = book;
	}

	public Workbook getBook() {
		return this.book;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Sheet getSheet() {
		return this.sheet;
	}

	public void setCfms(HSSFSheetConditionalFormatting cfms) {
		this.cfms = cfms;
	}

	public HSSFSheetConditionalFormatting getCfms() {
		return this.cfms;
	}

	public void setCfmCells(HashMap<HSSFCell, String> cfmCells) {
		this.cfmCells = cfmCells;
	}

	public HashMap<HSSFCell, String> getCfmCells() {
		return this.cfmCells;
	}

	public static void setLogger(Logger logger) {
		PoiExcelParser.logger = logger;
	}

	public static Logger getLogger() {
		return logger;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.util.excel.AbstractExcelParser#getCellStyle(java.lang.Object)
	 */
	@Override
	public String getCellStyle(Object paramObject)
	{
		return null;
	}
	
	/*
	 * 
	 */
	@Override
	public void close() 
	{
		
	}
}