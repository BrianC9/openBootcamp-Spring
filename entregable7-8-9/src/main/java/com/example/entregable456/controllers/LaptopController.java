package com.example.entregable456.controllers;


import com.example.entregable456.models.Laptop;
import com.example.entregable456.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final String ENDPOINT_BASE = "/laptops";

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Find All
    @GetMapping(ENDPOINT_BASE)

    public ResponseEntity<List<Laptop>> findAll() {
        if (laptopRepository.count() == 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(laptopRepository.findAll());

    }
    // Find one by id
    @GetMapping("laptops/{id}")
    public ResponseEntity<Laptop> findOne(@PathVariable Long id){
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        System.out.println(id);
        return optionalLaptop.isPresent() ? ResponseEntity.ok(optionalLaptop.get()): ResponseEntity.notFound().build();

    }

    // Create a Laptop
    @PostMapping(ENDPOINT_BASE)
    public ResponseEntity<Laptop> saveLaptop(@RequestBody Laptop laptopNueva) {
    if (laptopNueva.getId() != null){
        return ResponseEntity.badRequest().build();
    }
        Laptop laptopCreada = laptopRepository.save(laptopNueva);
        return ResponseEntity.ok(laptopCreada);
    }

    // Update a Laptop

    @PutMapping(ENDPOINT_BASE)
    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptopToUpdate){

        if(laptopToUpdate.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptopToUpdate.getId())){
            return ResponseEntity.notFound().build();
        }

        Laptop laptopUpdated = laptopRepository.save(laptopToUpdate);
        return  ResponseEntity.ok(laptopUpdated);
    }

    // Delete a Laptop by id

    @DeleteMapping(ENDPOINT_BASE+"/{id}")
    public ResponseEntity<Laptop> deleteOne(@PathVariable Long id){
        if (!laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping(ENDPOINT_BASE)
    public ResponseEntity<Laptop> deleteAll(){
        if (laptopRepository.count() == 0){
            return  ResponseEntity.notFound().build();
        }
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();

    }


}
