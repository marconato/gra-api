package com.texoIT.marconato.gra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.texoIT.marconato.gra.domain.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	public Producer findByName(String name);
	
	/**
	 * Busca somente os produtores que tiveram filmes premiados
	 * @return
	 */
	@Query(value="select p from Producer p inner join p.movies ms where ms.winner = true")
	public List<Producer> findWinnerProducers();

}
