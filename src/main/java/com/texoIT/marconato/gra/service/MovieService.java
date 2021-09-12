package com.texoIT.marconato.gra.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoIT.marconato.gra.domain.Movie;
import com.texoIT.marconato.gra.domain.Producer;
import com.texoIT.marconato.gra.domain.Studio;
import com.texoIT.marconato.gra.exceptions.ObjectNotFoundException;
import com.texoIT.marconato.gra.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private StudioService studioService;
	
	@Autowired
	private ProducerService producerService; 
	
	/**
	 * Serviço para buscar todos os filmes
	 * @return
	 */
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}
	
	/**
	 * Serviço para salvar a entidade filme
	 * @param movie
	 * @return
	 */
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}
	
	/**
	 * Serviço para buscar o filme através da chave primária
	 * @param id
	 * @return
	 */
	public Movie findById(Long id) {
		Optional<Movie> obj = this.movieRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Movie.class.getName()));
	}
	
	/**
	 * Serviço para remover a entidade filme
	 * @param id
	 */
	public void delete(Long id) {
		this.findById(id);
		this.movieRepository.deleteById(id);
	}
	
	/**
	 * Método para importar o arquivo csv de filmes do repositório resources
	 * @throws IOException
	 */
	public void importMoviesFile() throws IOException {
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream("movielist.csv");
		Reader reader = new InputStreamReader(in);
		
		CSVFormat format = CSVFormat.RFC4180.builder()
				.setDelimiter(';')
				.setHeader("year", "title", "studios", "producers", "winner")
				.setIgnoreEmptyLines(true)
				.setSkipHeaderRecord(true)
				.build();
		
		Iterable<CSVRecord> records = format.parse(reader);
		Set<Studio> studioList;
		Set<Producer> producerList;
		Movie movie;
		for (CSVRecord record : records) {
			// Salva a lista de estúdios e retorna o(s) registro(s) com o id
			studioList = this.studioService.saveStudios(record.get("studios"));
			
			// Salva a lista de produtores e retorna o(s) registro(s) com o id
			producerList = this.producerService.saveProducers(record.get("producers"));
			
			movie = new Movie(record.get("year"), record.get("title"), record.get("winner"));
			movie.setStudios(studioList);
			movie.setProducers(producerList);
			// Salva o filme com a lista de estúdios e produtores
			this.save(movie);
		}
	}

}
