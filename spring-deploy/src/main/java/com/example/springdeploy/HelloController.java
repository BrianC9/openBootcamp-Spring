package com.example.springdeploy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.message}")
    String message;

    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo!!";
    }

    @GetMapping("/var")
    public String printVar() {
        return message;
    }
    @GetMapping("/tailwind")
    public String tailwind() {
        return "<!doctype html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><script src='https://cdn.tailwindcss.com'></script></head><body><h1 class='text-3xl text-sky-700 font-bold underline '> Hello world from Java API! </h1></body></html>";
    }
}
