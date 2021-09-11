package com.texoIT.marconato.gra.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer year;

	@Column(length = 250, nullable = false)
	private String title;

	@Column(nullable = false)
	private Boolean winner;
	
	@ManyToMany
    @JoinTable(
            name = "movie_studio",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "studioId"),
            foreignKey = @ForeignKey(name = "movie_studio_fkey"),
            inverseForeignKey = @ForeignKey(name = "studio_movie_fkey")
    )
    @JsonIgnore
    @OrderBy("name")
	private Set<Studio> studios;
	
	@ManyToMany
    @JoinTable(
            name = "movie_producer",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "producerId"),
            foreignKey = @ForeignKey(name = "movie_producer_fkey"),
            inverseForeignKey = @ForeignKey(name = "producer_movie_fkey")
    )
    @JsonIgnore
    @OrderBy("name")
	private Set<Producer> producers;
	
	public Movie(String year, String title, String winner) {
		this.year = this.parseYear(year);
		this.title = title;
		this.winner = this.parseWinner(winner);
	}
	
	private Integer parseYear(String year) {		
		return Integer.valueOf(year);
	}
	
	private Boolean parseWinner(String winner) {		
		return winner != null && winner.equalsIgnoreCase("yes");
	}

}
