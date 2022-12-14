package com.grupo01.spring.control;


import java.util.Optional;

import java.io.File;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.grupo01.spring.model.Game;
import com.grupo01.spring.service.GameService;
import com.grupo01.spring.util.CSVHelper;
import com.grupo01.spring.util.ScriptBBDD;

/**
 * 
 * @author Grupo 01
 *
 */

@Controller
public class GameController {

	@Autowired
	GameService service;
	
	@Autowired
	ScriptBBDD script;
	
	@Autowired
	CSVHelper csvhelper;
	
	////// ----METODOS DE CARGA----- /////
	
	/**
	 * Esos dos metodos reciben un model y te devuelven a la pagina principal con la carga del archivo csv elegido.
	 * Solo es necesario cargarlos una vez (ya lo estan).
	 * 
	 * @param model
	 * @return
	 */
	
	//Para cargar CSV Opcion 1:
	//Mas rapido -> usamos Column en Game.java
	@GetMapping( "/upload1")
	public String addAllGamesJBDC(Model model) {
		ScriptBBDD.deCSVaMySQL();
		return "redirect:/";
	}
	
	//Para cargar CSV Opcion 2:
	//Mas lento -> usamos CSV Binder en Game.java
	@GetMapping( "/upload2")
	public String addAllGamesCSVRecord(Model model) {
		File file = ScriptBBDD.buscar("complete.csv", Paths.get(".").toFile());
		csvhelper.csvToGames(file);
		return "redirect:/";
	}
	

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

	@GetMapping("/")
	public String findByPage(Model model, Integer pageNum) { // Cuando la p??gina actual est??
		// vac??a, asigne un valor de
		// uno, lo que significa que la
		// p??gina actual es la primera
		// p??gina
		if (pageNum == null) {

			pageNum = 1;
		}
		Pageable pageable = PageRequest.of(pageNum - 1, 100);
		Page<Game> page = service.findAllByPage(pageable);
		model.addAttribute("gameList", page);

		return "GameList";
	}

	/**
	 * Metodo descriptionGamesrecibe el id de juego y redirige a la pagina de desctipcion del juego.)
	 * 
	 * @param id
	 * @return Game 
	 * @author Elina
	 */
	@GetMapping("/description")
		public String descriptionGames(@RequestParam("id") int id, Model model) {
		Optional<Game> optGame = service.findById(id);
		optGame.ifPresent(game -> {
			System.out.println("--------Description Control-----------" + game);
			model.addAttribute("game", game);		
		});
			return "GameInfo";
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
	 * @param game, model
	 * @author Javier
	 */


	@GetMapping("/new")
	public String newGame(Game game, Model model) {
		model.addAttribute("game", game);
		return "GameForm";
	}

	///// ---LISTADOS---///////

	/**
	 * Metodo orderYear,puede ordenar todos los juegos por su a??o,lo enviamos a la
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
	 * Metodo showSigloXX,muestra todos los juegos desde el a??o 1900 a 2000,lo
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
	

	@GetMapping("/publisher")
	public String showPublishers(Model model) {
		model.addAttribute("listPublishers", service.showPublishers());
		return "PublisherList";
	}

	// Games by Genre

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
	 * Metodo showEvenYears,muestra solo los juegos de a??os pares ,lo enviamos a la
	 * capa Service para que sea implementado,y nos envia al html correspondiente
	 * que muestra los juegos por a??os pares(EvenYears.html)
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
