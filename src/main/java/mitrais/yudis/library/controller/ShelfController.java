package mitrais.yudis.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mitrais.yudis.library.model.Book;
import mitrais.yudis.library.model.Shelf;
import mitrais.yudis.library.service.IBookService;
import mitrais.yudis.library.service.IShelfService;

@RestController
public class ShelfController {

	private IShelfService shelfService;
	private IBookService bookServive;
	
	@Autowired
	public void setShelfService(IShelfService shelfService) {
		this.shelfService = shelfService;
	}
	
	@Autowired
	public void setBookServive(IBookService bookServive) {
		this.bookServive = bookServive;
	}
	
	@GetMapping("/shelf")
	public List<Shelf> retriveAll() {
		return shelfService.retriveAll();
	}
	
	@GetMapping("/shelf/{id}")
	public Shelf retriveById(@PathVariable String id) {
		return shelfService.get(Integer.parseInt(id));
	}
	
	@GetMapping("/shelf/{id}/book/{book_id}")
	public void shelfBook(@PathVariable String id, @PathVariable String book_id) {
		Shelf shelf = shelfService.get(Integer.parseInt(id));
		Book book = bookServive.get(Integer.parseInt(book_id));
		
		bookServive.shelved(book, shelf);
		shelfService.updateCapacity("shelf",shelf);
	}
	
	@GetMapping("/unshelf/{id}/book/{book_id}")
	public void unshelfBook(@PathVariable String id, @PathVariable String book_id) {
		Shelf shelf = shelfService.get(Integer.parseInt(id));
		Book book = bookServive.get(Integer.parseInt(book_id));
		
		bookServive.unshelved(book);
		shelfService.updateCapacity("unshelf",shelf);
	}
}
