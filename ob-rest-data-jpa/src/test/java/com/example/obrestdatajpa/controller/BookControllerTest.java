package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {


    /*
     * Con esta clase podemos crear una request HTTP vinculadas a una dirección y un puerto
     * Nos permite utilizar los métodos HTTP, para poder hacerles desde Java de manera interna.
     * */
    private TestRestTemplate testRestTemplate;

    @Autowired // Le decimos a Spring que nos inyecte el builder.
    private RestTemplateBuilder restTemplateBuilder; // Nos permite construir la testRestTemplate

    @LocalServerPort
    private int PORT;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + PORT);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }


    @Test
    @DisplayName("Comprueba que se pinta el hola mundo en el body")
    void hello() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hola", String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("Hola Mundo!!", response.getBody());
    }

    @Test
    @DisplayName("comprueba que la lista que devuelve es correcta")
    void findAll() {
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/v1/books", Book[].class);

        //List<Book> listaBooks = Arrays.asList(response.getBody());
        // Como no hemos cargado ningún libro previamente, tendrá 0 libros
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertEquals(204, response.getStatusCodeValue());
        //Assertions.assertEquals(0, listaBooks.size());

    }

    @Test
    @DisplayName("Comprueba que la busqueda por id es correcta")
    void findById() {
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/v1/books/1", Book[].class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Comprueba que crea un Book y le añade el id")
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                                
                "title": "Libro creado desde Test",
                "autor": "Autor",
                "nPages": 300,
                "price": 15.9,
                "releaseDate": "2022-10-11",
                "eBook": true
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/v1/books", HttpMethod.POST, request, Book.class);
        Book result = response.getBody();
        Assertions.assertEquals(true, result.getId() != null);
        Assertions.assertEquals("Libro creado desde Test", result.getTitle());
    }
}