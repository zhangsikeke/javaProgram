package com.util;

import java.io.File;
import java.util.List;

public class FileUtil
{
	public static void getFileList(String dirPath, List<String> pathlist,List<String> nameList)
	{
		try
		{
			File file = new File(dirPath);
			File[] files = file.listFiles();
			if (files == null)
				return;
			for (File f : files)
			{
				if (f.isDirectory())
				{
					getFileList(f.getCanonicalPath(), pathlist,nameList);
				} else
				{
					pathlist.add(f.getCanonicalPath());
					nameList.add(f.getName());
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
