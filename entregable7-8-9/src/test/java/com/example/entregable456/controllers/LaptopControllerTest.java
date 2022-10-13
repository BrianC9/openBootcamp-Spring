package com.example.entregable456.controllers;

import com.example.entregable456.models.Laptop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    // Atributos necesarios para poder ejecutar peticiones HTTP desde Java
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int PORT;

    // Preparación previa a cada test



    // Batería de tests


    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+PORT);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    @DisplayName("Comprueba que la BBDD nos devuelve el código http esperado")
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/laptops",Laptop[].class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        Assertions.assertEquals(204,response.getStatusCodeValue());
    }



    @Test
    void saveLaptop() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String laptopInJson = """
                    {
                        "brand": "marcaNueva",
                        "model": "Inspiron 3500",
                        "price": 450.99,
                        "release": "2013-10-02"
                    }
                """;
        HttpEntity<String> request = new HttpEntity<String>(laptopInJson,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops",HttpMethod.POST,request,Laptop.class);
        Laptop laptopCreated = response.getBody();
        Assertions.assertEquals(true,laptopCreated.getId()!=null);
        Assertions.assertEquals("marcaNueva",laptopCreated.getBrand());
    }

    @Test
    void deleteOne() {
        // Creamos un nuevo registro
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String laptopInJson = """
                    {
                        "brand": "marcaNueva",
                        "model": "Inspiron 3500",
                        "price": 450.99,
                        "release": "2013-10-02"
                    }
                """;
        HttpEntity<String> request = new HttpEntity<String>(laptopInJson,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops",HttpMethod.POST,request,Laptop.class);
        Laptop laptopCreated = response.getBody();
        Assertions.assertEquals(true,laptopCreated.getId()!=null);

        Long idLaptopCreated = laptopCreated.getId();
        // Eliminamos dicho registro

        ResponseEntity<Laptop> deleteResponse = testRestTemplate.exchange("/laptops/"+idLaptopCreated,HttpMethod.DELETE,request,Laptop.class);
        Assertions.assertEquals(204,deleteResponse.getStatusCodeValue());



    }
}