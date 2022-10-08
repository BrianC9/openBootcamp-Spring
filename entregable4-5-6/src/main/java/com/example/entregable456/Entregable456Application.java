package com.example.entregable456;

import com.example.entregable456.models.Laptop;
import com.example.entregable456.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;


@SpringBootApplication
public class Entregable456Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Entregable456Application.class, args);
        LaptopRepository LRepository = context.getBean(LaptopRepository.class);
        Laptop l1 = new Laptop(null, "Dell", "Inspiron 3500", 450.99, LocalDate.of(2013, 10, 2));
        Laptop l2 = new Laptop(null, "HP", "Omen", 900.00, LocalDate.now());
        LRepository.save(l1);
        LRepository.save(l2);
        System.out.println(LRepository.findAll());

    }

}
