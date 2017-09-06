package com.main.java;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController的意思就是controller里面的方法都以json格式输出,不用再写什么jackjson配置的了！
@EnableAutoConfiguration
@RequestMapping(value="/app")
public class App
{

	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public UserBean getHello(HttpServletRequest requst)
	{
		String name=requst.getParameter("uName");
		String age=requst.getParameter("age");
		UserBean us=new UserBean(name,age);
		return us;
	}
	@RequestMapping(value="/hello",method=RequestMethod.POST)
	public UserBean posthello(HttpServletRequest requst)
	{
		String name=requst.getParameter("uName");
		String age=requst.getParameter("age");
		UserBean us=new UserBean(name,age);
		return us;
	}
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
		//http://127.0.0.1:8080/app/hello?uName=zhnagsan&age=100
	}

}
