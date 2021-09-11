package com.texoIT.marconato.gra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoIT.marconato.gra.domain.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	public Producer findByName(String name);
	
	/**
	 * Busca somente os produtores que tiveram filmes premiados
	 * @return
	 */
	public List<Producer> findByMovies_WinnerIsTrueOrderByName();

}
