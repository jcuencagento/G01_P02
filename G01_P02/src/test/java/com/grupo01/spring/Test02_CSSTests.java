package com.grupo01.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.grupo01.spring.control.GameController;


/**
 * 
 * @author Grupo01
 *
 */
@WebMvcTest(GameController.class)
public class Test02_CSSTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception{
		mockMvc.perform(get("/css/estilos.css"))
				   .andDo(print())
				   .andExpect(status().isOk());
		
	}

}
