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

import com.texoIT.marconato.gra.domain.Studio;
import com.texoIT.marconato.gra.dto.StudioDTO;
import com.texoIT.marconato.gra.service.StudioService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/studios")
public class StudioController {

	@Autowired
	private StudioService studioService;
	
	@ApiOperation(value = "Retorna a lista de todos os estúdios de filmes")
	@GetMapping
	public ResponseEntity<List<StudioDTO>> findAll() {
		
		List<Studio> studios = studioService.findAll();
		List<StudioDTO> studiosDTO = studios.stream().map(studio -> new StudioDTO(studio)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(studiosDTO);
	}

	@ApiOperation(value = "Retorna o estúdio através do id")
	@GetMapping("/{id}")
	public ResponseEntity<StudioDTO> findById(@PathVariable Long id) {
		Studio studio = studioService.findById(id);
		StudioDTO studioDTO = new StudioDTO(studio);
		
		return ResponseEntity.ok().body(studioDTO);
	}
	
	@ApiOperation(value = "Elminia um estúdio através do id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			studioService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			return new ResponseEntity<Void>(HttpStatus.IM_USED);
	    }
	}
	
}
