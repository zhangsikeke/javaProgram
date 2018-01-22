package com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel
{

	public static void readExcel(String excelPath)
	{
		File file = new File(excelPath);
		Workbook xls = null;
		try
		{
			if (!file.isFile())
			{
				Log.error(excelPath + " is not file");
				return;
			}
			Log.info("parse "+excelPath);
			xls = WorkbookFactory.create(file);
		} catch (InvalidFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		int sheetNum = xls.getNumberOfSheets();
		List<Sheet> sheetList = new ArrayList<Sheet>();
		for (int i = 0; i < sheetNum; i++)
		{
			Sheet sheet = xls.getSheetAt(i);
			sheetList.add(sheet);
			Log.debug("sheet name£º"+sheet.getSheetName());
		}
		for (int i = 0; i < sheetNum; i++)
		{
			Sheet sheet = sheetList.get(i);
			int rowNum = sheet.getLastRowNum();
			for (int j = 0; j < rowNum; j++)
			{
				Row row = sheet.getRow(j);
				int colNum = row.getLastCellNum();
				for (int k = 0; k < colNum; k++)
				{
					System.out.print(ExcelUtil.getCellValue(row.getCell(k))+"   ");
				}
				System.out.println();
			}

		}
	}

	public static void main(String[] args)
	{
		readExcel("./test.xlsx");

	}

}
