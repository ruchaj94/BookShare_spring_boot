package com.example.model;
import javax.persistence.*;

@Entity
@Table(name="books")
public class Book 
{
	private Integer bookId;
	private String bookName,author;
	private boolean availability;
	
	private Genre genre;
	private User owner;
	
	public Book() {
	   System.out.println("in ctor of "+ getClass().getName());
	}

	public Book(Integer bookId, String bookName, String author, boolean availability) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.availability = availability;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id",length=10)
	public Integer getBookId() {
		return bookId;
	}
    
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Column(name="book_name",length=50)
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name="author",length=50)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name="is_available")
	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}


	@ManyToOne
	@JoinColumn(name="genre_id")
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@ManyToOne
	@JoinColumn(name="owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", availability="
				+ availability + "]";
	}
	
	
	

}
