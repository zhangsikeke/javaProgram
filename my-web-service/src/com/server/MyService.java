package com.server;

import java.io.File;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;

@WebService(targetNamespace = "http://service.zsk.com", serviceName = "MyService")
public class MyService
{
	
	@Resource
	private static WebServiceContext wsContext;
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	static
	{
		
		System.out.println(new File(MyService.class.getResource("/").getPath()).getParent());
	}
	
	@WebMethod(operationName = "showInfo")
	@WebResult(name = "String")
	public String getinfo(@WebParam(name = "name") String name)
	{
		getClientInfo();
		return "you input is:" + name;
	}

	private void getClientInfo()
	{
		
		try
		{   
			
			MessageContext mc = wsContext.getMessageContext();
			//HttpExchange exchange = (HttpExchange) mc.get(JAXWSProperties.HTTP_EXCHANGE);
			HttpServletRequest request=(HttpServletRequest)mc.get("javax.xml.ws.servlet.request");
			String path=request.getSession().getServletContext().getRealPath("");
			System.out.println(path);
			File f=new File(path+"/WEB-INF/lib/dll/NppShell_05.dll");
			if(f.exists())
			{
				System.out.println("dll exist");
			}
			else
			{
				System.out.println("dll not exist");
			}
			System.out.println(df.format(new Date())+" romote address:" + request.getRemoteAddr()+" port:"+request.getRemotePort());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		Endpoint.publish("http://127.0.0.1:8080/server", new MyService());
		System.out.println("发布成功");
	}
}
