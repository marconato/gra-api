package com.texoIT.marconato.gra.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.texoIT.marconato.gra.domain.Studio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudioDTO {
	
	/**
	 * Código do estúdio
	 */
	private Long id;

	/**
	 * Nome do estúdio
	 */
	private String name;
	
	/**
	 * Lista de filmes do estúdio
	 */
	private List<String> filmes = new ArrayList<>();

	/**
	 * Construtor DTO que recebe o objeto do Domínio de negócio 
	 * @param studio
	 */
	public StudioDTO(Studio studio) {
		this.id = studio.getId();
		this.name = studio.getName();
		this.filmes = studio.getMovies().stream().map(movie -> movie.getTitle()).collect(Collectors.toList());
	}

}
