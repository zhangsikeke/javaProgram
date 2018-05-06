package com.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.OrderEntity;
import com.repository.OrderRepository;
import com.util.RandomUtil;

@Controller
public class Ctrler
{
	@Autowired
	private OrderRepository orderRep;;
	
	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	@GetMapping("/init")
	public String init()
	{
		for (int i = 0; i < 10; i++)
		{
			orderRep.save(new OrderEntity("E"+RandomUtil.getCurrentTime()+RandomUtil.generateNumberString(4), RandomUtil.generateString(10), RandomUtil.generateString(10), RandomUtil.generateString(5), "陕西省西安市区昆明路"+RandomUtil.generateNumberString(3)+"号", "1322778"+RandomUtil.generateNumberString(4)))
;		}
		return "redirect:list/1";
	}
	@RequestMapping("/to/{way}")
	public String forward(@PathVariable String way)
	{
		return way;
	}
	@RequestMapping("/list/{index}")
	public String list(Model model,@PathVariable int index)
	{
		List<OrderEntity> orderInfos=orderRep.findAll();
		model.addAttribute("orderInfos", orderInfos);
		return "/list";
	}
	
	public static void main(String[] args)
	{
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
		System.out.println(format.format(new Date()));
	}

}
