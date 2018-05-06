package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, String>
{
	public List<OrderEntity> findByOrderNo(String orderNo);
}
