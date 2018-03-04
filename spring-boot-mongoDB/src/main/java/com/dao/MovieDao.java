package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.po.Movie;

@Repository
public class MovieDao
{
	@Autowired
	private MovieRepository dao;

	public Movie save(Movie entity)
	{
		Movie movie = dao.findByName(entity.getName());
		if (movie != null)
		{
			entity.setId(movie.getId());
		}
		return dao.save(entity);
	}

	public MovieRepository getDao()
	{
		return dao;
	}

	public void setDao(MovieRepository dao)
	{
		this.dao = dao;
	}

}
