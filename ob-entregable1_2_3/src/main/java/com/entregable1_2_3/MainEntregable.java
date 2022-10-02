package com.entregable1_2_3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainEntregable {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //Ejercicio 1

        Saludo primerSaludo = (Saludo) context.getBean("saludo");
        primerSaludo.imprimeSaludo();

        // Ejercicio 2

        UserService userServ = (UserService) context.getBean("userService");
        userServ.notification.imprimeSaludo();
    }
}
