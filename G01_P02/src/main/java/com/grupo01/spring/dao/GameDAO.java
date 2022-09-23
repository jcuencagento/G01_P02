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
 *         Clase que, al heredar de la clase JpaRepository<Game, Integer>,
 *         hereda los metodos de Spring Data para poder acceder y modificar la
 *         BBDD.
 */

@Repository
public interface GameDAO extends JpaRepository<Game, Integer> {

	@Query("FROM Game WHERE genre=?1")
	List<Game> findByGenre(String genre);

	@Query("from Game where year >=1900 and year<=1999 order by year desc")
	List<Game> listSXX();

	@Query("from Game where eu_Sales > (select AVG(eu_Sales) from Game) order by eu_Sales desc")
	List<Game> listEurope();
	
	@Query("from Game where (year %2) = 0 order by year desc")
	List<Game> showEvenYears();

	@Query("from Game where Platform IN ('WII', 'NES', 'GB', 'DS', 'SNES', 'SNES', 'N64','GC','WIIU','DS3')  order by Platform")
	List<Game> listNintendo();


	
}
