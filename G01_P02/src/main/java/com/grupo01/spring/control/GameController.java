package com.grupo01.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.grupo01.spring.service.GameService;

/**
 * 
 * @author Grupo 01
 *
 */

@Controller
public class GameController {
	
	@Autowired
	GameService service;
	
	//Indice
	@GetMapping({"/","/games","","%"})
	public String listGames(Model model) {
		model.addAttribute("gameList", service.findAll());
		return "GameList";
	}
		
	
	
	
	
	
	
	
	
	//Guardar
	@PostMapping("/save")
	public String saveGame(Game game) {
		service.save(game);
		return ("redirect:/");
	}

}
