package mitrais.yudis.library.service;

import java.util.List;

import mitrais.yudis.library.model.Book;
import mitrais.yudis.library.model.BookStatus;
import mitrais.yudis.library.model.Shelf;

public interface IBookService {
	List<Book> retriveAll();
	List<Book> retriveByStatus(BookStatus status);
	List<Book> retriveByTitleStatus(String title, BookStatus status);
	Book get(Integer id);
	Book shelved(Book book, Shelf shelf);
	Book unshelved(Book book);
}
