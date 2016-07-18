package com.control;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView; 

import com.contol.bean.User;

import sun.util.logging.resources.logging;  

@Controller
public class LoginController
{  
	private final static Logger logger = Logger.getLogger(LoginController.class);
    @RequestMapping("login.do")
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp,User us)
    {  
       //1���ռ���������֤����  
       //2���󶨲������������  
       //3�������������ҵ��������ҵ����  
       //4��ѡ����һ��ҳ��  
       ModelAndView mv = new ModelAndView();  
       //���ģ������ �����������POJO����  
       mv.addObject("message", "Hello World,I am zhangsike!");
       mv.addObject("user", us); 
       //�����߼���ͼ������ͼ����������ݸ����ֽ������������ͼҳ�� 
       logger.debug(us.getuName()+" "+us.getPsw());
       logger.info(us.getuName()+" "+us.getPsw());
       logger.warn(us.getuName()+" "+us.getPsw());
       logger.error(us.getuName()+" "+us.getPsw());
       mv.setViewName("loginSuccess");
       return mv;  
    }    
}
