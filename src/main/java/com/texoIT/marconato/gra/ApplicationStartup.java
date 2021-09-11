package com.texoIT.marconato.gra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.texoIT.marconato.gra.service.MovieService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	
	final static Logger logger = LogManager.getLogger(ApplicationStartup.class.getName());

	@Autowired
	private MovieService movieService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("Starting import csv movies file");
		try {
			movieService.importMoviesFile();
			
			logger.info("Finished successfully import csv movies file!");
		} catch (Exception e) {
			logger.error("Error on import csv movies file", e);
		}		
	}

}
