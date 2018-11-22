package mitrais.yudis.library.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import mitrais.yudis.library.model.Book;
import mitrais.yudis.library.model.BookStatus;
import mitrais.yudis.library.model.Shelf;
import mitrais.yudis.library.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookRepository bookRepository;
	
	@Test
	public void retriveAllTest() {
		// Preparation
        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(
                new Book(1, "123", "Java", "Oracle", BookStatus.NOT_SHELVED),
                new Book(2, "456", "PHP", "Zend", BookStatus.NOT_SHELVED)
        ));

        // Action
        List<Book> items = bookService.retriveAll();

        // Assertion / Verification
        assertEquals(2, items.size());
        assertEquals(BookStatus.NOT_SHELVED, items.get(0).getStatus());
        assertNull(items.get(0).getShelf());

        Mockito.verify(bookRepository, atLeastOnce()).findAll();
	}

	@Test
	public void retriveByStatusTest() {
		// Preparation
        Mockito.when(bookRepository.findBookByStatus(BookStatus.NOT_SHELVED)).thenReturn(Arrays.asList(
                new Book(1, "123", "Java", "Oracle", BookStatus.NOT_SHELVED),
                new Book(2, "456", "PHP", "Zend", BookStatus.NOT_SHELVED)
        ));

        // Action
        List<Book> items = bookService.retriveByStatus(BookStatus.NOT_SHELVED);

        // Assertion / Verification
        assertEquals(2, items.size());
        assertEquals(BookStatus.NOT_SHELVED, items.get(0).getStatus());

        Mockito.verify(bookRepository, atLeastOnce()).findBookByStatus(BookStatus.NOT_SHELVED);
	}
	
	@Test
	public void retriveByTitleAndStatusTest() {
		// Preparation
        Mockito.when(bookRepository.findBookByTitleStatus("Java", BookStatus.SHELVED)).thenReturn(Arrays.asList(
                new Book(1, "123", "Java", "Oracle", BookStatus.SHELVED),
                new Book(2, "456", "Java Servlet", "Oracle", BookStatus.SHELVED)
        ));

        // Action
        List<Book> items = bookService.retriveByTitleStatus("Java", BookStatus.SHELVED);

        // Assertion / Verification
        assertEquals(2, items.size());
        assertEquals(BookStatus.SHELVED, items.get(0).getStatus());
        assertTrue(items.get(0).getTitle().contains("Java"));

        Mockito.verify(bookRepository, atLeastOnce()).findBookByTitleStatus("Java", BookStatus.SHELVED);
	}
	
	@Test
	public void bookShelvedTest() {
		// Preparation
		Book newbook = new Book(1, "123", "Java", "Oracle", BookStatus.NOT_SHELVED);
		Shelf shelf = new Shelf(3,10, 0);
		
        Mockito.when(bookRepository.saveAndFlush(newbook)).thenReturn(
        		new Book(1, "123", "Java", "Oracle", BookStatus.SHELVED, shelf)
        );

        // Action
        Book saved = bookService.shelved(newbook, shelf);

        // Assertion / Verification
        assertNotNull(saved);
        assertEquals(BookStatus.SHELVED, saved.getStatus());
        assertEquals(shelf.getId(), saved.getShelf().getId());

        Mockito.verify(bookRepository, atLeastOnce()).saveAndFlush(newbook);
	}
	
	@Test
	public void bookUnShelvedTest() {
		// Preparation
		Book newbook = new Book(1, "123", "Java", "Oracle", BookStatus.NOT_SHELVED);
		Shelf shelf = new Shelf(3,10, 0);
		newbook.setShelf(shelf);
		newbook.setStatus(BookStatus.SHELVED);
		
        Mockito.when(bookRepository.saveAndFlush(newbook)).thenReturn(
        		new Book(1, "123", "Java", "Oracle", BookStatus.NOT_SHELVED, null)
        );

        // Action
        Book saved = bookService.unshelved(newbook);

        // Assertion / Verification
        assertNotNull(saved);
        assertEquals(BookStatus.NOT_SHELVED, saved.getStatus());
        assertNull(saved.getShelf());

        Mockito.verify(bookRepository, atLeastOnce()).saveAndFlush(newbook);
	}
}
