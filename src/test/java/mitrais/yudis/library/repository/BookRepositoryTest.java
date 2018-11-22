package mitrais.yudis.library.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import mitrais.yudis.library.model.Book;
import mitrais.yudis.library.model.BookStatus;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void getBooksByStatusTest() {
		List<Book> books = bookRepository.findBookByStatus(BookStatus.NOT_SHELVED);
		
		assertEquals(BookStatus.NOT_SHELVED, books.get(0).getStatus());
	}

	public void getBooksByTitleStatusTest() {
		List<Book> books = bookRepository.findBookByTitleStatus("JSP", BookStatus.NOT_SHELVED);
		
		assertEquals("JSP", books.get(0).getTitle());
		assertEquals(BookStatus.NOT_SHELVED, books.get(0).getStatus());
	}
}
