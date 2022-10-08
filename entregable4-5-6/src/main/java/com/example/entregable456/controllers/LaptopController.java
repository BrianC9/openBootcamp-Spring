package com.example.entregable456.controllers;


import com.example.entregable456.models.Laptop;
import com.example.entregable456.repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Find All
    @GetMapping("/laptops")

    public List<Laptop> findAll() {
        return laptopRepository.findAll();

    }

    // Save a Laptop
    @PostMapping("/laptops")
    public Laptop saveLaptop(@RequestBody Laptop laptopNueva) {
        laptopRepository.save(laptopNueva);
        return laptopNueva;
    }


}
