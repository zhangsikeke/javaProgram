package com.ctrl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.MovieDao;
import com.dao.UserRepository;
import com.po.User;
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
		 * for (int i = 0; i < 10; i++) {
		 * 
		 * userDao.save(new User(UUID.randomUUID().toString(),
		 * RandomUtil.generateString(8), RandomUtil.generateLowerString(10) +
		 * "@126.com", RandomUtil.generateString(6),
		 * RandomUtil.generateString(6))); }
		 */
		List<User> userList = userDao.findAll();
		model.addAttribute("userList", userList);
		return "list";
	}

	@RequestMapping("/to/{way}")
	public String forward(@PathVariable String way)
	{
		return way;
	}

	@GetMapping("/init")
	@ResponseBody
	public String init()
	{

		for (int i = 0; i < 10; i++)
		{

			userDao.save(new User(UUID.randomUUID().toString(), RandomUtil.generateString(8),
					RandomUtil.generateLowerString(10) + "@126.com", RandomUtil.generateString(6),
					RandomUtil.generateString(6)));
		}
		return "{'status':'success'}";
	}

	@GetMapping("/getUUID")
	@ResponseBody
	public String getUUID()
	{

		return UUID.randomUUID().toString();
	}
}
