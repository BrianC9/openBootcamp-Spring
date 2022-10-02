package com.entregable1_2_3;

import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    public NotificationService() {
    }

    public void imprimeSaludo(){
        System.out.println("Hola, soy la clase " + getClass().getName());
    }
}
