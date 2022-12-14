package com.grupo01.spring.dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo01.spring.model.Game;

/**
 * 
 * @author Grupo 01
 *
 *         Clase que, al heredar de la clase JpaRepository<Game, Integer>,
 *         hereda los metodos de Spring Data para poder acceder y modificar la
 *         BBDD.
 */

@Repository
public interface GameDAO extends JpaRepository<Game, Integer> {
	
	/**
	 * Buscador de juegos filtrando por genero
	 * @param genre
	 * @return List<Game> de juegos del genero introducido
	 */
	@Query("FROM Game WHERE genre=?1")
	List<Game> findByGenre(String genre);

	/**
	 * 
	 * @return List<Game> de juegos del siglo XX
	 */
	@Query("from Game where year >=1900 and year<=1999 order by year desc")
	List<Game> listSXX();

	/**
	 * 
	 * @return List<Game> de los juegos mas vendidos en Europa
	 */
	@Query("from Game where eu_Sales > (select AVG(eu_Sales) from Game) order by eu_Sales desc")
	List<Game> listEurope();
	
	/**
	 * 
	 * @return List<Publisher> 
	 */
	@Query("select distinct publisher from Game") 
	List<String> listPublishers();
	
	/**
	 * 
	 * @return List<Game> de juegos lanzados en los annos pares
	 */
	@Query("from Game where (year %2) = 0 order by year desc")
	List<Game> showEvenYears();

	/**
	 * 
	 * @return List<Game> de juegos Nintendo
	 */
	@Query("from Game where Platform IN ('WII', 'NES', 'GB', 'DS', 'SNES', 'SNES', 'N64','GC','WIIU','DS3')  order by Platform")
	List<Game> listNintendo();

	Page<Game> findAll(Pageable pageable);


	
}
