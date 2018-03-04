package com.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.po.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>
{
	@Query(value="{username:?0}")
	public List<User> findByUserName(String UserName);
}
