package com.example.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hola")
    public String hola(){
        return "Hola Mundo!";
    }
    @GetMapping("adios")
    public String adios(){
        return "Adios Mundo!";
    }
    @GetMapping("/admin")
    public String admin(){
        return "Pagina de Admin";
    }
}
