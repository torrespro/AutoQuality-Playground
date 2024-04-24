package es.torres.books.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.javafaker.Faker;
import es.torres.books.model.Book;
import es.torres.books.model.BookListResponseDTO;
import es.torres.books.model.BookPostDTO;
import es.torres.books.service.BookService;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    protected BookService bookService;
    @Autowired
    protected MockMvc mockMvc;

    Faker faker = new Faker();
    Book book = new Book(1L,
        faker.book().title(),
        faker.lorem().paragraph(),
        faker.book().author(),
        faker.book().title(),
        faker.number().numberBetween(1900, 2020), new ArrayList<>());
    BookPostDTO quijote = new BookPostDTO(1L, "El Quijote", "", "", "", null);

    @Test
    void shouldGetEmptyArrayWhenNoBooks() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.size()", Matchers.is(0)))
            .andDo(print())
            .andReturn();
    }

    @Test
    @DisplayName("should return a list of books")
    void shouldGetBooksWhenServiceReturnsBooks() throws Exception {

        when(bookService.findAllBooks()).thenReturn(List.of(new BookListResponseDTO(), new BookListResponseDTO()));

        this.mockMvc
            .perform(get("/api/v1/books/")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
            .andExpect(status().is(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    @DisplayName("should get a Book when service receives a valid id")
    void shouldGetBookWhenServiceReturnBook() throws Exception {

        when(bookService.findById(1L)).thenReturn(book);

        this.mockMvc
            .perform(get("/api/v1/books/{id}", 1L)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
            .andExpect(status().is(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(book.getId().intValue())))
            .andExpect(jsonPath("$.title", is(book.getTitle())));

        verify(bookService).findById(1L);
    }

    @Test
    @DisplayName("should create a new Book with a valid a payload")
    void shouldCreateNewBookWithValidPayload() throws Exception {
        String requestBody = """
                {
                "author": null,
                "published_year": null,
                "publisher": null,
                "summary": null,
                "title": "El Quijote"
            }
            """;

        when(bookService.save(any())).thenReturn(quijote);

        this
            .mockMvc
            .perform(post("/api/v1/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.title", is("El Quijote")));
    }


    @Test
    void shouldAllowDeletingProduct() throws Exception {
        this.mockMvc
            .perform(delete("/api/v1/books/{id}", 1L)
            )
            .andExpect(status().isNoContent());
        verify(bookService).deleteById(1L);
    }

}
