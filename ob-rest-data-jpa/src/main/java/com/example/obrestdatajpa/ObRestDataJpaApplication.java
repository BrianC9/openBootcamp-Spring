package com.example.obrestdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDataJpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestDataJpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		// Operaciones CRUD

		// Crear Book
		Book libroNuevo1 = new Book(null,"Narnia","Autor",300,15.90, LocalDate.now(),true);
		Book libroNuevo2 = new Book(null,"El principito","Anonimo",100,12.90, LocalDate.of(1997,4,23),false);
		// Almacenar Book
		repository.save(libroNuevo1);
		repository.save(libroNuevo2);
		// Recuperar todos los Book

		System.out.println(repository.findAll());
		// Borrar un Book
		repository.delete(libroNuevo2);
		System.out.println(repository.findAll());


	}

}
