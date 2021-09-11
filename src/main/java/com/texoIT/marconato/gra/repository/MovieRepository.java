package com.texoIT.marconato.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texoIT.marconato.gra.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
