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
				// ����
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
				// �����Զ������ڸ�ʽ��m��d��(ͨ���жϵ�Ԫ��ĸ�ʽid�����id��ֵ��58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
				return sdf.format(date);
			} else
			{
				// ��ͨ����
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
