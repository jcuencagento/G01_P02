package com.grupo01.spring.service;

import java.util.List;
import java.util.Optional;

import com.grupo01.spring.model.Game;

/**
 * 
 * @author Grupo 01
 *
 */

public interface GameService {
	public List<Game> findAll();
  
	public void saveGame(Game game);
  
	public Optional<Game> findById(int id);

}
