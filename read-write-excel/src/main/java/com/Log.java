package com;

public class Log
{
	public static void info(Object o)
	{
		System.out.println(" INFO:"+o);
	}
	public static void warn(Object o)
	{
		System.out.println(" WARN:"+o);
	}
	public static void error(Object o)
	{
		System.err.println("ERROR:"+o);
	}
	public static void debug(Object o)
	{
		System.out.println("DEBUG:"+o);
	}
}
