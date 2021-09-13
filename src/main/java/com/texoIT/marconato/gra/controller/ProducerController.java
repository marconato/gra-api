package com.texoIT.marconato.gra.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoIT.marconato.gra.domain.Producer;
import com.texoIT.marconato.gra.dto.ProducerDTO;
import com.texoIT.marconato.gra.dto.ProducerHistoryAwardsDTO;
import com.texoIT.marconato.gra.dto.ProducerMovieWinnerDTO;
import com.texoIT.marconato.gra.service.ProducerService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/producers")
public class ProducerController {
	
	@Autowired
	private ProducerService producerService;
	
	@ApiOperation(value = "Retorna a lista de todos os produtores de filmes")
	@GetMapping
	public ResponseEntity<List<ProducerDTO>> findAll() {
		
		List<Producer> producers = producerService.findAll();
		List<ProducerDTO> producersDTO = producers.stream().map(producer -> new ProducerDTO(producer)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(producersDTO);
	}

	@ApiOperation(value = "Retorna o estúdio através do id")
	@GetMapping("/{id}")
	public ResponseEntity<ProducerDTO> findById(@PathVariable Long id) {
		Producer producer = producerService.findById(id);
		ProducerDTO producerDTO = new ProducerDTO(producer);
		
		return ResponseEntity.ok().body(producerDTO);
	}
	
	@ApiOperation(value = "Retorna a lista de todos os ganhadores do prêmio de pior filme")
	@GetMapping("awards")
	public ResponseEntity<List<ProducerMovieWinnerDTO>> historyAwards() {
		// Busca os produtores que tiveram filmes premiados
		List<ProducerMovieWinnerDTO> producerMovieWinners = producerService.historyAwards();
		return ResponseEntity.ok().body(producerMovieWinners);
	}
	
	@ApiOperation(value = "Obtém uma lista com o(s) produtor(es) com maior intervalo entre dois prêmios consecutivos, "
			+ "e o(s) que obtiveram dois prêmios mais rápido")
	@GetMapping("breakaward")
	public ResponseEntity<ProducerHistoryAwardsDTO> breakAward() {
		ProducerHistoryAwardsDTO producerHistoryAwardsDTO = producerService.breakAward();
		return ResponseEntity.ok().body(producerHistoryAwardsDTO);
	}

}
