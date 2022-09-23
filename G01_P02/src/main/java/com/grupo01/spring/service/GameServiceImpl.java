package com.grupo01.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow.Publisher;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		System.out.println("-------SERVICE--------" + game.toString());
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

	@Override
	public List<Game> orderYear() {
		return gameDAO.findAll(sortByYear());
	}

	private Sort sortByYear() {
		return Sort.by(Sort.Direction.DESC, "year");
	}

	@Override
	public List<Game> showSXX() {
		return gameDAO.listSXX();
	}
	
	
	@Override
	public List<Game> showEurope(){
		return gameDAO.listEurope();
	}
	
	@Override
	public List<String> showPublishers(){
		return gameDAO.listPublishers();
	}

	//private Sort sortSXX() {
	//	return Sort.by("year");
	//}
	
	
	@Override
	public List<Game> showGenre(String genre) {
		return gameDAO.findByGenre(genre);
	}

  @Override
	public List<Game> showEvenYears() {
		return gameDAO.showEvenYears();
  }

  @Override
	public List<Game> showNintendo() {
		return gameDAO.listNintendo();

	}
  
}
