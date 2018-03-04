package com.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movie")
public class Movie
{
	@Id
	private String id;
	@Indexed(name = "movie_name")
	private String name;
	private String path;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public Movie(String name, String path)
	{
		super();
		this.name = name;
		this.path = path;
	}

	public Movie()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "Movie [id=" + id + ", name=" + name + ", path=" + path + "]";
	}
	
	

}
