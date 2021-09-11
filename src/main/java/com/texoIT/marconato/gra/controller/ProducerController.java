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
import com.texoIT.marconato.gra.service.ProducerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/producers")
public class ProducerController {
	
	@Autowired
	private ProducerService producerService;
	
	@GetMapping
	public ResponseEntity<List<ProducerDTO>> findAll() {
		
		List<Producer> producers = producerService.findAll();
		List<ProducerDTO> producersDTO = producers.stream().map(producer -> new ProducerDTO(producer)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(producersDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProducerDTO> findById(@PathVariable Long id) {
		Producer producer = producerService.findById(id);
		ProducerDTO producerDTO = new ProducerDTO(producer);
		
		return ResponseEntity.ok().body(producerDTO);
	}
	
	@GetMapping("awards")
	public ResponseEntity<List<ProducerDTO>> historyAwards() {
		// Busca os produtores que tiveram filmes premiados, porém ao carregar os produtores todos os filmes dele são carregados
		List<Producer> producers = producerService.historyAwards();
		System.out.println(producers.size());
		
		List<ProducerDTO> producersDTO = producers.stream().map(producer -> new ProducerDTO(producer)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(producersDTO);
	}
	
	@GetMapping("statistic")
	public ResponseEntity<ProducerHistoryAwardsDTO> statisticAwards() {
		return null;
	}

}
