package com.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.MovieDao;
import com.dao.UserRepository;
import com.po.Movie;
import com.po.User;
import com.util.FileUtil;
import com.util.RandomUtil;

@Controller
public class Ctrler
{
	@Autowired
	UserRepository userDao;
	@Autowired
	MovieDao movieDao;

	@GetMapping("/")
	public String index(Model model)
	{
		/*
		for (int i = 0; i < 10; i++)
		{

			userDao.save(new User(UUID.randomUUID().toString(), RandomUtil.generateString(8),
					RandomUtil.generateLowerString(10) + "@126.com", RandomUtil.generateString(6),
					RandomUtil.generateString(6)));
		}*/
		/*
		List<String> pathList=new ArrayList<>();
		List<String> nameList=new ArrayList<>();
		FileUtil.getFileList("F:/movies", pathList,nameList);
		int fileSize=nameList.size();
		for (int i = 0; i <fileSize; i++)
		{
			movieDao.save(new Movie(nameList.get(i), pathList.get(i)));
		}*/
		List<Movie> movieList=movieDao.getDao().findAll();
		model.addAttribute("movieList", movieList);
		return "list";
	}
}
