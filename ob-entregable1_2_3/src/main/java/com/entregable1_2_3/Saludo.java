package com.entregable1_2_3;

import org.springframework.stereotype.Component;

@Component
public class Saludo {
    public Saludo() {
    }
    public void imprimeSaludo(){
        System.out.println("Hola, soy la clase Saludo");
    }
}
