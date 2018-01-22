package com.conctrol;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.po.User;

@Controller(value="/")
public class LoginController
{

	@RequestMapping(value="",method=RequestMethod.GET)
	public String getIndex(HttpServletRequest requst)
	{
		
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String posthello(HttpServletRequest requst,ModelMap map,@ModelAttribute User us)
	{
		map.addAttribute("us", us);
		return "loginSucess";
	}
}
