package mitrais.yudis.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitrais.yudis.library.model.Book;
import mitrais.yudis.library.model.BookStatus;

public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query("SELECT books FROM Book books WHERE status = :status")
    List<Book> findBookByStatus(BookStatus status);
	
	@Query("SELECT books FROM Book books WHERE title like %:title% and status = :status")
    List<Book> findBookByTitleStatus(String title, BookStatus status);
}
