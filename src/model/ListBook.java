package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
public class ListBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "AUTHOR")
	private String author;
	@Column(name = "GENRE")
	private String genre;
	

	public ListBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListBook(String title, String author, String genre) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
	}

	
	public ListBook(String title) { //constructor for deleteBook method
		super();
		this.title = title;
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String returnBookDetails() {
		return "\nTitle is " + title + " by " +author+". Genre is " + genre + ".\n";
	}
}
