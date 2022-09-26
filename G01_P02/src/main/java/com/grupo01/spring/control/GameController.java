package com.grupo01.spring.control;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.grupo01.spring.model.Game;
import com.grupo01.spring.service.GameService;
import com.grupo01.spring.util.CSVHelper;
import com.grupo01.spring.util.ScriptBBDD;

import net.bytebuddy.TypeCache.Sort;

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
	
	//Para cargar CSV Opcion 1:
	@GetMapping( "/upload1")
	public String addAllGamesJBDC(Model model) {
		ScriptBBDD.deCSVaMySQL();
		return "redirect:/";
	}
	
	//Para cargar CSV Opcion 2:
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
	 * @return String
	 * @author Javier
	 */

	// Indice
	@GetMapping({"","/"})
	public String listGames(Model model) {
		model.addAttribute("gameList", service.findAll());
		return "GameList";
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Game>> getGames(@PageableDefault(page = 0,
	            size = 30) Pageable pageable) {
	        Page<Game> games = service.findAll(pageable);
	        return ResponseEntity.ok(games);
	    }

	// Guardar

	/**
	 * Metodo saveGames,puede salvar ya sea una modificacion o eliminacion en el
	 * listados de juegos,mediante el metodo save(game) , y nos redirige al
	 * formulario correspondiente(GameList.html)
	 * 
	 * @param game
	 * @return String
	 * @author Elina
	 */
	// Guardar

	@PostMapping("/save")
	public String saveGame(Game game) {
		service.save(game);
		return "redirect:/";
	}

	// Save

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
		System.out.println("-----------EDIT------------" + service.findById(id).toString());
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

	///// ---LISTADOS---///////

	// Order by year

	@GetMapping("/order")
	public String orderYear(Model model) {
		model.addAttribute("yearList", service.orderYear());
		return "YearList";
	}
	
	
	//Order by year <2000 && >1900
	@GetMapping("/sxx")
	public String showSigloXX(Model model) {
		model.addAttribute("listSXX", service.showSXX());
		return "ListSXX";
	}

	//Games Europe
	@GetMapping("/europe")
	public String showEurope(Model model) {
		model.addAttribute("listEurope", service.showEurope());
		return "SoldList";
	}
	
	// Publishers
	@GetMapping("/publisher")
	public String showPublishers(Model model) {
		model.addAttribute("listPublishers", service.showPublishers());
		return "PublisherList";
	}

	//Games by Genre
	@GetMapping("/genre")
	public String showGenre(@RequestParam("genre") String genre, Model model) {
		model.addAttribute("listGenre", service.showGenre(genre));
		return "GenreList";
	}
	
	//Games by even years
	@GetMapping("/even")
	public String showEvenYears(Model model) {
		model.addAttribute("evenList", service.showEvenYears());
		return "EvenYears";
	}

	//juegos nintendo
	@GetMapping("/nintendo")
	public String showNintendo(Model model) {
		model.addAttribute("listNintendo", service.showNintendo());
		return "NintendoList";

	}
  
}
