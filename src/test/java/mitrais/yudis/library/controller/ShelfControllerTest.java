package mitrais.yudis.library.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import mitrais.yudis.library.model.Shelf;
import mitrais.yudis.library.service.BookServiceImpl;
import mitrais.yudis.library.service.ShelfServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(ShelfController.class)
public class ShelfControllerTest {
	@Autowired
    private MockMvc mockMvc;
	@MockBean
	private ShelfServiceImpl shelfService;
	@MockBean
	private BookServiceImpl bookService;

	@Test
	public void retriveAllTest() throws Exception {
		// PREPARATION
        when(shelfService.retriveAll()).thenReturn(Arrays.asList(
        		new Shelf(1,10, 0),
        		new Shelf(2,20, 0)
        ));

        // Call GET /all-items return status 200, content json array
        RequestBuilder request = MockMvcRequestBuilders
                .get("/shelf");

        // ACTION & ASSERTION
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}]"));
	}

}
