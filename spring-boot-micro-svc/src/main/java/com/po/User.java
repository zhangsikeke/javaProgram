package com.po;

import java.io.Serializable;

public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String psw;
	private String age;
	
	
	public User()
	{
		super();
	}
	
	

	public User(String name, String psw, String age)
	{
		super();
		this.name = name;
		this.psw = psw;
		this.age = age;
	}



	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getPsw()
	{
		return psw;
	}


	public void setPsw(String psw)
	{
		this.psw = psw;
	}


	public String getAge()
	{
		return age;
	}


	public void setAge(String age)
	{
		this.age = age;
	}



	@Override
	public String toString()
	{
		return "User [name=" + name + ", psw=" + psw + ", age=" + age + "]";
	}
	
	
	
	
}
