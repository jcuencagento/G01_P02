package com.grupo01.spring.service;

import java.util.List;

import com.grupo01.spring.model.Game;

/**
 * 
 * @author Grupo 01
 *
 */

public interface GameService {
	public List<Game> findAll();
	
	
	
	
	
	
	
	public void saveGame(Game game);

}
