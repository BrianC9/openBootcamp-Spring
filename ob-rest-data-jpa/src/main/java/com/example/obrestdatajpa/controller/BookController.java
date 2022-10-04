package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    //Atributos

    private BookRepository bookRepository;

    //Constructores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // CRUD sobre la entidad Book

    // Buscar todos los libros
    @GetMapping("/api/v1/books")
    public List<Book> findAll() {

        return bookRepository.findAll();
        // La librería jackson es la que hace la conversión de código Java a formato JSON
        // De esta manera a la hora de realizar una petición desde un punto externo, este recibe un JSON
    }
    // Buscar un libro por el id


    // Crear un nuevo libro en la BBDD


    // Acctualizar un libro existentte en la BBDD

}
