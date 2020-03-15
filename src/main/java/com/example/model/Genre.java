package com.example.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="genre")
public class Genre {
	
	private Integer genreId;
	private String genreName;
	
	@JsonIgnore
	private List<Book> books = new ArrayList<>();
	
	public Genre() {
	System.out.println("in ctor of "+getClass().getName());
	}

	public Genre(String genreName) {
		super();
		this.genreName = genreName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="genre_id")
	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	@Column(name="genre_name",unique=true)
	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}


	@OneToMany(mappedBy="genre",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + "]";
	}
	
}
