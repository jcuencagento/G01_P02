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
	 * @return String
	 * @author Javier
	 */

	// Indice
	@GetMapping({ "/", "/games", "", "%" })
	public String listGames(Model model) {
		model.addAttribute("gameList", service.findAll());
		return "GameList";
	}

	/**
	 * Metodo saveGames,puede salvar ya sea una modificacion o eliminacion en el
	 * listados de juegos,mediante el metodo save(game) , y nos redirige al
	 * formulario correspondiente(GameList.html)
	 * 
	 * @param game
	 * @return String
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
	 * @return String
	 * @author Ailed
	 */

	@GetMapping("/edit")
	public String editGames(@RequestParam("id") int id, Model model) {
		model.addAttribute("game", service.findById(id));
		System.out.println("--------------------------" + service.findById(id).toString());
		return "GameForm";
	}

	/**
	 * Metodo deleteMovies,puede eliminar un juego,mediante el id con RequestParam y
	 * mediante el metodo deleteById(id) lo enviamos a la capa Service,y nos envia
	 * al formulario correspondiente,en este caso nos quedamos en la propia pagina
	 * principal.
	 * 
	 * @param id
	 * @return String
	 * @author Elina
	 */
	// Delete
	@GetMapping("/delete")
	public String deleteMovies(@RequestParam("id") int id) {
		service.deleteById(id);
		return "redirect:/";
	}

	/**
	 * Metodo newGame,puede guardar un nuevo juego, nos vamos al formulario
	 * correspondiente para introducir la informacion
	 * 
	 * @param game
	 * @param model
	 * @return String
	 * @author Antonio
	 */
	@GetMapping("/new")
	public String newGame(Game game, Model model) {
		model.addAttribute("game", game);
		return "GameForm";
	}

	///// ---LISTADOS---///////
	/**
	 * Metodo orderYear,puede ordenar todos los juegos por su año,lo enviamos a la
	 * capa Service,y nos envia al html correspondiente(YearList.html)
	 * 
	 * @param model
	 * @return String
	 * @author Ailed
	 */
	// Order by year

	@GetMapping("/order")
	public String orderYear(Model model) {
		model.addAttribute("yearList", service.orderYear());
		return "YearList";
	}

	/**
	 * Metodo showSigloXX,muestra todos los juegos desde el año 1900 a 2000,lo
	 * enviamos a la capa Service,y nos envia al html correspondiente que muestra
	 * solo los juegos del siglo XX(ListSXX.html)
	 * 
	 * @param model
	 * @return String
	 * @author Antonio
	 */
	// Order by year <2000 && >1900
	@GetMapping("/sxx")
	public String showSigloXX(Model model) {
		model.addAttribute("listSXX", service.showSXX());
		return "ListSXX";
	}

	/**
	 * Metodo showEurope,muestra todos los juegos que superan la media de ventas de
	 * Europa,lo enviamos a la capa Service,y nos envia al html correspondiente que
	 * muestra solo los juegos que superen esta media(SoldList.html)
	 * 
	 * @param model
	 * @return String
	 * @author Javier
	 */
	// Games Europe
	@GetMapping("/europe")
	public String showEurope(Model model) {
		model.addAttribute("listEurope", service.showEurope());
		return "SoldList";
	}

	/**
	 * Metodo showPublishers,muestra solo los publishers de todos los juegos,lo
	 * enviamos a la capa Service,y nos envia al html correspondiente que muestra
	 * solo los publishers de los juegos(PublisherList.html)
	 * 
	 * @param model
	 * @return String
	 * @author Elina
	 */
	// Publishers
	@GetMapping("/publisher")
	public String showPublishers(Model model) {
		model.addAttribute("listPublishers", service.showPublishers());
		return "PublisherList";
	}

	/**
	 * Metodo showGenre,muestra solo los juegos de su propio genero ,lo enviamos a
	 * la capa Service ,y nos envia al html correspondiente que muestra los juegos
	 * agrupados por generos(GenreList.html)
	 * 
	 * @param genre
	 * @param model
	 * @return String
	 * @author Javier
	 */
	// Games by Genre
	@GetMapping("/genre")
	public String showGenre(@RequestParam("genre") String genre, Model model) {
		model.addAttribute("listGenre", service.showGenre(genre));
		return "GenreList";
	}

	/**
	 * Metodo showEvenYears,muestra solo los juegos de años pares ,lo enviamos a la
	 * capa Service para que sea implementado,y nos envia al html correspondiente
	 * que muestra los juegos por años pares(EvenYears.html)
	 * 
	 * @param model
	 * @return String
	 * @author Antonio
	 */
	// Games by even years
	@GetMapping("/even")
	public String showEvenYears(Model model) {
		model.addAttribute("evenList", service.showEvenYears());
		return "EvenYears";
	}

	/**
	 * Metodo showNintendo,muestra solo los juegos de plataforma Nintendo('WII',
	 * 'NES', 'GB', 'DS', 'SNES', 'SNES', 'N64','GC','WIIU','DS3') ,lo enviamos a la
	 * capa Service para que sea implementado,y nos envia al html correspondiente
	 * que muestra los juegos de Nintendo(NintendoList.html)
	 * 
	 * @param id
	 * @param model
	 * @return String
	 * @author Ailed
	 */
	// juegos nintendo
	@GetMapping("/nintendo")
	public String showNintendo(Model model) {
		model.addAttribute("listNintendo", service.showNintendo());
		return "NintendoList";

	}

}
