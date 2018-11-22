package mitrais.yudis.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitrais.yudis.library.model.Book;
import mitrais.yudis.library.model.BookStatus;
import mitrais.yudis.library.model.Shelf;
import mitrais.yudis.library.repository.BookRepository;

@Service
public class BookServiceImpl implements IBookService {
	
	private BookRepository bookRepository;

	@Autowired
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> retriveAll() {
		List<Book> books = bookRepository.findAll();
		return books;
	}

	@Override
	public List<Book> retriveByStatus(BookStatus status) {
		List<Book> books = bookRepository.findBookByStatus(status);
		return books;
	}
	
	@Override
	public List<Book> retriveByTitleStatus(String title, BookStatus status) {
		List<Book> books = bookRepository.findBookByTitleStatus(title, status);
		return books;
	}

	@Override
	public Book get(Integer id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public Book shelved(Book book, Shelf shelf) {
		book.setShelf(shelf);
		book.setStatus(BookStatus.SHELVED);
		return bookRepository.saveAndFlush(book);
	}

	@Override
	public Book unshelved(Book book) {
		book.setShelf(null);
		book.setStatus(BookStatus.NOT_SHELVED);
		return bookRepository.saveAndFlush(book);

	}

}
