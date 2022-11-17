package com.fourserveglobal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fourserveglobal.entity.Book;
import com.fourserveglobal.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public void saveBooks(Book book) {
		this.bookRepository.save(book);

	}

	@Override
	public Book getBookById(int bookId) {
		Optional<Book> optional = bookRepository.findById(bookId);
		Book book = null;
		if (optional.isPresent()) {
			book = optional.get();
		} else {
			throw new RuntimeException("Employee not found for id " + bookId);
		}
		return book;
	}

	@Override
	public void deleteBookById(int bookId) {
		this.bookRepository.deleteById(bookId);
	}

	@Override
	public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.bookRepository.findAll(pageable);
	}

}
