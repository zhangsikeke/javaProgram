package com.conctrol;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.po.User;

@RestController(value="/login")
public class LogDataController
{
	@RequestMapping(value="/logindata",method=RequestMethod.POST)
	public User posthello(HttpServletRequest requst)
	{
		User us=(User)requst.getSession().getAttribute("us");
		System.out.println("us");
		return us;
	}

}
