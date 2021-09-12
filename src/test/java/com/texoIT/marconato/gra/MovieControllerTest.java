package com.texoIT.marconato.gra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getMoviesSuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movies").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getMovie1SuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movies/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getMovie1000NotFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movies/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void deleteMovie1SuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movies/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteMovie1000NotFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movies/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
