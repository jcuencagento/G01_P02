package com.grupo01.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo01.spring.model.Game;


/**
 * 
 * @author Grupo 01
 *
 */

@Repository
public interface GameDAO extends JpaRepository<Game, Integer>{
	
	@Query("FROM Game WHERE genre=?1")
	List<Game> findByGenre(String genre);

}
