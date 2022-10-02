package com.example.obspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainObSpringdatajpaApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainObSpringdatajpaApplication.class, args);

        CocheRepository repository = context.getBean(CocheRepository.class);

        System.out.println("Número de coches en BBDD: " + repository.count());

        // crear y almacenar un coche en BBDD

        Coche toyota = new Coche(null, "Toyota", "Prius", 2010);
        Coche ibiza = new Coche(null, "Seat", "Ibiza", 2010);
        Coche ferrari = new Coche(null, "Ferrari", "Portofino", 2010);


        repository.save(toyota);
        repository.save(ibiza);
        repository.save(ferrari);
        
        System.out.println("Número de coches en BBDD: " + repository.count());

        // recuperar un coche por el id

        System.out.println(repository.findAll());
    }

}
