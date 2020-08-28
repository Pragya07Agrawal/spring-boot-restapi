package com.bookapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.IdNotFoundException;
import com.bookapp.models.Book;
import com.csi.repository.BookRepository;
   
@Service

public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookrepository;
	
	@Override
	public void addBook(Book book) {  
		bookrepository.save(book);
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		/*
		 * List<Book> bookList = getBookList() .stream()
		 * .filter((book)->book.getAuthor().equals(author))
		 * .collect(Collectors.toList()); if(bookList.isEmpty()) { throw new
		 * BookNotFoundException("Book with this author not found")
		 */;
	//	}
//	return bookList;
		  return  bookrepository.findByAuthor(author);
		
	
		
		
	} 
	@Override  
	public Book  getBookById(int bookid) {
		//return null; 
		/*
		 * if(bookId<=0) { throw new RuntimeException("other type of exception"); }
		 * return getBookList() .stream() .filter((book)->book.getBookid()==bookId)
		 * .findAny() .orElseThrow(()-> new IdNotFoundException("Invalid Id")); //
		 * if(opt.isPresent()) {
		 *///			return opt.get();
//		}else {
//			throw new IdNotFoundException("Invalid id");
//		}
		
		  Optional<Book>b =bookrepository.findById(bookid); Book b1=b.get();
		  
		  return b1;
		 
		 }
	@Override
	public List<Book> getBooksByCategory(String category) {
		List<Book> bookList = getBookList()
				.stream()
				.filter((book)->book.getCategory().equals(category))
				.collect(Collectors.toList());
		if(bookList.isEmpty()) {
			throw new BookNotFoundException("Book with this category not found");
		}
	return bookList;
	}
 
	public  List<Book> getBookList(){
		return bookrepository.findAll();
	
				
	
}

	@Override
	public boolean isBookExist( int bookid) {
		// TODO Auto-generated method stub
		return bookrepository.existsById(bookid);
	}

	@Override

	public void deletebybookid(int bookid) {
		 bookrepository.deleteById(bookid);
		// TODO Auto-generated method stub
		
		
	}

				
				
	}
	


