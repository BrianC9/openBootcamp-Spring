package com.example.obrestdatajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo!!";
    }

    @GetMapping("/tailwind")
    public String tailwind() {
        return """
                <!doctype html>
                <html>
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <script src="https://cdn.tailwindcss.com"></script>
                </head>
                <body>
                  <h1 class="text-3xl text-sky-500 font-bold underline ">
                    Hello world from Java API!
                  </h1>
                </body>
                </html>
                """;
    }
}
