package com.texoIT.marconato.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoIT.marconato.gra.domain.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long> {
	
	public Studio findByName(String name);

}
