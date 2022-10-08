package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    //Atributos
    //Esta clase nos permitirá persnoalizar los logs que enviemos desde nuestra aplicación
    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepository;

    //Constructores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // CRUD sobre la entidad Book

    /** Buscar todos los libros que hay en la BBDD
     *
     * @return List<Book>
     *
     * */
    @GetMapping("/api/v1/books")
    public ResponseEntity<List<Book>> findAll() {
        if(bookRepository.count() == 0){
            log.warn("Intentando buscar libros. La BBDD está vacía");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bookRepository.findAll());
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

        return  bookOpt.isPresent() ?ResponseEntity.ok(bookOpt.get()):ResponseEntity.notFound().build();
        // opcion 2

        //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    /** Crear un nuevo libro en la BBDD
     * No colisiona con el método findAll porque son diferentes métodos HTTP
     * @param book Book JSON parseado del body de la petición
     * @return Book ya con un Id generado por la BBDD
     * */
    @PostMapping("/api/v1/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        // guardarLibroRecibidoPorParámetro en la BBDD
        if(book.getId() != null){
            log.warn("Intentando crear un book con un id");
            return ResponseEntity.badRequest().build();
        }


        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }

    /**
     * Actualizar un libro existente en la BBDD
     */
    @PutMapping("/api/v1/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null){
            log.warn("Intentando actualizar libro que no existe");
            return ResponseEntity.badRequest().build();
        }

        if(!bookRepository.existsById(book.getId())){
            log.warn("NO existe el book con id: "+book.getId());
            return ResponseEntity.notFound().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }
    /**
     * Borrar un book de la BBDD, buscandolo por id
     */
    @DeleteMapping("/api/v1/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        /* A la hora de borrar un registro de la base de datos, nos podemos encontrar con problemas de integridad referencial
        * esto es, que haya tablas que guarden referencia al valor que vamos a borrar por FK, por lo tanto
        * deberemos asegurarnos antes de tener establecido un métdo de borrado, en cascado, etc.
        *
        */
        if (!bookRepository.existsById(id)){
            log.warn("Intentando eliminar un libro que no existe");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
    /**
     * Borrar TODOS los registros de la base de datos
     */
    public ResponseEntity<Book> deleteAll(){
        log.info("REST Requesto for borrar todos los registros de la BBDD");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
