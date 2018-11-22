package mitrais.yudis.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mitrais.yudis.library.model.Book;
import mitrais.yudis.library.model.BookStatus;
import mitrais.yudis.library.service.IBookService;

@RestController
public class BookController {
	
	private IBookService bookService;

	@Autowired
	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/book")
	public List<Book> retriveAll() {
		return bookService.retriveAll();
	}

	@GetMapping("/book/notshelved")
	public List<Book> retriveNotShelvedBooks() {
		return bookService.retriveByStatus(BookStatus.NOT_SHELVED);
	}
	
	@GetMapping("/book/shelved")
	public List<Book> retriveShelvedBooks() {
		return bookService.retriveByStatus(BookStatus.SHELVED);
	}
	
	@GetMapping("/book/{title}/{status}")
	public List<Book> retriveBooksByTitleAndStatys(@PathVariable String title, @PathVariable BookStatus status) {
		return bookService.retriveByTitleStatus(title, status);
	}
}
