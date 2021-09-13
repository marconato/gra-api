package com.texoIT.marconato.gra.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoIT.marconato.gra.domain.Movie;
import com.texoIT.marconato.gra.domain.Producer;
import com.texoIT.marconato.gra.domain.Studio;
import com.texoIT.marconato.gra.dto.ProducerAwardsDTO;
import com.texoIT.marconato.gra.dto.ProducerHistoryAwardsDTO;
import com.texoIT.marconato.gra.dto.ProducerMovieWinnerDTO;
import com.texoIT.marconato.gra.exceptions.ObjectNotFoundException;
import com.texoIT.marconato.gra.repository.ProducerRepository;

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
	
	/**
	 * Método usado para apresentar o histórico dos campeões do prêmio
	 * @return
	 */
	public List<ProducerMovieWinnerDTO> historyAwards() {
		// Obtém os produtores que venceram prêmios
		List<Producer> producers = this.producerRepository.findWinnerProducers();
		
		List<ProducerMovieWinnerDTO> producerMovieWinners = new ArrayList<>();
		ProducerMovieWinnerDTO producerMovieWinnerDTO;
		for (Producer producer : producers) {
			for (Movie movie : producer.getMovies()) {
				// Inclui somente os filmes que foram campeões
				if (movie.getWinner()) {
					producerMovieWinnerDTO = new ProducerMovieWinnerDTO(producer, movie);
					// Inclui somente se o produtor e o filme ainda não foram incluídos na lista
					if (!producerMovieWinners.contains(producerMovieWinnerDTO)) {
						producerMovieWinners.add(producerMovieWinnerDTO);
					}
				}
			}
		}
		
		return producerMovieWinners;
	}
	
	/**
	 * Busca o intervalo dos prêmios
	 * @return
	 */
	public ProducerHistoryAwardsDTO breakAward() {

		ProducerHistoryAwardsDTO awardsDTO = new ProducerHistoryAwardsDTO();
		List<ProducerMovieWinnerDTO> winners = this.historyAwards();

		awardsDTO.addMin(this.findBreakAward(winners, true));
		awardsDTO.addMax(this.findBreakAward(winners, false));
		
		return awardsDTO;
	}
	
	/**
	 * Monta a regra de negócio para obter o intervalo dos prêmios
	 * @param winners
	 * @param isMin
	 * @return
	 */
	private ProducerAwardsDTO findBreakAward(List<ProducerMovieWinnerDTO> winners, boolean isMin) {
		ProducerAwardsDTO producerAwardsDTO = new ProducerAwardsDTO();
		producerAwardsDTO.setInterval(isMin ? Integer.MAX_VALUE : Integer.MIN_VALUE);
		
		ProducerMovieWinnerDTO previous;
		ProducerMovieWinnerDTO following;
		for (int i = 0; i < winners.size(); i++) {
			for (int j = 0; j < winners.size(); j++) {
				previous = winners.get(i);
				following = winners.get(j);
				
				// Se o nome do produtor for o mesmo e o filme for diferetne
				if (previous.getProducer().equals(following.getProducer()) 
						&& previous.getMovie() != following.getMovie()) {
					
					// The java.lang.Math.abs() returns the absolute value of a given argument. 
					// If the argument is not negative, the argument is returned.
					// If the argument is negative, the negation of the argument is returned
					Integer interval = Math.abs(previous.getYear() - following.getYear());
					
					if (isMin) {
						if (interval < producerAwardsDTO.getInterval()) {
							producerAwardsDTO.setInterval(interval);
							producerAwardsDTO.setProducer(previous.getProducer());
							producerAwardsDTO.setPreviousWin(previous.getYear());
							producerAwardsDTO.setFollowingWin(following.getYear());
						}
					} else {
						if (interval > producerAwardsDTO.getInterval()) {
							producerAwardsDTO.setInterval(interval);
							producerAwardsDTO.setProducer(previous.getProducer());
							producerAwardsDTO.setPreviousWin(previous.getYear());
							producerAwardsDTO.setFollowingWin(following.getYear());
						}
					}
					
				}
			}
		}
		
		return producerAwardsDTO;
	}

}
