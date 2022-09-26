package com.grupo01.spring;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo01.spring.dao.GameDAO;
import com.grupo01.spring.model.Game;

import data.ListadoJuegos;
import model.Juegos;
import util.ExcepcionJuegoSinNombre;


/**
 * Test de Servicios con la clase Servicios que tiene los metodos provenientes
 * del Control y que posteriormente llamaran a Datos tras manejar (o no)
 * diferentes servicios o funciones
 * 
 * @author Grupo 1
 *
 */
public class TestServicios {

	/**
	 * Parte logger
	 * 
	 */
	
	private static byte cont = 1;
	private static Logger logger;
	
	// Inicializo
	static {
		try {
			logger = LogManager.getLogger(TestServicios.class);
		} catch (Throwable e) {
			System.out.println("Don't work");
		}
	}
	
	@BeforeAll
	public static void onceExecutedBeforeAll() {
		logger.info(">>> Iniciando las pruebas");
	}
	
	@BeforeEach
	public void executedBeforeEach() {
		System.out.println("");
		logger.info("=== PRUEBA "+(cont++)+" ======");
	}
	
	@AfterAll
	public static void onceExecutedAfterAll() {
		System.out.println("");
		logger.info(">>> Terminado las pruebas");
	}
	
	
	/**
	 * Parte testing
	 * 
	 */
	
	
	/**
	 * Introducimos a altaJuego un juego vacio (va a ser vacio por el year incorrecto)
	 * y comprobamos que una lista vacia y una queriendo sumar un juego vacio es lo mismo.
	 * 
	 */
	@Test
	public void testListarVacio() {
		logger.warn("Test::testListarVacio(): List<Game>");
		
		GameDAO gameDAO=new GameDAO();
		List<Game> listaJuegos1 = null;
		
		Assert.assertEquals(listaJuegos1,gameDAO.findAll());
	}

	
}
