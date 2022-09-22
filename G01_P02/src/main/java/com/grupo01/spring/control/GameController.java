package com.grupo01.spring.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo01.spring.util.ScriptBBDD;

/**
 * 
 * @author Grupo 01
 *
 */

@Controller
public class GameController {
	
	//Indice
	@GetMapping({"/","/index","","%"})
	public String index() {
		ScriptBBDD.deCSVaMySQL();
		return "index";
	}
		

}
