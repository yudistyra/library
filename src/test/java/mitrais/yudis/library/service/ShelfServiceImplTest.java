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

import mitrais.yudis.library.model.Shelf;
import mitrais.yudis.library.repository.ShelfRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShelfServiceImplTest {

	@InjectMocks
	private ShelfServiceImpl shelfService;
	@Mock
	private ShelfRepository shelfRepository;
	
	@Test
	public void retriveAllTest() {
		// Preparation
        Mockito.when(shelfRepository.findAll()).thenReturn(Arrays.asList(
        		new Shelf(1,10, 0),
        		new Shelf(2,20, 0)
        ));

        // Action
        List<Shelf> items = shelfService.retriveAll();

        // Assertion / Verification
        assertEquals(2, items.size());

        Mockito.verify(shelfRepository, atLeastOnce()).findAll();
	}
	
	@Test
	public void updateCapacityTest() {
		// Preparation
		Shelf shelf = new Shelf(1,10, 0);
		
        Mockito.when(shelfRepository.saveAndFlush(shelf)).thenReturn(
        		new Shelf(1,10, 1)
        );

        // Action
        Shelf items = shelfService.updateCapacity("shelf", shelf);

        // Assertion / Verification
        assertNotNull(items);
        assertEquals(shelf.getId(), items.getId());
        assertEquals(shelf.getCurrentCapacity(), items.getCurrentCapacity());

        Mockito.verify(shelfRepository, atLeastOnce()).saveAndFlush(shelf);
	}

}
