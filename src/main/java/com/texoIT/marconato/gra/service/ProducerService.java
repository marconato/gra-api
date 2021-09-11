package com.texoIT.marconato.gra.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoIT.marconato.gra.domain.Movie;
import com.texoIT.marconato.gra.domain.Producer;
import com.texoIT.marconato.gra.domain.Studio;
import com.texoIT.marconato.gra.repository.ProducerRepository;
import com.texoIT.marconato.gra.service.exception.ObjectNotFoundException;

@Service
public class ProducerService {

	@Autowired
	private ProducerRepository producerRepository;
	
	/**
	 * Serviço para salvar a entidade produtor
	 * @param producer
	 * @return
	 */
	public Producer save(Producer producer) {
		return producerRepository.save(producer);
	}
	
	/**
	 * Serviço para buscar todos os produtores
	 * @return
	 */
	public List<Producer> findAll() {
		return producerRepository.findAll();
	}
	
	/**
	 * Serviço para salvar uma lista de produtores
	 * @param producers
	 * @return
	 */
	public Set<Producer> saveProducers(String producers) {
		Set<Producer> producersList = new HashSet<>();
		Producer producer;
		for (String p : producers.split(",|\\ and ")) {
			p = p.trim();
			producer = this.producerRepository.findByName(p);
			if (producer == null) {
				producer = producerRepository.save(new Producer(p));
			}
			producersList.add(producer);
		}
		
		return producersList;
	}
		
	/**
	 * Serviço para buscar o produtor através da chave primária
	 * @param id
	 * @return
	 */
	public Producer findById(Long id) {
		Optional<Producer> obj = this.producerRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Studio.class.getName()));
	}
	
	public List<Producer> historyAwards() {
		List<Producer> awards = this.producerRepository.findByMovies_WinnerIsTrueOrderByName();
		
		// Percorre a lista de produtores para atualizar a lista e manter somente os filmes vencedores do prêmio
		awards.stream().forEach(producer -> {
			List<Movie> movies = producer.getMovies().stream().filter(movie -> movie.getWinner()).collect(Collectors.toList());
			producer.setMovies(new HashSet<Movie>(movies));
		});
		
		return awards;
	}

}
