package com.main.java;

import java.io.Serializable;

public class UserBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uName;
	private String age;
	
	
	public UserBean()
	{
		super();
	}
	public UserBean(String uName, String age)
	{
		super();
		this.uName = uName;
		this.age = age;
	}
	public String getuName()
	{
		return uName;
	}
	public void setuName(String uName)
	{
		this.uName = uName;
	}
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	
}
