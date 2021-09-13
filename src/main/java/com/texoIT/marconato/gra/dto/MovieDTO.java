package com.texoIT.marconato.gra.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.texoIT.marconato.gra.domain.Movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MovieDTO {
	
	private Long id;

	private Integer year;

	private String title;

	private List<String> studios = new ArrayList<>();

	private List<String> producers = new ArrayList<>();

	private Boolean winner;
	
	public MovieDTO(Movie movie) {
		
		this.id = movie.getId();
		this.year = movie.getYear();
		this.title = movie.getTitle();
		this.winner = movie.getWinner();
		this.studios = movie.getStudios().stream().map(studio -> studio.getName()).collect(Collectors.toList());	
		this.producers = movie.getProducers().stream().map(producer -> producer.getName()).collect(Collectors.toList());		
	}

}
