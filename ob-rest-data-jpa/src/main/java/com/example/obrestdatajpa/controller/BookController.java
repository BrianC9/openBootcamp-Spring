package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/api/v1/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        // opción 1
//        if (bookOpt.isPresent())
//            return ResponseEntity.ok(bookOpt.get());
//        else return ResponseEntity.notFound().build();
        // opcion 2

        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    // Crear un nuevo libro en la BBDD
    @PostMapping("/api/v1/books")
    public Book create(@RequestBody Book book, @RequestBody StreamingHttpOutputMessage.Body body) {
        // guardarLibroRecibidoPorParámetro en la BBDD
        System.out.println(body);
        return bookRepository.save(book);

    }

    // Acctualizar un libro existentte en la BBDD

}
