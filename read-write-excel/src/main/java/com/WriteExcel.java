package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel
{
	private static final String EXCEL_2003 = "xls";
	private static final String EXCEL_2007 = "xlsx";

	public static void writeExcel(String excelPath)
	{
		Workbook wb = null;
		OutputStream out = null;
		try
		{
			File file = new File(excelPath);
			out = new FileOutputStream(file);
			if (excelPath.endsWith(EXCEL_2003))
			{
				wb = new HSSFWorkbook();
			} else if (excelPath.endsWith(EXCEL_2007))
			{
				wb = new XSSFWorkbook();
			} else
			{
				Log.error("File  fomat is not  correct");
				return;
			}

			Sheet sheet = wb.createSheet();
			Row row_1 = sheet.createRow(0);
			Cell cell_1 = row_1.createCell(0);
			Cell cell_2 = row_1.createCell(1);
			Cell cell_3 = row_1.createCell(2);
			Cell cell_4 = row_1.createCell(3);
			cell_1.setCellValue("姓名");
			cell_2.setCellValue("性别");
			cell_3.setCellValue("年龄");
			cell_4.setCellValue("职业");

			for (int i = 1; i < 5; i++)
			{
				Row row = sheet.createRow(i);
				for (int j = 0; j < 4; j++)
				{
					Cell cell = row.createCell(j);
					cell.setCellValue("test");
				}
			}
			wb.write(out);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (out != null)
				{
					out.flush();
					out.close();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args)
	{
		writeExcel("C:/Users/acer/Desktop/test.xlsx");
	}
}
