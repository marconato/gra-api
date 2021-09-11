package com.texoIT.marconato.gra.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.texoIT.marconato.gra.domain.Producer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProducerDTO {
	
	/**
	 * Código do produtor
	 */
	private Long id;

	/**
	 * Nome do produtor
	 */
	private String name;
	
	/**
	 * Lista de filmes do produtor
	 */
	private List<MovieDTO> filmes = new ArrayList<>();
	
	/**
	 * Construtor DTO que recebe o objeto do Domínio de negócio
	 * @param producer
	 */
	public ProducerDTO(Producer producer) {
		this.id = producer.getId();
		this.name = producer.getName();
		this.filmes = producer.getMovies().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
	}
	
	/**
	 * Construtor DTO que recebe o objeto do Domínio de negócio e um parametro para filtrar por vencedor
	 * @param producer
	 *	Filtro para carregar somente os filmes premiados 
	 * @param winner
	 */
	public ProducerDTO(Producer producer, boolean winner) {
		this.id = producer.getId();
		this.name = producer.getName();
		this.filmes = producer.getMovies().stream().filter(movie -> movie.getWinner() == winner).map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
	}

}
