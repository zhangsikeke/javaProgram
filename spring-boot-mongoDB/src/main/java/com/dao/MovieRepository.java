package com.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.po.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie,String>
{
	@Query(value="{name:?0}")
	public List<Movie> findMoreByName(String name);
	@Query(value="{name:?0}")
	public Movie findByName(String name);
}
