package mitrais.yudis.library.service;

import java.util.List;

import mitrais.yudis.library.model.Shelf;

public interface IShelfService {
	List<Shelf> retriveAll();
	Shelf get(Integer id);
	Shelf updateCapacity(String command,Shelf shelf);
}
