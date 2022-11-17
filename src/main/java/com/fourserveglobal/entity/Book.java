package com.fourserveglobal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book_Table")
public class Book {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
    private int bookId;
	
	@Column(name = "book_Name")
    private String bookName;
	
	@Column(name = "book_Author")
    private String bookAuthor;
	
	@Column(name = "book_Price")
    private String bookPrice;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int bookId, String bookName, String bookPrice, String bookAuthor) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPrice = bookPrice;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	
}
