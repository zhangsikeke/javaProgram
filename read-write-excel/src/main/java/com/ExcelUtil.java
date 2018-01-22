package com;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelUtil
{
	public static String getCellValue(Cell cell)
	{
		if (cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{

			if (DateUtil.isCellDateFormatted(cell))
			{
				// 日期
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm"))
				{
					sdf = new SimpleDateFormat("HH:mm");
				} else
				{
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date d = cell.getDateCellValue();
				return sdf.format(d);
			} else if (cell.getCellStyle().getDataFormat() == 58)
			{
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
				return sdf.format(date);
			} else
			{
				// 普通数字
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				if (temp.equals("General"))
				{
					format.applyPattern("#");
				}
				return format.format(value);
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
		{
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
		{
			return "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA)
		{
			return cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR)
		{
			return String.valueOf(cell.getErrorCellValue());
		}
		return "unknown type value";
	}
}
