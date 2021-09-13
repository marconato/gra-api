package com.texoIT.marconato.gra.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoIT.marconato.gra.domain.Movie;
import com.texoIT.marconato.gra.dto.MovieDTO;
import com.texoIT.marconato.gra.service.MovieService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;

	@ApiOperation(value = "Retorna a lista de todos os filmes")
	@GetMapping
	public ResponseEntity<List<MovieDTO>> findAll() {
		
		List<Movie> movies = movieService.findAll();
		List<MovieDTO> moviesDTO = movies.stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(moviesDTO);
	}

	@ApiOperation(value = "Retorna o filme através do id")
	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		Movie movie = movieService.findById(id);
		MovieDTO movieDTO = new MovieDTO(movie);
		
		return ResponseEntity.ok().body(movieDTO);
	}

	@ApiOperation(value = "Elminia um filme através do id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		movieService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
