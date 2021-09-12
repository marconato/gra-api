package com.texoIT.marconato.gra.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoIT.marconato.gra.domain.Studio;
import com.texoIT.marconato.gra.exceptions.ObjectNotFoundException;
import com.texoIT.marconato.gra.repository.StudioRepository;

@Service
public class StudioService {

	@Autowired
	private StudioRepository studioRepository;
	
	/**
	 * Serviço para buscar todos os estúdios
	 * @return
	 */
	public List<Studio> findAll() {
		return studioRepository.findAll();
	}
	
	/**
	 * Serviço para salvar a entidade estúdio
	 * @param studio
	 * @return
	 */
	public Studio save(Studio studio) {
		return studioRepository.save(studio);
	}
	
	/**
	 * Serviço para salvar uma lista de estúdios
	 * @param studios
	 * @return
	 */
	public Set<Studio> saveStudios(String studios) {
		Set<Studio> studioList = new HashSet<>();
		Studio studio;
		for (String s : studios.split(",|\\ and ")) {
			s = s.trim();
			studio = this.studioRepository.findByName(s);
			if (studio == null) {
				studio = studioRepository.save(new Studio(s));
			}
			studioList.add(studio);
		}
		
		return studioList;
	}
	
	/**
	 * Serviço para buscar o estúdio através da chave primária
	 * @param id
	 * @return
	 */
	public Studio findById(Long id) {
		Optional<Studio> obj = this.studioRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Studio.class.getName()));
	}
	
	/**
	 * Serviço para remover a entidade estúdio
	 * @param id
	 */
	public void delete(Long id) {
		this.findById(id);
		this.studioRepository.deleteById(id);
	}
}
