package com.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "order_info")
public class OrderEntity
{
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column(name = "order_no")
	private String orderNo;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();
	@Column(name = "accept_name")
	private String acceptName;
	@Column(name = "accept_address")
	private String acceptAddress;
	@Column(name = "accept_phone")
	private String acceptPhone;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getAcceptName()
	{
		return acceptName;
	}

	public void setAcceptName(String acceptName)
	{
		this.acceptName = acceptName;
	}

	public String getAcceptAddress()
	{
		return acceptAddress;
	}

	public void setAcceptAddress(String acceptAddress)
	{
		this.acceptAddress = acceptAddress;
	}

	public String getAcceptPhone()
	{
		return acceptPhone;
	}

	public void setAcceptPhone(String acceptPhone)
	{
		this.acceptPhone = acceptPhone;
	}

	public OrderEntity()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderEntity(String orderNo, String userId, String userName, String acceptName, String acceptAddress,
			String acceptPhone)
	{
		super();
		this.orderNo = orderNo;
		this.userId = userId;
		this.userName = userName;
		this.acceptName = acceptName;
		this.acceptAddress = acceptAddress;
		this.acceptPhone = acceptPhone;
	}

}
