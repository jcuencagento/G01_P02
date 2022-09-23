package com.grupo01.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.grupo01.spring.model.Game;
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

	/////---GESTION---///////
	
	// Indice
	@GetMapping({ "/", "/games", "", "%" })
	public String listGames(Model model) {
		model.addAttribute("gameList", service.findAll());
		return "GameList";
	}
	
	// Guardar
	@PostMapping("/save")
	public String saveGame(Game game) {
		System.out.println("--------------------------"+game.toString());
		service.save(game);
		return "redirect:/";
	}

	// Save
	@GetMapping("/edit")
	public String editGames(@RequestParam("id") int id, Model model) {
		model.addAttribute("game", service.findById(id));
		System.out.println("--------------------------"+service.findById(id).toString());
		return "GameForm";
	}
	
	// Delete
	@GetMapping("/delete")	
	public String deleteMovies(@RequestParam("id") int id) {
		service.deleteById(id);
		return "redirect:/";
	}
	
	// New
	@GetMapping("/new")
	public String newGame(Game game, Model model) {
		model.addAttribute("game", game);
		return "GameForm";
	}
	
	/////---LISTADOS---///////
	
	// Order by year
}
