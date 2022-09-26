package com.grupo01.spring;


import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 
 * @author Grupo01
 *
 */
@SpringBootTest
public class Test01_ApplicationTests {
	

	/**
	 * Parte logger
	 * 
	 */
	
	private static byte cont = 1;
	private static Logger logger;
	
	// Inicializo
	static {
		try {
			logger = LogManager.getLogger(Test01_ApplicationTests.class);
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
	
	
	@Test
	public void contextLoads() {
		assertThat(true).isTrue();
	}
	//Ok

}
