package com.ctrl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileCtrl
{
	private final static Logger logger = LoggerFactory.getLogger(FileCtrl.class);

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file)
	{
		if (file.isEmpty())
		{
			return "文件为空";
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		// 文件上传后的路径
		String filePath=null;
		try
		{
			filePath = new File("").getCanonicalPath()+"/userFile/";
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logger.info(filePath);
		// 解决中文问题，liunx下中文路径，图片显示问题
		// fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists())
		{
			dest.getParentFile().mkdirs();
		}
		try
		{
			file.transferTo(dest);
			return "上传成功";
		} catch (IllegalStateException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "上传失败";
	}
}
