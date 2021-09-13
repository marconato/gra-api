package com.texoIT.marconato.gra.dto;

import java.util.Objects;

import com.texoIT.marconato.gra.domain.Movie;
import com.texoIT.marconato.gra.domain.Producer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProducerMovieWinnerDTO {
	
	private String producer;
	
	private String movie;
	
	private int year;
	
	public ProducerMovieWinnerDTO(Producer producer, Movie movie) {
		this.producer = producer.getName();
		this.movie = movie.getTitle();
		this.year = movie.getYear();
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, producer, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProducerMovieWinnerDTO other = (ProducerMovieWinnerDTO) obj;
		return Objects.equals(movie, other.movie) && Objects.equals(producer, other.producer) && year == other.year;
	}
	
	

}
