package com.zsk.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class Test
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MyService_Service s=new MyService_ServiceLocator();
		try
		{
			System.out.println(s.getMyServicePort().showInfo("zsk"));
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
