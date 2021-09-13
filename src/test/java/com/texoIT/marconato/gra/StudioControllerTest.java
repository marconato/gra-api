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
public class StudioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getStudiosSuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/studios").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getStudio1SuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/studios/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getStudio1000NotFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/studios/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void deleteStudio1SuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/studios/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isImUsed());
	}
	
	@Test
	public void deleteStudio1000NotFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/studios/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
