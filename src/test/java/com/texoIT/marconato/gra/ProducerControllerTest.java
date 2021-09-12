package com.texoIT.marconato.gra;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
class ProducerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void breakaward() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/producers/breakaward")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.min.*.producer", hasItem(is("Joel Silver"))))
				.andExpect(jsonPath("$.min.*.interval", hasItem(is(1))))
				.andExpect(jsonPath("$.min.*.previousWin", hasItem(is(1991))))
				.andExpect(jsonPath("$.min.*.followingWin", hasItem(is(1990))))
				.andExpect(jsonPath("$.max.*.producer", hasItem(is("Matthew Vaughn"))))
				.andExpect(jsonPath("$.max.*.interval", hasItem(is(13))))
				.andExpect(jsonPath("$.max.*.previousWin", hasItem(is(2015))))
				.andExpect(jsonPath("$.max.*.followingWin", hasItem(is(2002))));
	}
	
	@Test
	public void getProducersSuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/producers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getAwardsSuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/producers/awards").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getProducer1SuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/producers/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getProducer1000NotFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/producers/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void deleteProducer1SuccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/producers/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteProducer1000NotFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/producers/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
