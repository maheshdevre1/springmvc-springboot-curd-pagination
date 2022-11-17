package com.fourserveglobal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fourserveglobal.entity.Book;
import com.fourserveglobal.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	// display list of Books
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "bookName", "asc", model);
	}

	@GetMapping("/showNewBookForm")
	public String showNewBookForm(Model model) {
		// create model attribute to bind form data
		Book book = new Book();
		model.addAttribute("book", book);
		return "new_book";
	}

	@PostMapping("/saveBook")
	public String saveBook(@ModelAttribute("book") Book book) {
		// save book to database
		bookService.saveBooks(book);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{bookId}")
	public String showFormForUpdate(@PathVariable(value = "bookId") int bookId, Model model) {
		// get book from the service
		Book book = bookService.getBookById(bookId);

		// set book as a model attribute to pre-populate the form
		model.addAttribute("book", book);
		return "update_book";
	}

	@GetMapping("/deleteBook/{bookId}")
	public String deleteBook(@PathVariable(value = "bookId") int bookId) {
		// call delete employee method
		this.bookService.deleteBookById(bookId);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDirection, Model model) {
		int pageSize = 5;

		Page<Book> page = bookService.findPaginated(pageNo, pageSize, sortField, sortDirection);
		List<Book> listBooks = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
		model.addAttribute("listBooks", listBooks);
		return "index";

	}

}
