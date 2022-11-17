package com.fourserveglobal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fourserveglobal.entity.Book;

public interface BookService {
	List<Book> getAllBooks();

	void saveBooks(Book book);

	Book getBookById(int bookId);

	void deleteBookById(int bookId);

	Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
