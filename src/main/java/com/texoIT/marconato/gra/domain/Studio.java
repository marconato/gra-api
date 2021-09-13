package com.texoIT.marconato.gra.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Studio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 250, nullable = false)
	private String name;
		
	@ManyToMany(mappedBy = "studios")
	@OrderBy("title")
    @JsonIgnore
	private Set<Movie> movies;
	
	public Studio(String name) {
		this.name = name;
	}
}
