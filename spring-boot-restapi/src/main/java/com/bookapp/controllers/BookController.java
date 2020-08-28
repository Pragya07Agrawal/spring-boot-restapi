package com.bookapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.IdNotFoundException;
import com.bookapp.models.Book;
import com.bookapp.service.BookService;
import com.csi.repository.BookRepository;

@RestController
@RequestMapping("book-restapi")
public class BookController {

	@Autowired
	BookService bookService;
	@Autowired
	BookRepository repo;

	@GetMapping("/greet")
	public ResponseEntity<String> sayHello() {
		String msg = "Welcome to Book App";
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Online Book Application");
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}

	@PostMapping("/books")
	public ResponseEntity<Void> addBook(@RequestBody Book book) {
		bookService.addBook(book);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Adding one book");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
//		 return ResponseEntity.ok().build();
	}

	@GetMapping("/books/{bookid}")
	public ResponseEntity<Book> getBookbyId(@PathVariable int bookid) {
		boolean b = bookService.isBookExist(bookid);
		if (b) {
			Book book = bookService.getBookById(bookid);
			return (ResponseEntity<Book>) ResponseEntity.status(HttpStatus.OK);

		} else {
			throw new BookNotFoundException();
		}

		/*
		 * Book book = bookService.getBookById(bookid); HttpHeaders header = new
		 * HttpHeaders(); header.add("desc","Getting book by id"); return
		 * ResponseEntity.status(HttpStatus.OK).headers(header).body(book);
		 */ }

	@GetMapping("/books-by-author/{author}")
	public ResponseEntity<List<Book>> getBookSByAuthor(@PathVariable("author") String author) {

		List<Book> bookList = bookService.getBooksByAuthor(author);
		return ResponseEntity.ok(bookList);
	}

	@GetMapping("/books-by-category")
	public ResponseEntity<List<Book>> getBookByCategory(@RequestParam("categ ory") String category) {
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "List of books by category");
		header.add("type", "book object");
		List<Book> bookList = bookService.getBooksByCategory(category);
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(bookList);
	}

	public ResponseEntity<Book> getdeltebybookid(int bookid) {
		boolean b1 = bookService.isBookExist(bookid);
		if (b1) {
			bookService.deletebybookid(bookid);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			throw new IdNotFoundException("id is not pressent for delete");
		}

	}

}
