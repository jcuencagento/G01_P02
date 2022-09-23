package com.grupo01.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo01.spring.dao.GameDAO;
import com.grupo01.spring.model.Game;

/**
 * 
 * @author Grupo 01
 *
 */

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameDAO gameDAO;

	@Override
	public List<Game> findAll() {
		return gameDAO.findAll();
	}

	@Override
	public void save(Game game) {
		System.out.println("-------SERVICE--------"+game.toString());
		gameDAO.save(game);
	}

	@Override
	public Optional<Game> findById(int id) {
		return gameDAO.findById(id);
	}

	@Override
	public void deleteById(int id) {
		gameDAO.deleteById(id);
	}
  
}
