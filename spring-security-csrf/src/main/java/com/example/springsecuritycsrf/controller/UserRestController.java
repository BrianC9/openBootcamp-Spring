package com.example.springsecuritycsrf.controller;

import com.example.springsecuritycsrf.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @GetMapping("/api/hola")
    public String hola(){
        return "Hola mundo, endpoint accesible";
    }

    @GetMapping("/api/autenticado")
    public String autenticado(){
        return "Autenticación correcta";
    }

    @PostMapping("/api/users")
    public String save(@RequestBody UserDTO user){
        System.out.println(user);
        return user.toString();
    }
}
