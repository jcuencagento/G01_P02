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

	///// ---GESTION---///////

	/**
	 * Metodo listGames,guardamos todos los juegos en gameList mediante el metodo
	 * findAll()) y lo enviamos a la capa Service, y nos envia al formulario
	 * correspondiente(GameList.html)
	 * 
	 * @param model
	 * @return List<Game> 
	 * @author Javier
	 */

	// Indice
	@GetMapping({ "/", "/games", "", "%" })
	public String listGames(Model model) {
		model.addAttribute("gameList", service.findAll());
		return "GameList";
	}


	/**
	 * Metodo saveGames,puede guardar ya sea una modificacion o eliminacion en el
	 * listados de juegos,mediante el metodo save(game) , y nos redirige al
	 * formulario correspondiente(GameList.html)
	 * 
	 * @param game
	 * @author Elina
	 */

	@PostMapping("/save")
	public String saveGame(Game game) {
		System.out.println("--------------------------" + game.toString());
		service.save(game);
		return "redirect:/";
	}


	/**
	 * Metodo editGames,puede modificar todos los campos de un juego,guardamos el id
	 * con RequestParam y mediante el metodo findById(id) lo enviamos a la capa
	 * Service,y nos envia al formulario correspondiente(GameForm.html)
	 * 
	 * @param id
	 * @param model
	 * @return GameForm.html
	 * @author Ailed
	 */

	@GetMapping("/edit")
	public String editGames(@RequestParam("id") int id, Model model) {
		model.addAttribute("game", service.findById(id));
		System.out.println("--------------------------" + service.findById(id).toString());
		return "GameForm";
	}

	
	/**
	 * Metodo deleteGame sirve para borrar un registro de juego. Se recibe el id del juego
	 * con RequestParam y mediante el metodo deleteById(id) se lo borra de la base de datos.
	 * 
	 * @param id
	 * @author Javier
	 */
	
	@GetMapping("/delete")
	public String deleteGame(@RequestParam("id") int id) {
		service.deleteById(id);
		return "redirect:/";
	}

	
	/**
	 * Metodo newGame sirve para annadir un nuevo juego a la base de datos. Se inicia el metodo
	 * dando al boton "New game" que lleva el usuario al formulario GameForm. Mediante el formulario
	 * se introduce los datos del nyevo juego. 
	 * 
	 * @param id
	 * @author Javier
	 */

	@GetMapping("/new")
	public String newGame(Game game, Model model) {
		model.addAttribute("game", game);
		return "GameForm";
	}

	///// ---LISTADOS---///////


	/**
	 * Metodo orderYear ordena juegos por anno de lanzamiento.
	 * 
	 * @param genre
	 * @return String???
	 * @author Ailed
	 */

	@GetMapping("/order")
	public String orderYear(Model model) {
		model.addAttribute("yearList", service.orderYear());
		return "YearList";
	}
	
	
	/**
	 * Metodo showSigloXX devuelve los juegos lanzados en el siglo XX ordenados por el anno 
	 * de lanzamiento.
	 * 
	 * @param genre
	 * @return List<Game>
	 * @author Elina
	 */
	//Order by year <2000 && >1900
	@GetMapping("/sxx")
	public String showSigloXX(Model model) {
		model.addAttribute("listSXX", service.showSXX());
		return "ListSXX";
	}

	/**
	 * Metodo devuelve el listado de distintos publishers que se encuentran en la base de datos.
	 * 
	 * @param genre
	 * @return List<Game>
	 * @author Elina
	 */
	
	@GetMapping("/europe")
	public String showEurope(Model model) {
		model.addAttribute("listEurope", service.showEurope());
		return "SoldList";
	}
	
	
	/**
	 * Metodo devuelve el listado de distintos publishers que se encuentran en la base de datos.
	 * 
	 * @param genre
	 * @return List<Publisher>
	 * @author Elina
	 */
	
	@GetMapping("/publisher")
	public String showPublishers(Model model) {
		model.addAttribute("listPublishers", service.showPublishers());
		return "PublisherList";
	}


	/**
	 * Metodo showGenre recibe como parametro el genero del juego devuelve el listado de juegos
	 * que pertenecen a ese genero.
	 * 
	 * @param genre
	 * @return List<Game>
	 * @author Javier
	 */
	
	@GetMapping("/genre")
	public String showGenre(@RequestParam("genre") String genre, Model model) {
		model.addAttribute("listGenre", service.showGenre(genre));
		return "GenreList";
	}
	
	
	/**
	 * Metodo showEvenYears devuelve el listado de juegos lanzados en los annos pares.
	 * 
	 * @param model
	 * @return List<Game>
	 * @author Antonio
	 */
	
	@GetMapping("/even")
	public String showEvenYears(Model model) {
		model.addAttribute("evenList", service.showEvenYears());
		return "EvenYears";
	}

	
	/**
	 * Metodo showNintendo devuelve el listado de juegos desarrollados por Nuntendo.
	 * 
	 * @param model
	 * @return List<Game>
	 * @author Ailed
	 */
	
	@GetMapping("/nintendo")
	public String showNintendo(Model model) {
		model.addAttribute("listNintendo", service.showNintendo());
		return "NintendoList";

	}
  
}
