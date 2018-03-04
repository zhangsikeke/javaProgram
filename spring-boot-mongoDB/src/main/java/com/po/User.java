package com.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User
{
	@Id
	private String id;
	@Indexed(name = "user_no")
	private String no;
	private String username;
	private String email;
	private String nickname;
	private String password;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getNo()
	{
		return no;
	}

	public void setNo(String no)
	{
		this.no = no;
	}

	public User()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String no, String username, String email, String nickname, String password)
	{
		super();
		this.no = no;
		this.username = username;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", no=" + no + ", username=" + username + ", email=" + email + ", nickname="
				+ nickname + ", password=" + password + "]";
	}
	
	

}
